package com.garagu.marvel.presentation.character.view.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.garagu.marvel.domain.usecase.GetCharacters;
import com.garagu.marvel.domain.usecase.GetCharactersByName;
import com.garagu.marvel.presentation.character.model.CharacterModelMapper;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.character.view.list.CharacterListPresenter.CharacterListView;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by garagu.
 */

public class CharacterListPresenter extends BasePresenter<CharacterListView> {

    private final GetCharacters getCharacters;
    private final GetCharactersByName getCharactersByName;
    private final CharacterModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Consumer<PaginatedListViewModel<CharacterViewModel>> onNext;
    private Consumer<Throwable> onError;
    private Action onComplete;
    private Consumer<Disposable> onSubscribe;

    @Inject
    public CharacterListPresenter(GetCharacters getCharacters, GetCharactersByName getCharactersByName, CharacterModelMapper mapper) {
        this.getCharacters = getCharacters;
        this.getCharactersByName = getCharactersByName;
        this.mapper = mapper;
    }

    @Override
    public void subscribe() {
        onNext = getView()::showCharacters;
        onError = error -> {
            getView().showError(error.getMessage());
            getView().hideProgress();
        };
        onComplete = () -> getView().hideProgress();
        onSubscribe = compositeDisposable::add;
        getCharacters(0);
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    private void getCharacters(int offset) {
        getView().showProgress();
        getCharacters.execute(offset)
                .map(mapper::paginatedListModelToViewModel)
                .subscribe(onNext, onError, onComplete, onSubscribe);
    }

    private void getCharactersByName(@NonNull String name, int offset) {
        final GetCharactersByName.InputParam inputParam = new GetCharactersByName.InputParam(name, offset);
        getView().showProgress();
        getCharactersByName.execute(inputParam)
                .map(mapper::paginatedListModelToViewModel)
                .subscribe(onNext, onError, onComplete, onSubscribe);
    }

    void onListScrolled(@NonNull String name, int offset) {
        if (name.isEmpty()) {
            getCharacters(offset);
        } else {
            getCharactersByName(name, offset);
        }
    }

    void onSearchClick(@NonNull String name) {
        getView().clearScreen();
        getCharactersByName(name, 0);
    }

    void onThumbnailClick(@Nullable View clickedView, @NonNull CharacterViewModel character) {
        getView().openDetail(clickedView, character);
    }

    interface CharacterListView extends BaseView {
        void clearScreen();

        void hideProgress();

        void openDetail(@Nullable View clickedView, @NonNull CharacterViewModel character);

        void showCharacters(@NonNull PaginatedListViewModel<CharacterViewModel> paginatedListOfCharacters);

        void showError(@NonNull String message);

        void showProgress();
    }

}