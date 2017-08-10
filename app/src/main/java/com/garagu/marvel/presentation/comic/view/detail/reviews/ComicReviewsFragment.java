package com.garagu.marvel.presentation.comic.view.detail.reviews;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.di.ComicComponent;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.detail.reviews.ComicReviewsPresenter.ComicReviewsView;
import com.garagu.marvel.presentation.common.model.ReviewViewModel;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by garagu.
 */
public class ComicReviewsFragment extends BaseFragment implements ComicReviewsView {

    private static final String KEY_SELECTED_COMIC = "selectedComic";

    @Inject
    ComicReviewsPresenter presenter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ComicViewModel selectedComic;
    private RVRendererAdapter<ReviewViewModel> adapter;

    public static ComicReviewsFragment newInstance(ComicViewModel selectedComic) {
        ComicReviewsFragment fragment = new ComicReviewsFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_SELECTED_COMIC, selectedComic);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comic_reviews;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedComic = getArguments().getParcelable(KEY_SELECTED_COMIC);
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        initDependencyInjector();
        initComponents();
        initPresenter();
    }

    private void initDependencyInjector() {
        getComponent(ComicComponent.class).inject(this);
    }

    private void initComponents() {
        txtTitle.setText(selectedComic.getTitle());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        final Renderer<ReviewViewModel> renderer = new ReviewRenderer();
        final RendererBuilder<ReviewViewModel> rendererBuilder = new RendererBuilder<>(renderer);
        final AdapteeCollection<ReviewViewModel> emptyList = new ListAdapteeCollection<>(new ArrayList<>());
        adapter = new RVRendererAdapter<>(rendererBuilder, emptyList);
        recyclerView.setAdapter(adapter);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe(selectedComic.getId());
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(@NonNull String message) {
        showMessage(message);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showReviews(List<ReviewViewModel> reviews) {
        final int positionStart = adapter.getItemCount();
        adapter.addAll(reviews);
        adapter.notifyItemRangeChanged(positionStart, adapter.getItemCount());
    }

}