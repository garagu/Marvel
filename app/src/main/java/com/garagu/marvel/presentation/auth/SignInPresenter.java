package com.garagu.marvel.presentation.auth;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.usecase.SignIn;
import com.garagu.marvel.presentation.auth.SignInPresenter.SignInView;
import com.garagu.marvel.presentation.common.model.UserModelMapper;
import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */
public class SignInPresenter extends BasePresenter<SignInView> {

    private final SignIn signIn;
    private final UserModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public SignInPresenter(SignIn signIn, UserModelMapper mapper) {
        this.signIn = signIn;
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
            final SignIn.InputParam input = new SignIn.InputParam(email, password);
            signIn(input);
        }
    }

    void onRegisterClick() {
        getView().openRegister();
    }

    private void signIn(@NonNull SignIn.InputParam input) {
        getView().showProgress();
        signIn.execute(input)
                .map(mapper::mapUserModelToViewModel)
                .subscribe(
                        user -> {
                            getView().hideProgress();
                            getView().openHome(user);
                        },
                        error -> {
                            ;
                            getView().hideProgress();
                            getView().showError(error.getMessage());
                        },
                        () -> { // do nothing
                        },
                        compositeDisposable::add
                );

    }

    interface SignInView extends BaseView {
        void hideProgress();

        void openHome(@NonNull UserViewModel user);

        void openRegister();

        void showError(@NonNull String message);

        void showProgress();

        void showRequiredFieldError(boolean email, boolean password);
    }

}