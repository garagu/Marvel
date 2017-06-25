package com.garagu.marvel.presentation.comic.view.list;

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
                        comicsPaginatedList -> getView().showComics(comicsPaginatedList), // on next
                        error -> {
                            getView().showError(error.getMessage());
                            getView().hideProgress();
                        }, // on error
                        () -> getView().hideProgress(), // on complete
                        disposable -> compositeDisposable.add(disposable) // on subscribe
                );
    }

    void onListScrolled(int offset) {
        getComics(offset);
    }

    void onComicClicked(Comic comic) {
        getView().openDetail(comic);
    }

    interface ListView extends BaseView {
        void hideProgress();

        void openDetail(Comic comic);

        void showComics(PaginatedList<Comic> paginatedList);

        void showError(String message);

        void showProgress();
    }

}