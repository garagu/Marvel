package com.garagu.marvel.presentation.login.view;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.usecase.Login;
import com.garagu.marvel.domain.usecase.RegisterUser;
import com.garagu.marvel.presentation.common.model.UserModelMapper;
import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.login.view.RegisterPresenter.RegisterView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */
public class RegisterPresenter extends BasePresenter<RegisterView> {

    private final RegisterUser registerUser;
    private final UserModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public RegisterPresenter(RegisterUser registerUser, UserModelMapper mapper) {
        this.registerUser = registerUser;
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

    void onRegisterClick(@NonNull String username, @NonNull String email, @NonNull String password) {
        getView().showRequiredFieldError(username.isEmpty(), email.isEmpty(), password.isEmpty());
        if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            final RegisterUser.InputParam input = new RegisterUser.InputParam(username, email, password);
            registerUser(input);
        }
    }

    void onLoginClick() {
        getView().openLogin();
    }

    private void registerUser(@NonNull RegisterUser.InputParam input) {
        getView().showProgress();
        registerUser.execute(input)
                .map(mapper::mapUserModelToViewModel)
                .subscribe(
                        user -> {
                            getView().showConfirmation();
                            getView().openHome(user);
                        },
                        error -> {
                            getView().showError(error.getMessage());
                            getView().hideProgress();
                        },
                        () -> getView().hideProgress(),
                        compositeDisposable::add
                );
    }

    interface RegisterView extends BaseView {
        void hideProgress();

        void openHome(@NonNull UserViewModel user);

        void openLogin();

        void showConfirmation();

        void showError(String message);

        void showProgress();

        void showRequiredFieldError(boolean username, boolean email, boolean password);
    }

}