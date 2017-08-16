package com.garagu.marvel.presentation.common.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.common.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class ReviewModelMapper {

    @NonNull
    public List<ReviewViewModel> mapListModelToViewModel(@NonNull List<Review> modelList) {
        final List<ReviewViewModel> viewModelList = new ArrayList<>();
        for (Review model : modelList) {
            final ReviewViewModel viewModel = mapSimpleModelToViewModel(model);
            viewModelList.add(viewModel);
        }
        return viewModelList;
    }

    @NonNull
    private ReviewViewModel mapSimpleModelToViewModel(@NonNull Review model) {
        return new ReviewViewModel.Builder()
                .withAuthor(model.getAuthor())
                .withDate(model.getDate())
                .withRate(model.getRate())
                .withText(model.getText())
                .withTitle(model.getTitle())
                .build();
    }

    @NonNull
    public Review mapSimpleViewModelToModel(@NonNull ReviewViewModel viewModel) {
        return new Review(
                viewModel.getRate(),
                viewModel.getText(),
                viewModel.getAuthor(),
                viewModel.getTitle(),
                viewModel.getDate()
        );
    }

}