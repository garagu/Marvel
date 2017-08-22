package com.garagu.marvel.presentation.reviews.view;

import android.widget.RatingBar;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.model.ReviewViewModel;
import com.garagu.marvel.presentation.common.view.RVRenderer;

import butterknife.BindView;

/**
 * Created by garagu.
 */
class MyReviewRenderer extends RVRenderer<ReviewViewModel> {

    @BindView(R.id.txt_comic_title)
    TextView txtComicTitle;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.txt_review)
    TextView txtReview;

    @Override
    protected int getLayoutResId() {
        return R.layout.item_my_review;
    }

    @Override
    public void render() {
        final ReviewViewModel review = getContent();
        txtComicTitle.setText(review.getTitle());
        ratingBar.setRating(review.getRate());
        txtReview.setText(review.getText());
    }

}