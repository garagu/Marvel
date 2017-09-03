package com.garagu.marvel.presentation.comic.view.detail.reviews;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.common.User;
import com.garagu.marvel.domain.usecase.CheckIfUserHasReviewed;
import com.garagu.marvel.domain.usecase.GetReviewsByComic;
import com.garagu.marvel.domain.usecase.GetReviewsByUser;
import com.garagu.marvel.domain.usecase.GetUser;
import com.garagu.marvel.presentation.comic.model.mapper.ReviewModelMapper;
import com.garagu.marvel.presentation.comic.model.ReviewViewModel;
import com.garagu.marvel.presentation.comic.view.detail.reviews.ComicReviewsPresenter.ComicReviewsView;
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
    private final GetUser getUser;
    private final CheckIfUserHasReviewed checkIfUserHasReviewed;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ComicReviewsPresenter(GetReviewsByComic getReviewsByComic, ReviewModelMapper mapper, GetUser getUser, CheckIfUserHasReviewed checkIfUserHasReviewed) {
        this.getReviewsByComic = getReviewsByComic;
        this.mapper = mapper;
        this.getUser = getUser;
        this.checkIfUserHasReviewed = checkIfUserHasReviewed;
    }

    @Override
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    void onAddReviewClick() {
        getView().openNewReview();
    }

    void onInitView(int comicId) {
        getReviewsByComic(comicId);
        checkIfUserHasReviewed(comicId);
    }

    private float getRate(@NonNull List<ReviewViewModel> reviews) {
        float rate = 0;
        for (ReviewViewModel review : reviews) {
            rate += review.getRate();
        }
        return rate / reviews.size();
    }

    private void checkIfUserHasReviewed(int comicId) {
        getUser.execute(null)
                .map(user -> new CheckIfUserHasReviewed.InputParam(user.getId(), comicId))
                .flatMap(checkIfUserHasReviewed::execute)
                .subscribe(
                        hasReviewed -> getView().showFab(hasReviewed),
                        error -> { // do nothing
                        },
                        () -> { // do nothing
                        },
                        compositeDisposable::add
                );
    }

    private void getReviewsByComic(int comicId) {
        getView().showProgress();
        getReviewsByComic.execute(comicId)
                .map(mapper::listModelToViewModel)
                .subscribe(
                        reviews -> {
                            getView().showReviews(reviews);
                            final float rate = getRate(reviews);
                            getView().showRate(rate);
                        },
                        error -> {
                            getView().showError(error.getMessage());
                            getView().hideProgress();
                        },
                        () -> getView().hideProgress(),
                        compositeDisposable::add
                );
    }

    interface ComicReviewsView extends BaseView {
        void hideProgress();

        void openNewReview();

        void showError(@NonNull String message);

        void showFab(boolean hasReviewed);

        void showProgress();

        void showRate(float rate);

        void showReviews(List<ReviewViewModel> reviews);
    }

}