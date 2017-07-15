package com.garagu.marvel.presentation.character.view;

import com.garagu.marvel.domain.model.common.Offset;
import com.garagu.marvel.domain.usecase.GetCharacters;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;
import com.garagu.marvel.presentation.character.model.CharacterModelMapper;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.character.view.CharacterListPresenter.CharacterListView;

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
        getCharacters.execute(Offset.DEFAULT)
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

    @Override
    public void unsubscribe() {

    }

    interface CharacterListView extends BaseView {
        void hideProgress();

        void showCharacters(PaginatedListViewModel<CharacterViewModel> paginatedListOfCharacters);

        void showError(String message);

        void showProgress();
    }

}