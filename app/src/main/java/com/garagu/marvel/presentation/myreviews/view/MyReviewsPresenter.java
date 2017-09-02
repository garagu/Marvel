package com.garagu.marvel.presentation.myreviews.view;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.common.User;
import com.garagu.marvel.domain.usecase.GetComic;
import com.garagu.marvel.domain.usecase.GetReviewsByUser;
import com.garagu.marvel.domain.usecase.GetUser;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.comic.model.ComicModelMapper;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.myreviews.model.MyReviewModelMapper;
import com.garagu.marvel.presentation.myreviews.model.MyReviewViewModel;
import com.garagu.marvel.presentation.myreviews.view.MyReviewsPresenter.MyReviewsView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */
@ActivityScope
public class MyReviewsPresenter extends BasePresenter<MyReviewsView> {

    private final GetUser getUser;
    private final GetReviewsByUser getReviewsByUser;
    private final MyReviewModelMapper reviewMapper;
    private final GetComic getComic;
    private final ComicModelMapper comicMapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public MyReviewsPresenter(GetUser getUser, GetReviewsByUser getReviewsByUser, MyReviewModelMapper reviewMapper, GetComic getComic, ComicModelMapper comicMapper) {
        this.getUser = getUser;
        this.getReviewsByUser = getReviewsByUser;
        this.reviewMapper = reviewMapper;
        this.getComic = getComic;
        this.comicMapper = comicMapper;
    }

    @Override
    public void subscribe() {
        getView().showProgress();
        getUser.execute(null)
                .map(User::getId)
                .flatMap(getReviewsByUser::execute)
                .map(reviewMapper::listModelToViewModel)
                .subscribe(
                        reviews -> {
                            if (reviews.isEmpty()) {
                                getView().showEmptyWarning();
                            } else {
                                getView().showReviews(reviews);
                            }
                        },
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
        compositeDisposable.clear();
    }

    void onOpenComicClick(int comicId) {
        getView().showProgress();
        getComic.execute(comicId)
                .map(comicMapper::simpleModelToViewModel)
                .subscribe(
                        getView()::openComicDetail,
                        error -> {
                            getView().showError(error.getMessage());
                            getView().hideProgress();
                        },
                        () -> getView().hideProgress(),
                        compositeDisposable::add
                );
    }

    interface MyReviewsView extends BaseView {
        void hideProgress();

        void openComicDetail(@NonNull ComicViewModel comic);

        void showEmptyWarning();

        void showError(@NonNull String message);

        void showProgress();

        void showReviews(@NonNull List<MyReviewViewModel> reviews);
    }

}