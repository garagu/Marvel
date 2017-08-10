package com.garagu.marvel.presentation.comic.view.detail.reviews;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.usecase.GetReviewsByComic;
import com.garagu.marvel.presentation.comic.view.detail.reviews.ComicReviewsPresenter.ComicReviewsView;
import com.garagu.marvel.presentation.common.model.ReviewModelMapper;
import com.garagu.marvel.presentation.common.model.ReviewViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */
public class ComicReviewsPresenter extends BasePresenter<ComicReviewsView> {

    private final GetReviewsByComic getReviewsByComic;
    private final ReviewModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ComicReviewsPresenter(GetReviewsByComic getReviewsByComic, ReviewModelMapper mapper) {
        this.getReviewsByComic = getReviewsByComic;
        this.mapper = mapper;
    }

    void subscribe(String comicId) {
        getView().showProgress();
        getReviewsByComic.execute(comicId)
                .map(mapper::mapModelToViewModel)
                .subscribe(
                        reviews -> getView().showReviews(reviews),
                        error -> {
                            getView().showError(error.getMessage());
                            getView().hideProgress();
                        },
                        () -> getView().hideProgress(),
                        compositeDisposable::add
                );
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.dispose();
    }

    interface ComicReviewsView extends BaseView {
        void hideProgress();

        void showError(@NonNull String message);

        void showProgress();

        void showReviews(List<ReviewViewModel> reviews);
    }

}