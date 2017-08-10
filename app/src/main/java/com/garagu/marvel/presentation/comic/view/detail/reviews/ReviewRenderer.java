package com.garagu.marvel.presentation.comic.view.detail.reviews;

import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.model.ReviewViewModel;
import com.garagu.marvel.presentation.common.view.RVRenderer;

import butterknife.BindView;

/**
 * Created by garagu.
 */
public class ReviewRenderer extends RVRenderer<ReviewViewModel> {

    @BindView(R.id.txt_review_username)
    TextView txtUsername;
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
        txtReview.setText(review.getText());
    }

}