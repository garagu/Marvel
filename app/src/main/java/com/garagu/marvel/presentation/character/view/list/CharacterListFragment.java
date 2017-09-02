package com.garagu.marvel.presentation.character.view.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
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
import com.garagu.marvel.presentation.character.di.CharacterComponent;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.character.view.Navigator;
import com.garagu.marvel.presentation.character.view.list.CharacterListPresenter.CharacterListView;
import com.garagu.marvel.presentation.character.view.list.CharacterRenderer.OnCardClickListener;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.common.view.CardDecoration;
import com.garagu.marvel.presentation.common.view.ImageLoader;
import com.garagu.marvel.presentation.common.view.OnListBottomReachedListener;
import com.garagu.marvel.presentation.common.view.RVAnimRendererAdapter;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by garagu.
 */
public class CharacterListFragment extends BaseFragment implements CharacterListView, SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    @Inject
    ImageLoader imageLoader;
    @Inject
    CharacterListPresenter presenter;
    @Inject
    Navigator navigator;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private SearchView searchView;
    private boolean searchExecuted;
    private final OnCardClickListener onCardClickListener = new OnCardClickListener() {
        @Override
        public void onThumbnailClick(@NonNull View view, @NonNull CharacterViewModel character) {
            presenter.onThumbnailClick(character.isThumbnailAvailable() ? view : null, character, searchExecuted);
        }
    };
    private RVAnimRendererAdapter<CharacterViewModel> adapter;
    private boolean hasMore;
    private int offset;

    public static CharacterListFragment newInstance() {
        return new CharacterListFragment();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        final MenuItem searchItem = menu.findItem(R.id.item_search);
        MenuItemCompat.setOnActionExpandListener(searchItem, this);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.characterslist_hint_search));
        searchView.setOnQueryTextListener(this);
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

    private void initComponents() {
        initToolbar(R.string.home_characters);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final ItemDecoration itemDecoration = new CardDecoration(getActivity());
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addOnScrollListener(new OnListBottomReachedListener(layoutManager) {
            @Override
            public void onBottomReached() {
                if (isNotLoading() && hasMore) {
                    presenter.onListScrolled(searchView != null ? searchView.getQuery().toString() : "", offset);
                }
            }
        });
        final Renderer<CharacterViewModel> renderer = new CharacterRenderer(imageLoader, onCardClickListener);
        final RendererBuilder<CharacterViewModel> rendererBuilder = new RendererBuilder<>(renderer);
        final AdapteeCollection<CharacterViewModel> emptyList = new ListAdapteeCollection<>(new ArrayList<>());
        adapter = new RVAnimRendererAdapter<>(rendererBuilder, emptyList);
        recyclerView.setAdapter(adapter);
    }

    private void initDependencyInjection() {
        getComponent(CharacterComponent.class).inject(this);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
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

    private void updateList(@NonNull List<CharacterViewModel> characters) {
        final int positionStart = adapter.getItemCount();
        adapter.addAll(characters);
        adapter.notifyItemRangeChanged(positionStart, adapter.getItemCount());
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
    public void openDetail(@Nullable View clickedView, @NonNull CharacterViewModel character) {
        navigator.openDetail(this, clickedView, character);
    }

    @Override
    public void showCharacters(@NonNull PaginatedListViewModel<CharacterViewModel> paginatedListOfCharacters) {
        hasMore = paginatedListOfCharacters.hasMore();
        offset = paginatedListOfCharacters.getOffset();
        updateList(paginatedListOfCharacters.getItems());
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