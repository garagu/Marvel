package com.garagu.marvel.presentation.comic.view.list;

import android.support.annotation.NonNull;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.domain.model.GetComicsInputParam;
import com.garagu.marvel.domain.model.PaginatedList;
import com.garagu.marvel.domain.usecase.GetComicsByCharacter;
import com.garagu.marvel.presentation.comic.view.list.ListPresenter.ListView;
import com.garagu.marvel.presentation.common.BasePresenter;
import com.garagu.marvel.presentation.common.BaseView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */
public class ListPresenter extends BasePresenter<ListView> {

    private GetComicsByCharacter getComicsByCharacter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    ListPresenter(GetComicsByCharacter getComicsByCharacter) {
        this.getComicsByCharacter = getComicsByCharacter;
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
        GetComicsInputParam inputParam = new GetComicsInputParam.Builder()
                .withId(BuildConfig.CHARACTER_ID)
                .withOffset(offset)
                .build();
        getComicsByCharacter
                .execute(inputParam)
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

    void onComicClicked(@NonNull Comic comic) {
        getView().openDetail(comic);
    }

    interface ListView extends BaseView {
        void hideProgress();

        void openDetail(@NonNull Comic comic);

        void showComics(@NonNull PaginatedList<Comic> paginatedList);

        void showError(@NonNull String message);

        void showProgress();
    }

}