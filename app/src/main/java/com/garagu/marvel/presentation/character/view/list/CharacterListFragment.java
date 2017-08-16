package com.garagu.marvel.presentation.character.view.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
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
public class CharacterListFragment extends BaseFragment implements CharacterListView {

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

    private RVAnimRendererAdapter<CharacterViewModel> adapter;
    private boolean hasMore;
    private int offset;
    private final OnCardClickListener onCardClickListener = new OnCardClickListener() {
        @Override
        public void onFavoriteClick(@NonNull CharacterViewModel character) {
            // TODO
        }

        @Override
        public void onThumbnailClick(@NonNull View view, @NonNull CharacterViewModel character) {
            presenter.onThumbnailClicked(character.isThumbnailAvailable() ? view : null, character);
        }
    };

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

    private void initComponents() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final ItemDecoration itemDecoration = new CardDecoration(getActivity());
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (isNotLoading() && hasMore && checkScroll(layoutManager, dy)) {
                    presenter.onListScrolled(offset);
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

    private void updateList(@NonNull List<CharacterViewModel> comics) {
        final int positionStart = adapter.getItemCount();
        adapter.addAll(comics);
        adapter.notifyItemRangeChanged(positionStart, adapter.getItemCount());
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