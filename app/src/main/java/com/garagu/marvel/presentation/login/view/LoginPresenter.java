package com.garagu.marvel.presentation.login.view;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.usecase.Login;
import com.garagu.marvel.presentation.common.model.UserModelMapper;
import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.login.view.LoginPresenter.LoginView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    private final Login login;
    private final UserModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public LoginPresenter(Login login, UserModelMapper mapper) {
        this.login = login;
        this.mapper = mapper;
    }

    @Override
    public void subscribe() {
        // do nothing
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.dispose();
    }

    void onGoClick(@NonNull String email, @NonNull String password) {
        getView().showRequiredFieldError(email.isEmpty(), password.isEmpty());
        if (!email.isEmpty() && !password.isEmpty()) {
            final Login.InputParam input = new Login.InputParam(email, password);
            login(input);
        }
    }

    void onRegisterClick() {
        getView().openRegister();
    }

    private void login(@NonNull Login.InputParam input) {
        getView().showProgress();
        login.execute(input)
                .map(mapper::mapUserModelToViewModel)
                .subscribe(
                        user -> {
                            getView().hideProgress();
                            getView().openHome(user);
                        },
                        error -> {;
                            getView().hideProgress();
                            getView().showError(error.getMessage());
                        },
                        () -> { // do nothing
                        },
                        compositeDisposable::add
                );

    }

    interface LoginView extends BaseView {
        void hideProgress();

        void openHome(@NonNull UserViewModel user);

        void openRegister();

        void showError(@NonNull String message);

        void showProgress();

        void showRequiredFieldError(boolean email, boolean password);
    }

}