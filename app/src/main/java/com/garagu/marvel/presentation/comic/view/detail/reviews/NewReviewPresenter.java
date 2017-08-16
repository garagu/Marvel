package com.garagu.marvel.presentation.comic.view.detail.reviews;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.usecase.AddReviewToComic;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.detail.reviews.NewReviewPresenter.NewReviewView;
import com.garagu.marvel.presentation.common.model.ReviewModelMapper;
import com.garagu.marvel.presentation.common.model.ReviewViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;

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

    void onPublishClick(@NonNull ComicViewModel comic, int rating, @NonNull String reviewText) {
        if ((rating != 0) && !reviewText.isEmpty()) {
            // TODO author
            final ReviewViewModel review = new ReviewViewModel.Builder()
                    .withAuthor("username1")
                    .withDate(getCurrentDate())
                    .withRate(rating)
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
                                getView().showAlert(R.string.comicnewreview_message_confirmation);
                                getView().close();
                            },
                            error -> {
                                getView().showError(error.getMessage());
                                getView().hideProgress();
                            },
                            () -> getView().hideProgress(),
                            compositeDisposable::add
                    );
        } else {
            getView().showAlert(R.string.comicnewreview_message_required_fields);
        }
    }

    @NonNull
    private String getCurrentDate() {
        final Date currentDate = new Date();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

    interface NewReviewView extends BaseView {
        void close();

        void hideKeyboard();

        void hideProgress();

        void showAlert(@StringRes int messageIdRes);

        void showError(@NonNull String message);

        void showProgress();
    }

}