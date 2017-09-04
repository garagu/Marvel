package com.garagu.marvel.presentation.auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.auth.SignInPresenter.SignInView;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by garagu.
 */
public class SignInFragment extends BaseFragment implements SignInView, GoogleApiClient.OnConnectionFailedListener {

    private static final int RC_GOOGLE_SIGN_IN = 200;

    @Inject
    SignInPresenter presenter;
    @Inject
    Navigator navigator;

    @BindView(R.id.txt_layout_email)
    TextInputLayout txtLayoutEmail;
    @BindView(R.id.edtxt_email)
    EditText edtxtEmail;
    @BindView(R.id.txt_layout_password)
    TextInputLayout txtLayoutPassword;
    @BindView(R.id.edtxt_password)
    EditText edtxtPassword;

    private GoogleApiClient googleApiClient;
    private ProgressDialog pd;

    @NonNull
    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sign_in;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        initDependencyInjection();
        initPresenter();
    }

    @Override
    public void onDestroyView() {
        presenter.unsubscribe();
        super.onDestroyView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            final GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            presenter.onGoogleSignIn(signInResult);
        }
    }

    private void initDependencyInjection() {
        getComponent(AppComponent.class).inject(this);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
    }

    @OnClick(R.id.btn_go)
    void onGoClick() {
        presenter.onGoClick(edtxtEmail.getText().toString(), edtxtPassword.getText().toString());
    }

    @OnClick(R.id.btn_google)
    void onGoogleSignInClick() {
        presenter.onGoogleSignInClick();
    }

    @OnClick(R.id.btn_register)
    void onRegisterClick() {
        presenter.onRegisterClick();
    }

    @Override
    public void connectGoogle() {
        final GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
                .enableAutoManage(getBaseActivity(), this)
                .build();
    }

    @Override
    public void disconnectGoogle() {
        googleApiClient.stopAutoManage(getBaseActivity());
        googleApiClient.disconnect();
    }

    @Override
    public void hideProgress() {
        try {
            pd.dismiss();
        } catch (Exception e) {
            // do nothing
        }
    }

    @Override
    public void openHome() {
        navigator.openHome(getActivity());
    }

    @Override
    public void showGoogleAccountChooser() {
        Auth.GoogleSignInApi.signOut(googleApiClient); // Sign out to show the account chooser every time
        final Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, RC_GOOGLE_SIGN_IN);
    }

    @Override
    public void openRegister() {
        navigator.openCreateUser(getActivity());
    }

    @Override
    public void showError(@NonNull String message) {
        showSnackbar(message);
    }

    @Override
    public void showGoogleError(int errorCode) {
        GoogleApiAvailability.getInstance().showErrorDialogFragment(getActivity(), errorCode, 0);
    }

    @Override
    public void showProgress() {
        if (pd == null) {
            pd = new ProgressDialog(getActivity());
            pd.setCancelable(false);
            pd.setMessage(getString(R.string.signin_pd_message));
        }
        pd.show();
    }

    @Override
    public void showRequiredFieldError(boolean email, boolean password) {
        txtLayoutEmail.setErrorEnabled(email);
        txtLayoutEmail.setError(email ? getString(R.string.error_empty_field) : null);
        txtLayoutPassword.setErrorEnabled(password);
        txtLayoutPassword.setError(password ? getString(R.string.error_empty_field) : null);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        presenter.onGoogleConnectionFailed(result);
    }

}