package com.garagu.marvel.presentation.comic.view.list;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.domain.model.PaginatedList;
import com.garagu.marvel.presentation.comic.di.ComicModule;
import com.garagu.marvel.presentation.comic.di.DaggerComicComponent;
import com.garagu.marvel.presentation.comic.view.list.ListPresenter.ListView;
import com.garagu.marvel.presentation.common.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by garagu.
 */
public class ListFragment extends BaseFragment implements ListView {

    @Inject
    ListPresenter presenter;
    @Inject
    ComicAdapter adapter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

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
        presenter.destroy();
    }

    private void initDependencyInjector() {
        DaggerComicComponent.builder()
                .netComponent(getNetComponent())
                .comicModule(new ComicModule())
                .build()
                .inject(this);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.init();
    }

    private void initList() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (isNotLoading() && hasMore && checkScroll(layoutManager, dy)) {
                    presenter.onListScrolled(offset);
                }
            }
        });
        adapter.setOnComicClickListener(comic -> presenter.onComicClicked(comic));
        recyclerView.setAdapter(adapter);
    }

    private boolean checkScroll(LinearLayoutManager layoutManager, int dy) {
        if (dy > 0) {
            int visibleItems = layoutManager.getChildCount();
            int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
            int totalItems = layoutManager.getItemCount();
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
    public void openDetail(Comic comic) {
        // TODO
        Toast.makeText(getActivity(), comic.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComics(PaginatedList<Comic> comics) {
        hasMore = comics.hasMore();
        offset = comics.getOffset();
        adapter.add(comics.getItems());
    }

    @Override
    public void showError(String message) {
        showMessage(message);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

}