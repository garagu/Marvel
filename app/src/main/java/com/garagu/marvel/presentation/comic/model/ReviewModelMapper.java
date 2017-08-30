package com.garagu.marvel.presentation.comic.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.comic.Review;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class ReviewModelMapper {

    @Inject
    public ReviewModelMapper() {
    }

    @NonNull
    public List<ReviewViewModel> listModelToViewModel(@NonNull List<Review> modelList) {
        final List<ReviewViewModel> viewModelList = new ArrayList<>();
        for (Review model : modelList) {
            final ReviewViewModel viewModel = simpleModelToViewModel(model);
            viewModelList.add(viewModel);
        }
        return viewModelList;
    }

    @NonNull
    private ReviewViewModel simpleModelToViewModel(@NonNull Review model) {
        return new ReviewViewModel.Builder()
                .withAuthor(model.getAuthor())
                .withDate(model.getDate())
                .withRate(model.getRate())
                .withText(model.getText())
                .build();
    }

}