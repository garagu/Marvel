package com.garagu.marvel.presentation.comic.view.detail.reviews;

import android.widget.RatingBar;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.model.ReviewViewModel;
import com.garagu.marvel.presentation.common.view.RVRenderer;

import butterknife.BindView;

/**
 * Created by garagu.
 */
public class ReviewRenderer extends RVRenderer<ReviewViewModel> {

    @BindView(R.id.txt_review_username)
    TextView txtUsername;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.txt_review_date)
    TextView txtDate;
    @BindView(R.id.txt_review)
    TextView txtReview;

    @Override
    protected int getLayoutResId() {
        return R.layout.item_review;
    }

    @Override
    public void render() {
        final ReviewViewModel review = getContent();
        txtUsername.setText(review.getAuthor());
        ratingBar.setRating(review.getRate());
        txtDate.setText(review.getDate());
        txtReview.setText(review.getText());
    }

}