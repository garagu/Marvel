package com.garagu.marvel.presentation.comic.view.detail.reviews;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.di.ComicComponent;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.detail.reviews.NewReviewPresenter.NewReviewView;
import com.garagu.marvel.presentation.common.view.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by garagu.
 */

public class NewReviewFragment extends BaseFragment implements NewReviewView {

    private static final String KEY_SELECTED_COMIC = "selectedComic";

    @Inject
    NewReviewPresenter presenter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.txt_comic_title)
    TextView txtTitle;
    @BindView(R.id.rating_bar)
    RatingBar ratingBar;
    @BindView(R.id.edtxt_comic_review)
    EditText edTxtReview;

    private ComicViewModel selectedComic;

    public static NewReviewFragment newInstance(@NonNull ComicViewModel selectedComic) {
        final NewReviewFragment fragment = new NewReviewFragment();
        final Bundle args = new Bundle();
        args.putParcelable(KEY_SELECTED_COMIC, selectedComic);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_review;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedComic = getArguments().getParcelable(KEY_SELECTED_COMIC);
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        initDependencyInjection();
        initComponents();
        initPresenter();
    }

    @Override
    public void onDestroyView() {
        presenter.unsubscribe();
        super.onDestroyView();
    }

    private void initDependencyInjection() {
        getComponent(ComicComponent.class).inject(this);
    }

    private void initComponents() {
        txtTitle.setText(selectedComic.getTitle());
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
    }

    @OnClick(R.id.btn_publish)
    void onPublishClick() {
        presenter.onPublishClick(selectedComic, (int) ratingBar.getRating(), edTxtReview.getText().toString());
    }

    @Override
    public void close() {
        getActivity().onBackPressed();
    }

    @Override
    public void hideKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edTxtReview.getWindowToken(), 0);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showAlert(@StringRes int messageIdRes) {
        showSnackbar(messageIdRes);
    }

    @Override
    public void showError(@NonNull String message) {
        showSnackbar(message);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

}