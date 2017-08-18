package com.garagu.marvel.presentation.splash.view;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.datasource.LoginDatasource;
import com.garagu.marvel.domain.usecase.GetUser;
import com.garagu.marvel.presentation.common.model.UserModelMapper;
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

    private final GetUser getUser;
    private final UserModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public SplashPresenter(GetUser getUser, UserModelMapper mapper) {
        this.getUser = getUser;
        this.mapper = mapper;
    }

    @Override
    public void subscribe() {
        getUser.execute(null)
                .map(mapper::mapUserModelToViewModel)
                .subscribe(
                        getView()::openHome,
                        error -> getView().openLogin(),
                        () -> { // do nothing
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