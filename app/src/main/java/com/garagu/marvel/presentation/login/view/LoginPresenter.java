package com.garagu.marvel.presentation.login.view;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.datasource.LoginDatasource;
import com.garagu.marvel.data.entity.login.LoginEntity;
import com.garagu.marvel.data.entity.login.RegisterEntity;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.login.view.LoginPresenter.LoginView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by garagu.
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    // TODO domain layer

    private final LoginDatasource datasource;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public LoginPresenter(LoginDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.dispose();
    }

    void onGoClick(@NonNull String email, @NonNull String password) {
        if (email.isEmpty() || password.isEmpty()) {
            // TODO show required fields
        } else {
            login(new LoginEntity(email, password));
        }
    }

    private void login(@NonNull LoginEntity login) {
        getView().showProgress();
        datasource.login(login)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        success -> {
                        },
                        error -> getView().showError(error.getMessage()),
                        () -> {
                            getView().hideProgress();
                            getView().openHome();
                        },
                        compositeDisposable::add
                );

    }

    private void registerUser(@NonNull RegisterEntity user) {
        getView().showProgress();
        datasource.registerUser(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        success -> {
                            getView().showConfirmation();
                            getView().hideProgress();
                        },
                        error -> getView().showError(error.getMessage()),
                        () -> getView().hideProgress(),
                        compositeDisposable::add
                );
    }

    interface LoginView extends BaseView {
        void hideProgress();

        void openHome();

        void showConfirmation();

        void showError(@NonNull String message);

        void showProgress();
    }

}