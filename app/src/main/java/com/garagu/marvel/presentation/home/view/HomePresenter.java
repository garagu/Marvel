package com.garagu.marvel.presentation.home.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.usecase.GetUser;
import com.garagu.marvel.domain.usecase.SignOut;
import com.garagu.marvel.presentation.common.model.UserModelMapper;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.home.model.HomeOptionType;
import com.garagu.marvel.presentation.home.model.HomeOptionViewModel;
import com.garagu.marvel.presentation.home.view.HomePresenter.HomeView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    private final GetUser getUser;
    private final SignOut signOut;
    private final UserModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public HomePresenter(GetUser getUser, SignOut signOut, UserModelMapper mapper) {
        this.getUser = getUser;
        this.signOut = signOut;
        this.mapper = mapper;
    }

    @Override
    public void subscribe() {
        getView().showHomeOptions(getHomeOptions());
        getUser.execute(null)
                .map(mapper::mapUserModelToViewModel)
                .subscribe(
                        user -> getView().initLateralMenu(user.getName(), user.getEmail()),
                        error -> getView().showError(error.getMessage()),
                        () -> { // do nothing
                        },
                        compositeDisposable::add
                );
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.dispose();
    }

    private List<HomeOptionViewModel> getHomeOptions() {
        final List<HomeOptionViewModel> homeOptions = new ArrayList<>();
        homeOptions.add(new HomeOptionViewModel(HomeOptionType.CHARACTERS, R.mipmap.iron_man, R.string.home_characters, Gravity.BOTTOM));
        homeOptions.add(new HomeOptionViewModel(HomeOptionType.COMICS, R.mipmap.ant_man, R.string.home_comics, Gravity.BOTTOM | Gravity.END));
        homeOptions.add(new HomeOptionViewModel(HomeOptionType.FAVORITES, R.mipmap.vision, R.string.home_favorites, Gravity.BOTTOM));
        homeOptions.add(new HomeOptionViewModel(HomeOptionType.REVIEWS, R.mipmap.captain_america, R.string.home_reviews, Gravity.BOTTOM | Gravity.END));
        return homeOptions;
    }

    void onAboutClick() {
        getView().openAbout();
    }

    void onCharactersOptionClick() {
        getView().openCharacters();
    }

    void onComicsOptionClick() {
        getView().openComics();
    }

    void onFavoritesOptionClick() {
        getView().showNextVersionMessage();
    }

    void onReviewsOptionClick() {
        getView().openMyReviews();
    }

    void onSignOutClick() {
        signOut.execute(null)
                .subscribe(
                        success -> getView().openSignIn(),
                        error -> getView().showError(error.getMessage()),
                        () -> { // do nothing
                        },
                        compositeDisposable::add
                );
    }

    interface HomeView extends BaseView {
        void initLateralMenu(@Nullable String name, @Nullable String email);

        void openAbout();

        void openCharacters();

        void openComics();

        void openMyReviews();

        void openSignIn();

        void showError(@NonNull String message);

        void showHomeOptions(@NonNull List<HomeOptionViewModel> homeOptions);

        void showNextVersionMessage();
    }

}