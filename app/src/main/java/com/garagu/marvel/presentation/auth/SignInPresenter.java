package com.garagu.marvel.presentation.auth;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.usecase.GoogleSignIn;
import com.garagu.marvel.domain.usecase.SignIn;
import com.garagu.marvel.presentation.auth.SignInPresenter.SignInView;
import com.garagu.marvel.presentation.common.model.UserModelMapper;
import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by garagu.
 */
public class SignInPresenter extends BasePresenter<SignInView> {

    private final GoogleSignIn googleSignIn;
    private final SignIn signIn;
    private final UserModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Consumer<UserViewModel> onNext;
    private Consumer<Throwable> onError;
    private Action onComplete;
    private Consumer<Disposable> onSubscribe;

    @Inject
    public SignInPresenter(GoogleSignIn googleSignIn, SignIn signIn, UserModelMapper mapper) {
        this.googleSignIn = googleSignIn;
        this.signIn = signIn;
        this.mapper = mapper;
    }

    @Override
    public void subscribe() {
        onNext = user -> {
            getView().hideProgress();
            getView().openHome();
        };
        onError = error -> {
            getView().hideProgress();
            getView().showError(error.getMessage());
        };
        onComplete = () -> { // do nothing
        };
        onSubscribe = compositeDisposable::add;
        getView().connectGoogle();
    }

    @Override
    public void unsubscribe() {
        getView().disconnectGoogle();
        compositeDisposable.clear();
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

    void onGoogleConnectionFailed(@NonNull ConnectionResult result) {
        getView().showGoogleError(result.getErrorCode());
    }

    void onGoogleSignInClick() {
        getView().showGoogleAccountChooser();
    }

    void onGoogleSignIn(@NonNull GoogleSignInResult signInResult) {
        if (signInResult.isSuccess() && (signInResult.getSignInAccount() != null)) {
            final String token = signInResult.getSignInAccount().getIdToken();
            googleSignIn.execute(token)
                    .map(mapper::mapUserModelToViewModel)
                    .subscribe(onNext, onError, onComplete, onSubscribe);
        }
    }

    private void signIn(@NonNull SignIn.InputParam input) {
        getView().showProgress();
        signIn.execute(input)
                .map(mapper::mapUserModelToViewModel)
                .subscribe(onNext, onError, onComplete, onSubscribe);
    }

    interface SignInView extends BaseView {
        void connectGoogle();

        void disconnectGoogle();

        void hideProgress();

        void openHome();

        void openRegister();

        void showError(@NonNull String message);

        void showGoogleAccountChooser();

        void showGoogleError(int errorCode);

        void showProgress();

        void showRequiredFieldError(boolean email, boolean password);
    }

}