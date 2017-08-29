package com.garagu.marvel.presentation.splash;

import com.garagu.marvel.domain.usecase.GetUser;
import com.garagu.marvel.presentation.common.model.UserModelMapper;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.splash.SplashPresenter.SplashView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

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
                        user -> getView().openHome(),
                        error -> getView().openAuth(),
                        () -> { // do nothing
                        },
                        compositeDisposable::add
                );
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    interface SplashView extends BaseView {
        void openHome();

        void openAuth();
    }

}