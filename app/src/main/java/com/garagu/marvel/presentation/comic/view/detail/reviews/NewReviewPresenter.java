package com.garagu.marvel.presentation.comic.view.detail.reviews;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.model.common.Review;
import com.garagu.marvel.domain.model.common.User;
import com.garagu.marvel.domain.usecase.AddReviewToComic;
import com.garagu.marvel.domain.usecase.GetUser;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.detail.reviews.NewReviewPresenter.NewReviewView;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */

public class NewReviewPresenter extends BasePresenter<NewReviewView> {

    private final GetUser getUser;
    private final AddReviewToComic addReviewToComic;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public NewReviewPresenter(GetUser getUser, AddReviewToComic addReviewToComic) {
        this.getUser = getUser;
        this.addReviewToComic = addReviewToComic;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
        getView().hideKeyboard();
    }

    void onPublishClick(@NonNull ComicViewModel comic, int rate, @NonNull String reviewText) {
        if ((rate != 0) && !reviewText.isEmpty()) {
            getView().showProgress();
            getUser.execute(null)
                    .map(user -> getAddReviewInputParam(comic, rate, reviewText, user))
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

    private AddReviewToComic.InputParam getAddReviewInputParam(@NonNull ComicViewModel comic, int rate, @NonNull String reviewText, @NonNull User user) {
        final Review review = new Review(rate, reviewText, user.getName(), comic.getTitle(), getCurrentDate());
        return new AddReviewToComic.InputParam(comic.getId(), user.getId(), review);
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