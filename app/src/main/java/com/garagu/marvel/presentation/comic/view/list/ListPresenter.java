package com.garagu.marvel.presentation.comic.view.list;

import com.garagu.marvel.domain.Comic;
import com.garagu.marvel.domain.usecase.GetComicsByCharacter;
import com.garagu.marvel.presentation.comic.view.list.ListPresenter.ListView;
import com.garagu.marvel.presentation.common.BasePresenter;
import com.garagu.marvel.presentation.common.BaseView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class ListPresenter extends BasePresenter<ListView> {

    private GetComicsByCharacter getComicsByCharacter;

    @Inject
    ListPresenter(GetComicsByCharacter getComicsByCharacter) {
        this.getComicsByCharacter = getComicsByCharacter;
    }

    void onComicClicked(Comic comic) {
        getView().openDetail(comic);
    }

    interface ListView extends BaseView {
        void openDetail(Comic comic);

        void showComics(List<Comic> comics);

        void showError(String message);
    }

}