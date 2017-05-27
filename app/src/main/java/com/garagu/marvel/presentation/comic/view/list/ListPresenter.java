package com.garagu.marvel.presentation.comic.view.list;

import com.garagu.marvel.BuildConfig;
import com.garagu.marvel.domain.model.Comic;
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
        getView().showProgress();
        getComicsByCharacter
                .execute(BuildConfig.CHARACTER_ID)
                .subscribe(
                        comicsPaginatedList -> getView().showComics(comicsPaginatedList),
                        error -> {
                            getView().showError(error.getMessage());
                            getView().hideProgress();
                        },
                        () -> getView().hideProgress(),
                        disposable -> compositeDisposable.add(disposable)
                );
    }

    void destroy() {
        compositeDisposable.dispose();
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