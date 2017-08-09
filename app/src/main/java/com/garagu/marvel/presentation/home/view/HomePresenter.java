package com.garagu.marvel.presentation.home.view;

import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.home.view.HomePresenter.HomeView;

/**
 * Created by garagu.
 */

public class HomePresenter extends BasePresenter<HomeView> {

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    void onCharactersClicked() {
        getView().openCharacters();
    }

    void onComicsClicked() {
        getView().openComics();
    }

    interface HomeView extends BaseView {
        void openCharacters();

        void openComics();
    }

}