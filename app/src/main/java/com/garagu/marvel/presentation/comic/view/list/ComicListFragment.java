package com.garagu.marvel.presentation.comic.view.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.comic.di.ComicComponent;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.view.Navigator;
import com.garagu.marvel.presentation.comic.view.list.ComicListPresenter.ListView;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.common.view.ImageLoader;
import com.garagu.marvel.presentation.common.view.OnListBottomReachedListener;
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
public class ComicListFragment extends BaseFragment implements ListView, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    private static final String KEY_CHARACTER_ID = "characterId";

    @Inject
    ComicListPresenter presenter;
    @Inject
    Navigator navigator;
    @Inject
    ImageLoader imageLoader;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SearchView searchView;
    private boolean searchExecuted;
    private RVRendererAdapter<ComicViewModel> adapter;
    private boolean hasMore;
    private int offset;
    private int characterId;

    @NonNull
    public static ComicListFragment newInstance() {
        return new ComicListFragment();
    }

    @NonNull
    public static ComicListFragment newInstance(int characterId) {
        final ComicListFragment fragment = new ComicListFragment();
        final Bundle args = new Bundle();
        args.putInt(KEY_CHARACTER_ID, characterId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        characterId = (getArguments() != null) ? getArguments().getInt(KEY_CHARACTER_ID, CharacterViewModel.DEFAULT_ID) : CharacterViewModel.DEFAULT_ID;
        super.onCreate(savedInstanceState);
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
        super.onDestroyView();
        presenter.unsubscribe();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (characterId == CharacterViewModel.DEFAULT_ID) {
            inflater.inflate(R.menu.menu_search, menu);
            final MenuItem searchItem = menu.findItem(R.id.item_search);
            MenuItemCompat.setOnActionExpandListener(searchItem, this);
            searchView = (SearchView) searchItem.getActionView();
            searchView.setQueryHint(getString(R.string.comicinfo_hint_search));
            searchView.setOnQueryTextListener(this);
        }
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchExecuted = true;
        recyclerView.requestFocus();
        presenter.onSearchClick(query);
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        presenter.onCloseSearch(searchExecuted);
        searchExecuted = false;
        return true;
    }

    private void initDependencyInjection() {
        getComponent(ComicComponent.class).inject(this);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
        presenter.onInitView(characterId);
    }

    private void initComponents() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addOnScrollListener(new OnListBottomReachedListener(layoutManager) {
            @Override
            public void onBottomReached() {
                if (isNotLoading() && hasMore) {
                    presenter.onListScrolled(searchView != null ? searchView.getQuery() : "", characterId, offset);
                }
            }
        });
        final Renderer<ComicViewModel> renderer = new ComicRenderer(presenter::onComicClick, imageLoader);
        final RendererBuilder<ComicViewModel> rendererBuilder = new RendererBuilder<>(renderer);
        final AdapteeCollection<ComicViewModel> emptyList = new ListAdapteeCollection<>(new ArrayList<>());
        adapter = new RVRendererAdapter<>(rendererBuilder, emptyList);
        recyclerView.setAdapter(adapter);
    }

    private void clearList() {
        hasMore = false;
        offset = 0;
        adapter.clear();
        adapter.notifyDataSetChanged();
    }

    private boolean isNotLoading() {
        return (progressBar.getVisibility() == View.GONE);
    }

    @Override
    public void clearScreen() {
        hideKeyboard();
        clearList();
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openDetail(@NonNull ComicViewModel comic) {
        navigator.openComicDetail(getActivity(), comic);
    }

    @Override
    public void showList(@NonNull PaginatedListViewModel<ComicViewModel> paginatedList) {
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
        showSnackbar(message);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

}