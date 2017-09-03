package com.garagu.marvel.presentation.comic.view.list;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.garagu.marvel.domain.usecase.GetComics;
import com.garagu.marvel.domain.usecase.GetComicsByCharacter;
import com.garagu.marvel.domain.usecase.GetComicsByTitle;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.comic.model.mapper.ComicModelMapper;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.list.ComicListPresenter.ListView;
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
public class ComicListPresenter extends BasePresenter<ListView> {

    private final GetComics getComics;
    private final GetComicsByCharacter getComicsByCharacter;
    private final GetComicsByTitle getComicsByTitle;
    private final ComicModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Consumer<PaginatedListViewModel<ComicViewModel>> onNext;
    private Consumer<Throwable> onError;
    private Action onComplete;
    private Consumer<Disposable> onSubscribe;

    @Inject
    public ComicListPresenter(GetComics getComics, GetComicsByCharacter getComicsByCharacter, GetComicsByTitle getComicsByTitle, ComicModelMapper mapper) {
        this.getComics = getComics;
        this.getComicsByCharacter = getComicsByCharacter;
        this.getComicsByTitle = getComicsByTitle;
        this.mapper = mapper;
    }

    @Override
    public void subscribe() {
        onNext = comicsPaginatedList -> {
            if (comicsPaginatedList.getItems().isEmpty()) {
                getView().showEmptyWarning();
            } else {
                getView().showList(comicsPaginatedList);
            }
        };
        onError = error -> {
            getView().showError(error.getMessage());
            getView().hideProgress();
        };
        onComplete = () -> getView().hideProgress();
        onSubscribe = compositeDisposable::add;
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    void onInitView(int characterId) {
        if (characterId == CharacterViewModel.DEFAULT_ID) {
            getComics(0);
        } else {
            getComicsByCharacter(characterId, 0);
        }
    }

    void onCloseSearch(boolean searchExecuted) {
        if (searchExecuted) {
            getView().clearScreen();
            getComics(0);
        }
    }

    void onComicClick(@NonNull ComicViewModel comic) {
        getView().openDetail(comic);
    }

    void onListScrolled(@NonNull CharSequence searchedText, int characterId, int offset) {
        if (TextUtils.isEmpty(searchedText)) {
            if (characterId == CharacterViewModel.DEFAULT_ID) {
                getComics(offset);
            } else {
                getComicsByCharacter(characterId, offset);
            }
        } else {
            getComicsByTitle(searchedText.toString(), offset);
        }
    }

    void onSearchClick(@NonNull String title) {
        getView().clearScreen();
        getComicsByTitle(title, 0);
    }

    private void getComics(int offset) {
        getView().showProgress();
        getComics.execute(offset)
                .map(mapper::paginatedListModelToViewModel)
                .subscribe(onNext, onError, onComplete, onSubscribe);
    }

    private void getComicsByCharacter(int characterId, int offset) {
        final GetComicsByCharacter.InputParam inputParam = new GetComicsByCharacter.InputParam(characterId, offset);
        getView().showProgress();
        getComicsByCharacter.execute(inputParam)
                .map(mapper::paginatedListModelToViewModel)
                .subscribe(onNext, onError, onComplete, onSubscribe);
    }

    private void getComicsByTitle(@NonNull String title, int offset) {
        final GetComicsByTitle.InputParam inputParam = new GetComicsByTitle.InputParam(title, offset);
        getView().showProgress();
        getComicsByTitle.execute(inputParam)
                .map(mapper::paginatedListModelToViewModel)
                .subscribe(onNext, onError, onComplete, onSubscribe);
    }

    interface ListView extends BaseView {
        void clearScreen();

        void hideProgress();

        void openDetail(@NonNull ComicViewModel comic);

        void showEmptyWarning();

        void showList(@NonNull PaginatedListViewModel<ComicViewModel> paginatedList);

        void showError(@NonNull String message);

        void showProgress();
    }

}