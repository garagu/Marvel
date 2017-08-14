package com.garagu.marvel.presentation.character.view.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.garagu.marvel.domain.model.common.Offset;
import com.garagu.marvel.domain.usecase.GetCharacters;
import com.garagu.marvel.presentation.character.model.CharacterModelMapper;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.character.view.list.CharacterListPresenter.CharacterListView;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */

public class CharacterListPresenter extends BasePresenter<CharacterListView> {

    private final GetCharacters getCharacters;
    private final CharacterModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public CharacterListPresenter(GetCharacters getCharacters, CharacterModelMapper mapper) {
        this.getCharacters = getCharacters;
        this.mapper = mapper;
    }

    @Override
    public void subscribe() {
        getCharacters(Offset.DEFAULT);
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.dispose();
    }

    private void getCharacters(@NonNull Offset offset) {
        getView().showProgress();
        getCharacters.execute(offset)
                .map(mapper::mapModelToViewModel)
                .subscribe(
                        paginatedListOfCharacters -> getView().showCharacters(paginatedListOfCharacters),
                        error -> {
                            getView().showError(error.getMessage());
                            getView().hideProgress();
                        },
                        () -> getView().hideProgress(),
                        compositeDisposable::add
                );
    }

    void onListScrolled(int offset) {
        final Offset model = new Offset(offset);
        getCharacters(model);
    }

    void onThumbnailClicked(@Nullable View clickedView, @NonNull CharacterViewModel character) {
        getView().openDetail(clickedView, character);
    }

    interface CharacterListView extends BaseView {
        void hideProgress();

        void openDetail(@Nullable View clickedView, @NonNull CharacterViewModel character);

        void showCharacters(@NonNull PaginatedListViewModel<CharacterViewModel> paginatedListOfCharacters);

        void showError(@NonNull String message);

        void showProgress();
    }

}