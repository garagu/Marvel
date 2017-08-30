package com.garagu.marvel.presentation.myreviews.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.review.MyReview;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class MyReviewModelMapper {

    @Inject
    public MyReviewModelMapper() {
    }

    @NonNull
    public List<MyReviewViewModel> listModelToViewModel(@NonNull List<MyReview> modelList) {
        final List<MyReviewViewModel> viewModelList = new ArrayList<>();
        for (MyReview model : modelList) {
            final MyReviewViewModel viewModel = simpleModelToViewModel(model);
            viewModelList.add(viewModel);
        }
        return viewModelList;
    }

    @NonNull
    private MyReviewViewModel simpleModelToViewModel(@NonNull MyReview model) {
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