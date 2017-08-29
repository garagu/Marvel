package com.garagu.marvel.presentation.myreviews.view;

import android.widget.RatingBar;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.view.RVRenderer;
import com.garagu.marvel.presentation.myreviews.model.MyReviewViewModel;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by garagu.
 */
class MyReviewRenderer extends RVRenderer<MyReviewViewModel> {

    @BindView(R.id.txt_comic_title)
    TextView txtComicTitle;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.txt_date)
    TextView txtDate;
    @BindView(R.id.txt_review)
    TextView txtReview;

    private final OnClickListener onClickListener;

    MyReviewRenderer(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.item_my_review;
    }

    @Override
    public void render() {
        final MyReviewViewModel review = getContent();
        txtComicTitle.setText(review.getComicTitle());
        ratingBar.setRating(review.getRate());
        txtDate.setText(review.getDate());
        txtReview.setText(review.getText());
    }

    @OnClick(R.id.btn_open_comic)
    void onOpenComicClick() {
        onClickListener.onOpenComicClick(getContent().getComicId());
    }

    public interface OnClickListener {
        void onOpenComicClick(int comicId);
    }

}