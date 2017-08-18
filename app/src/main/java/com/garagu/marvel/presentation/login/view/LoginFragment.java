package com.garagu.marvel.presentation.login.view;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.login.di.LoginComponent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by garagu.
 */
public class LoginFragment extends BaseFragment implements LoginPresenter.LoginView {

    @Inject
    LoginPresenter presenter;
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

    private ProgressDialog pd;

    @NonNull
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        initDependencyInjection();
        initComponents();
        initPresenter();
    }

    @Override
    public void onDestroyView() {
        presenter.unsubscribe();
        super.onDestroyView();
    }

    private void initDependencyInjection() {
        getComponent(LoginComponent.class).inject(this);
    }

    private void initComponents() {
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
    }

    @OnClick(R.id.btn_go)
    void onGoClick() {
        presenter.onGoClick(edtxtEmail.getText().toString(), edtxtPassword.getText().toString());
    }

    @OnClick(R.id.btn_register)
    void onRegisterClick() {
        presenter.onRegisterClick();
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
    public void openRegister() {
        navigator.openRegister(getActivity());
    }

    @Override
    public void showError(@NonNull String message) {
        showSnackbar(message);
    }

    @Override
    public void showProgress() {
        if (pd == null) {
            pd = new ProgressDialog(getActivity());
            pd.setCancelable(false);
            pd.setMessage(getString(R.string.login_pd_message));
        }
        pd.show();
    }

    @Override
    public void showRequiredFieldError(boolean email, boolean password) {
        txtLayoutEmail.setErrorEnabled(email);
        txtLayoutEmail.setError(email ? getString(R.string.login_error_empty_field) : null);
        txtLayoutPassword.setErrorEnabled(password);
        txtLayoutPassword.setError(password ? getString(R.string.login_error_empty_field) : null);
    }

}