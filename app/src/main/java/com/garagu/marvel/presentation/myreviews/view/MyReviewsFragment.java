package com.garagu.marvel.presentation.myreviews.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import android.widget.ProgressBar;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.common.view.DividingLineDecoration;
import com.garagu.marvel.presentation.myreviews.di.MyReviewsComponent;
import com.garagu.marvel.presentation.myreviews.model.MyReviewViewModel;
import com.garagu.marvel.presentation.myreviews.view.MyReviewsPresenter.MyReviewsView;
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
public class MyReviewsFragment extends BaseFragment implements MyReviewsView {

    @Inject
    MyReviewsPresenter presenter;
    @Inject
    Navigator navigator;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RVRendererAdapter<MyReviewViewModel> adapter;

    @NonNull
    public static MyReviewsFragment newInstance() {
        return new MyReviewsFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
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
        getComponent(MyReviewsComponent.class).inject(this);
    }

    private void initComponents() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final ItemDecoration dividerDecoration = new DividingLineDecoration(getActivity());
        recyclerView.addItemDecoration(dividerDecoration);
        final Renderer<MyReviewViewModel> renderer = new MyReviewRenderer(presenter::onOpenComicClick);
        final RendererBuilder<MyReviewViewModel> rendererBuilder = new RendererBuilder<>(renderer);
        final AdapteeCollection<MyReviewViewModel> emptyList = new ListAdapteeCollection<>(new ArrayList<>());
        adapter = new RVRendererAdapter<>(rendererBuilder, emptyList);
        recyclerView.setAdapter(adapter);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openComicDetail(@NonNull ComicViewModel comic) {
        navigator.openComicDetail(getActivity(), comic);
    }

    @Override
    public void showError(@NonNull String message) {
        showSnackbar(message);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showReviews(@NonNull List<MyReviewViewModel> reviews) {
        final int positionStart = adapter.getItemCount();
        adapter.addAll(reviews);
        adapter.notifyItemRangeChanged(positionStart, adapter.getItemCount());
    }

}