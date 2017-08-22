package com.garagu.marvel.presentation.auth;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by garagu.
 */

public class RegisterFragment extends BaseFragment implements RegisterPresenter.RegisterView {

    @Inject
    RegisterPresenter presenter;
    @Inject
    Navigator navigator;

    @BindView(R.id.txt_layout_username)
    TextInputLayout txtLayoutUsername;
    @BindView(R.id.edtxt_username)
    EditText edtxtUsername;
    @BindView(R.id.txt_layout_email)
    TextInputLayout txtLayoutEmail;
    @BindView(R.id.edtxt_email)
    EditText edtxtEmail;
    @BindView(R.id.txt_layout_password)
    TextInputLayout txtLayoutPassword;
    @BindView(R.id.edtxt_password)
    EditText edtxtPassword;

    private ProgressDialog pd;

    @NonNull
    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
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

    private void initDependencyInjection() {
        getComponent(AppComponent.class).inject(this);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
    }

    @OnClick(R.id.btn_register)
    void onRegisterClick() {
        presenter.onRegisterClick(
                edtxtUsername.getText().toString(),
                edtxtEmail.getText().toString(),
                edtxtPassword.getText().toString()
        );
    }

    @OnClick(R.id.btn_sign_in)
    void onSignInClick() {
        presenter.onSignInClick();
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
    public void openHome(@NonNull UserViewModel user) {
        navigator.openHome(getActivity(), user);
    }

    @Override
    public void openSignIn() {
        navigator.openSignIn(getActivity());
    }

    @Override
    public void showConfirmation() {
        showSnackbar(R.string.register_message_confirmation);
    }

    @Override
    public void showError(String message) {
        showSnackbar(message);
    }

    @Override
    public void showProgress() {
        if (pd == null) {
            pd = new ProgressDialog(getActivity());
            pd.setCancelable(false);
            pd.setMessage(getString(R.string.register_pd_message));
        }
        pd.show();
    }

    @Override
    public void showRequiredFieldError(boolean username, boolean email, boolean password) {
        setFieldError(txtLayoutUsername, username);
        setFieldError(txtLayoutEmail, email);
        setFieldError(txtLayoutPassword, password);
    }

    private void setFieldError(TextInputLayout textInputLayout, boolean enabled) {
        textInputLayout.setErrorEnabled(enabled);
        textInputLayout.setError(enabled ? getString(R.string.error_empty_field) : null);
    }

}