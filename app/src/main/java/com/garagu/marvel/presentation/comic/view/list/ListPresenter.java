package com.garagu.marvel.presentation.comic.view.list;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.domain.model.InputParam;
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

    void init() {
        getComics(0);
    }

    void destroy() {
        compositeDisposable.dispose();
    }

    private void getComics(int offset) {
        getView().showProgress();
        InputParam inputParam = new InputParam.Builder()
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

        void showComics(PaginatedList<Comic> comics);

        void showError(String message);

        void showProgress();
    }

}