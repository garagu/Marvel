package com.garagu.marvel.presentation.character.view.list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.widget.ProgressBar;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.character.di.CharacterComponent;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.character.view.list.CharacterListPresenter.CharacterListView;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.common.view.CardDecoration;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by garagu.
 */
public class CharacterListFragment extends BaseFragment implements CharacterListView {

    @Inject
    CharacterListPresenter presenter;
    @Inject
    Picasso picasso;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RVRendererAdapter<CharacterViewModel> adapter;

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
        initView();
        initPresenter();
    }

    @Override
    public void onDestroyView() {
        presenter.unsubscribe();
        super.onDestroyView();
    }

    private void initView() {
        final LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final ItemDecoration itemDecoration = new CardDecoration(getActivity());
        recyclerView.addItemDecoration(itemDecoration);
        final Renderer<CharacterViewModel> renderer = new CharacterRenderer(character -> {
            // TODO
        }, picasso);
        final RendererBuilder<CharacterViewModel> rendererBuilder = new RendererBuilder<>(renderer);
        final AdapteeCollection<CharacterViewModel> emptyList = new ListAdapteeCollection<>(new ArrayList<>());
        adapter = new RVRendererAdapter<>(rendererBuilder, emptyList);
        recyclerView.setAdapter(adapter);
    }

    private void initDependencyInjection() {
        getComponent(CharacterComponent.class).inject(this);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showCharacters(PaginatedListViewModel<CharacterViewModel> paginatedListOfCharacters) {
        adapter.addAll(paginatedListOfCharacters.getItems());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showProgress() {

    }
}