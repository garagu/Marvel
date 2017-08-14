package com.garagu.marvel.presentation.comic.view.detail.reviews;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.usecase.AddReviewToComic;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.detail.reviews.NewReviewPresenter.NewReviewView;
import com.garagu.marvel.presentation.common.model.ReviewModelMapper;
import com.garagu.marvel.presentation.common.model.ReviewViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */

public class NewReviewPresenter extends BasePresenter<NewReviewView> {

    private final AddReviewToComic addReviewToComic;
    private final ReviewModelMapper mapper;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public NewReviewPresenter(AddReviewToComic addReviewToComic, ReviewModelMapper mapper) {
        this.addReviewToComic = addReviewToComic;
        this.mapper = mapper;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        compositeDisposable.dispose();
        getView().hideKeyboard();
    }

    void onPublishClick(ComicViewModel comic, String reviewText) {
        // TODO author + rate
        final ReviewViewModel review = new ReviewViewModel.Builder()
                .withAuthor("username1")
                .withDate(getCurrentDate())
                .withRate(5)
                .withText(reviewText)
                .withTitle(comic.getTitle())
                .build();
        getView().showProgress();
        Observable.just(review)
                .map(mapper::mapSimpleViewModelToModel)
                .map(model -> new AddReviewToComic.InputParam(comic.getId(), model))
                .flatMap(addReviewToComic::execute)
                .subscribe(
                        published -> {
                            getView().showConfirmation();
                            getView().close();
                        },
                        error -> {
                            getView().showError(error.getMessage());
                            getView().hideProgress();
                        },
                        () -> getView().hideProgress(),
                        compositeDisposable::add
                );
    }

    private String getCurrentDate() {
        final Date currentDate = new Date();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

    interface NewReviewView extends BaseView {
        void close();

        void hideKeyboard();

        void hideProgress();

        void showConfirmation();

        void showError(@NonNull String message);

        void showProgress();
    }

}