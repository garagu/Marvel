package com.garagu.marvel.presentation.home.view;

import android.support.annotation.NonNull;
import android.view.Gravity;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.home.model.HomeOptionType;
import com.garagu.marvel.presentation.home.model.HomeOptionViewModel;
import com.garagu.marvel.presentation.home.view.HomePresenter.HomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    @Override
    public void subscribe() {
        getView().showHomeOptions(getHomeOptions());
    }

    @Override
    public void unsubscribe() {
        // do nothing
    }

    private List<HomeOptionViewModel> getHomeOptions() {
        final List<HomeOptionViewModel> homeOptions = new ArrayList<>();
        homeOptions.add(new HomeOptionViewModel(HomeOptionType.CHARACTERS, R.mipmap.iron_man, R.string.home_characters, Gravity.BOTTOM));
        homeOptions.add(new HomeOptionViewModel(HomeOptionType.COMICS, R.mipmap.ant_man, R.string.home_comics, Gravity.BOTTOM | Gravity.END));
        homeOptions.add(new HomeOptionViewModel(HomeOptionType.FAVORITES, R.mipmap.vision, R.string.home_favorites, Gravity.BOTTOM));
        homeOptions.add(new HomeOptionViewModel(HomeOptionType.REVIEWS, R.mipmap.captain_america, R.string.home_reviews, Gravity.BOTTOM | Gravity.END));
        return homeOptions;
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
        getView().showNextVersionMessage();
    }

    interface HomeView extends BaseView {
        void openCharacters();

        void openComics();

        void showHomeOptions(@NonNull List<HomeOptionViewModel> homeOptions);

        void showNextVersionMessage();
    }

}