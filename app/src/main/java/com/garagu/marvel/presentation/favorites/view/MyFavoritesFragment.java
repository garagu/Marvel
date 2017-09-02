package com.garagu.marvel.presentation.favorites.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.common.model.FavoriteViewModel;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.common.view.CardDecoration;
import com.garagu.marvel.presentation.common.view.ImageLoader;
import com.garagu.marvel.presentation.favorites.di.MyFavoritesComponent;
import com.garagu.marvel.presentation.favorites.view.MyFavoritesPresenter.MyFavoritesView;
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
public class MyFavoritesFragment extends BaseFragment implements MyFavoritesView {

    @Inject
    MyFavoritesPresenter presenter;
    @Inject
    ImageLoader imageLoader;
    @Inject
    Navigator navigator;

    @BindView(R.id.layout_empty_data)
    LinearLayout layoutEmptyData;
    @BindView(R.id.img_empty_data)
    ImageView imgEmptyData;
    @BindView(R.id.txt_empty_data)
    TextView txtEmptyData;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RVRendererAdapter<FavoriteViewModel> adapter;

    @NonNull
    public static MyFavoritesFragment newInstance() {
        return new MyFavoritesFragment();
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
    }

    @Override
    public void onResume() {
        super.onResume();
        initPresenter();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unsubscribe();
    }

    private void initDependencyInjection() {
        getComponent(MyFavoritesComponent.class).inject(this);
    }

    private void initComponents() {
        initToolbar(R.string.home_favorites);
        initEmptyWarning();
        initList();
    }

    private void initEmptyWarning() {
        imgEmptyData.setImageResource(R.drawable.logo_favorites);
        txtEmptyData.setText(R.string.favorite_warning_empty);
    }

    private void initList() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final ItemDecoration dividerDecoration = new CardDecoration(getActivity());
        recyclerView.addItemDecoration(dividerDecoration);
        final Renderer<FavoriteViewModel> renderer = new MyFavoriteRenderer(presenter::onFavoriteClick, imageLoader);
        final RendererBuilder<FavoriteViewModel> rendererBuilder = new RendererBuilder<>(renderer);
        final AdapteeCollection<FavoriteViewModel> emptyList = new ListAdapteeCollection<>(new ArrayList<>());
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
    public void showCharacter(@NonNull CharacterViewModel character) {
        navigator.openCharacter(getActivity(), character);
    }

    @Override
    public void showEmptyWarning() {
        recyclerView.setVisibility(View.GONE);
        layoutEmptyData.setVisibility(View.VISIBLE);
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
    public void showFavorites(@NonNull List<FavoriteViewModel> favorites) {
        layoutEmptyData.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.clear();
        adapter.addAll(favorites);
        adapter.notifyDataSetChanged();
    }

}
