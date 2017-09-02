package com.garagu.marvel.presentation.myreviews.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.review.MyReview;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.common.model.ModelToViewModelMapper;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class MyReviewModelMapper extends ModelToViewModelMapper<MyReview, MyReviewViewModel> {

    @Inject
    public MyReviewModelMapper() {
    }

    @NonNull
    @Override
    public MyReviewViewModel simpleModelToViewModel(@NonNull MyReview model) {
        return new MyReviewViewModel.Builder()
                .withAuthor(model.getAuthor())
                .withDate(model.getDate())
                .withRate(model.getRate())
                .withText(model.getText())
                .withComicId(model.getComicId())
                .withComicTitle(model.getComicTitle())
                .build();
    }

}