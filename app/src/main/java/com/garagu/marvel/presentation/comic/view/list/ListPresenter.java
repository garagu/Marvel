package com.garagu.marvel.presentation.comic.view.list;

import android.support.annotation.NonNull;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.domain.usecase.GetComicsByCharacter;
import com.garagu.marvel.presentation.comic.model.ComicModelMapper;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;
import com.garagu.marvel.presentation.comic.view.list.ListPresenter.ListView;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */
public class ListPresenter extends BasePresenter<ListView> {

    private final GetComicsByCharacter getComicsByCharacter;
    private final ComicModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    ListPresenter(GetComicsByCharacter getComicsByCharacter, ComicModelMapper mapper) {
        this.getComicsByCharacter = getComicsByCharacter;
        this.mapper = mapper;
    }

    @Override
    public void subscribe() {
        getComics(0);
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.dispose();
    }

    private void getComics(int offset) {
        getView().showProgress();
        final GetComicsByCharacter.InputParam inputParam = new GetComicsByCharacter.InputParam(BuildConfig.CHARACTER_ID, offset);
        getComicsByCharacter
                .execute(inputParam)
                .map(mapper::mapModelToViewModel)
                .subscribe(
                        comicsPaginatedList -> getView().showComics(comicsPaginatedList),
                        error -> {
                            getView().showError(error.getMessage());
                            getView().hideProgress();
                        },
                        () -> getView().hideProgress(),
                        compositeDisposable::add
                );
    }

    void onListScrolled(int offset) {
        getComics(offset);
    }

    void onComicClicked(@NonNull ComicViewModel comic) {
        getView().openDetail(comic);
    }

    interface ListView extends BaseView {
        void hideProgress();

        void openDetail(@NonNull ComicViewModel comic);

        void showComics(@NonNull PaginatedListViewModel<ComicViewModel> paginatedList);

        void showError(@NonNull String message);

        void showProgress();
    }

}