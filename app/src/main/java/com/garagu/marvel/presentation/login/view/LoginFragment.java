package com.garagu.marvel.presentation.login.view;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.garagu.marvel.R;
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

    @BindView(R.id.edtxt_email)
    EditText edtxtEmail;
    @BindView(R.id.edtxt_password)
    EditText edtxtPassword;

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

    @Override
    public void hideProgress() {

    }

    @Override
    public void openHome() {
        navigator.openHome(getActivity());
    }

    @Override
    public void showConfirmation() {
        showSnackbar(R.string.register_message_confirmation);
    }

    @Override
    public void showError(@NonNull String message) {
        showSnackbar(message);
    }

    @Override
    public void showProgress() {

    }

}