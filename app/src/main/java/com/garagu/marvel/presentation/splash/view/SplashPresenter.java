package com.garagu.marvel.presentation.splash.view;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.datasource.LoginDatasource;
import com.garagu.marvel.presentation.common.model.UserViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.splash.view.SplashPresenter.SplashView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by garagu.
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    // TODO domain layer

    private final LoginDatasource datasource;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public SplashPresenter(LoginDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public void subscribe() {
        datasource.getUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
                            UserViewModel viewModel = new UserViewModel.Builder()
                                    .withEmail(user.getEmail())
                                    .withId(user.getId())
                                    .withName(user.getName())
                                    .build();
                            getView().openHome(viewModel);
                        },
                        error -> getView().openLogin(),
                        () -> {
                        },
                        compositeDisposable::add
                );
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.dispose();
    }

    interface SplashView extends BaseView {
        void openHome(@NonNull UserViewModel user);

        void openLogin();
    }

}