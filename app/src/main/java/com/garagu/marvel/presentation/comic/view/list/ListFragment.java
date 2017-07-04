package com.garagu.marvel.presentation.comic.view.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import android.widget.ProgressBar;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.di.ComicComponent;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.model.PaginatedList;
import com.garagu.marvel.presentation.comic.view.Navigator;
import com.garagu.marvel.presentation.comic.view.list.ListPresenter.ListView;
import com.garagu.marvel.presentation.common.BaseFragment;
import com.garagu.marvel.presentation.common.ImageLoader;
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
public class ListFragment extends BaseFragment implements ListView {

    @Inject
    ListPresenter presenter;
    @Inject
    Navigator navigator;
    @Inject
    ImageLoader imageLoader;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RVRendererAdapter<ComicViewModel> adapter;
    private boolean hasMore;
    private int offset;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        initDependencyInjector();
        initList();
        initPresenter();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unsubscribe();
    }

    private void initDependencyInjector() {
        getComponent(ComicComponent.class).inject(this);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
    }

    private void initList() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (isNotLoading() && hasMore && checkScroll(layoutManager, dy)) {
                    presenter.onListScrolled(offset);
                }
            }
        });
        final Renderer<ComicViewModel> renderer = new ComicRenderer(comic -> presenter.onComicClicked(comic), imageLoader);
        final RendererBuilder<ComicViewModel> rendererBuilder = new RendererBuilder<>(renderer);
        final AdapteeCollection<ComicViewModel> emptyList = new ListAdapteeCollection<>(new ArrayList<>());
        adapter = new RVRendererAdapter<>(rendererBuilder, emptyList);
        recyclerView.setAdapter(adapter);
    }

    private boolean checkScroll(@NonNull LinearLayoutManager layoutManager, int dy) {
        if (dy > 0) {
            final int visibleItems = layoutManager.getChildCount();
            final int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
            final int totalItems = layoutManager.getItemCount();
            return (visibleItems + firstVisibleItem >= totalItems);
        }
        return false;
    }

    private boolean isNotLoading() {
        return (progressBar.getVisibility() == View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openDetail(@NonNull ComicViewModel comic) {
        navigator.openComicDetail(getActivity(), this, comic);
    }

    @Override
    public void showComics(@NonNull PaginatedList<ComicViewModel> paginatedList) {
        hasMore = paginatedList.hasMore();
        offset = paginatedList.getOffset();
        updateList(paginatedList.getItems());
    }

    private void updateList(@NonNull List<ComicViewModel> comics) {
        final int positionStart = adapter.getItemCount();
        adapter.addAll(comics);
        adapter.notifyItemRangeChanged(positionStart, adapter.getItemCount());
    }

    @Override
    public void showError(@NonNull String message) {
        showMessage(message);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

}