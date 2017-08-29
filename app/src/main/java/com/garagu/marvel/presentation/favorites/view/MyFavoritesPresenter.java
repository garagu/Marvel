package com.garagu.marvel.presentation.favorites.view;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.common.User;
import com.garagu.marvel.domain.usecase.GetCharacter;
import com.garagu.marvel.domain.usecase.GetFavorites;
import com.garagu.marvel.domain.usecase.GetUser;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.character.model.CharacterModelMapper;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.common.model.FavoriteModelMapper;
import com.garagu.marvel.presentation.common.model.FavoriteViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.favorites.view.MyFavoritesPresenter.MyFavoritesView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */
@ActivityScope
public class MyFavoritesPresenter extends BasePresenter<MyFavoritesView> {

    private final GetUser getUser;
    private final GetFavorites getFavorites;
    private final FavoriteModelMapper favoriteMapper;
    private final GetCharacter getCharacter;
    private final CharacterModelMapper characterMapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public MyFavoritesPresenter(GetUser getUser, GetFavorites getFavorites, FavoriteModelMapper favoriteMapper, GetCharacter getCharacter, CharacterModelMapper characterMapper) {
        this.getUser = getUser;
        this.getFavorites = getFavorites;
        this.getCharacter = getCharacter;
        this.favoriteMapper = favoriteMapper;
        this.characterMapper = characterMapper;
    }

    @Override
    public void subscribe() {
        getView().showProgress();
        getUser.execute(null)
                .map(User::getId)
                .flatMap(getFavorites::execute)
                .map(favoriteMapper::listModelToViewModel)
                .subscribe(
                        favorites -> getView().showFavorites(favorites),
                        error -> {
                            getView().showError(error.getMessage());
                            getView().hideProgress();
                        },
                        () -> getView().hideProgress(),
                        compositeDisposable::add
                );
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    void onFavoriteClick(@NonNull FavoriteViewModel favorite) {
        getView().showProgress();
        getCharacter.execute(favorite.getFavoriteId())
                .map(characterMapper::simpleModelToViewModel)
                .subscribe(
                        character -> getView().showCharacter(character),
                        error -> {
                            getView().showError(error.getMessage());
                            getView().hideProgress();
                        },
                        () -> getView().hideProgress(),
                        compositeDisposable::add
                );
    }

    interface MyFavoritesView extends BaseView {
        void hideProgress();

        void showCharacter(@NonNull CharacterViewModel character);

        void showError(@NonNull String message);

        void showProgress();

        void showFavorites(@NonNull List<FavoriteViewModel> favorites);
    }

}