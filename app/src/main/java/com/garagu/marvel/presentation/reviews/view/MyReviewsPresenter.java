package com.garagu.marvel.presentation.reviews.view;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.common.User;
import com.garagu.marvel.domain.usecase.GetReviewsByUser;
import com.garagu.marvel.domain.usecase.GetUser;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.common.model.ReviewModelMapper;
import com.garagu.marvel.presentation.common.model.ReviewViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;
import com.garagu.marvel.presentation.reviews.view.MyReviewsPresenter.MyReviewsView;

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
    private final ReviewModelMapper reviewMapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public MyReviewsPresenter(GetUser getUser, GetReviewsByUser getReviewsByUser, ReviewModelMapper reviewMapper) {
        this.getUser = getUser;
        this.getReviewsByUser = getReviewsByUser;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public void subscribe() {
        getView().showProgress();
        getUser.execute(null)
                .map(User::getId)
                .flatMap(getReviewsByUser::execute)
                .map(reviewMapper::mapListModelToViewModel)
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
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    interface MyReviewsView extends BaseView {
        void hideProgress();

        void showError(@NonNull String message);

        void showProgress();

        void showReviews(@NonNull List<ReviewViewModel> reviews);
    }

}