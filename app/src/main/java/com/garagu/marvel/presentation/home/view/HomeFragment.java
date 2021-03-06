package com.garagu.marvel.presentation.home.view;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.DisplayMetrics;
import android.view.View;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.application.di.AppComponent;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.common.view.CardDecoration;
import com.garagu.marvel.presentation.common.view.OnLateralMenuItemSelectedListener;
import com.garagu.marvel.presentation.home.model.HomeOptionType;
import com.garagu.marvel.presentation.home.model.HomeOptionViewModel;
import com.garagu.marvel.presentation.home.view.HomePresenter.HomeView;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;

/**
 * Created by garagu.
 */
public class HomeFragment extends BaseFragment implements HomeView, OnLateralMenuItemSelectedListener {

    @Inject
    HomePresenter presenter;
    @Inject
    Navigator navigator;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindDimen(R.dimen.margin_medium)
    int margin;

    @NonNull
    public static HomeFragment newInstance() {
        return new HomeFragment();
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
        getComponent(AppComponent.class).inject(this);
    }

    private void initComponents() {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        final LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final ItemDecoration itemDecoration = new CardDecoration(margin);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void initPresenter() {
        presenter.setView(this);
        presenter.subscribe();
    }

    private int getCellHeight(int listSize, int margin) {
        final DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        final int screenHeight = displayMetrics.heightPixels;

        final TypedArray themeAttributes = getActivity().getTheme().obtainStyledAttributes(new int[]{android.support.v7.appcompat.R.attr.actionBarSize});
        final int toolbarHeight = themeAttributes.getDimensionPixelSize(0, 0);
        themeAttributes.recycle();

        int statusBarHeight = 0;
        final int resourceId = getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getActivity().getResources().getDimensionPixelSize(resourceId);
        }

        final int heightAvailable = screenHeight - toolbarHeight - statusBarHeight - 2 * margin;
        return (heightAvailable / listSize) - margin;
    }

    private void onHomeOptionClick(HomeOptionViewModel homeOption) {
        switch (homeOption.getType()) {
            case HomeOptionType.CHARACTERS:
                presenter.onCharactersOptionClick();
                break;
            case HomeOptionType.COMICS:
                presenter.onComicsOptionClick();
                break;
            case HomeOptionType.FAVORITES:
                presenter.onFavoritesOptionClick();
                break;
            case HomeOptionType.REVIEWS:
                presenter.onReviewsOptionClick();
        }
    }

    @Override
    public void onLateralMenuItemSelected(@IdRes int idMenuItem) {
        if (idMenuItem == R.id.nav_sign_out) {
            presenter.onSignOutClick();
        } else if (idMenuItem == R.id.nav_about) {
            presenter.onAboutClick();
        }
    }

    @Override
    public void initLateralMenu(@Nullable String name, @Nullable String email) {
        initNavigationView(name, email, this);
    }

    @Override
    public void openCharacters() {
        navigator.openCharacters(getActivity());
    }

    @Override
    public void openComics() {
        navigator.openComics(getActivity());
    }

    @Override
    public void openMyFavorites() {
        navigator.openMyFavorites(getActivity());
    }

    @Override
    public void openMyReviews() {
        navigator.openMyReviews(getActivity());
    }

    @Override
    public void openSignIn() {
        navigator.openSignIn(getActivity());
    }

    @Override
    public void showAbout(@StringRes int title, @StringRes int message) {
        showAlertDialog(title, message);
    }

    @Override
    public void showError(@NonNull String message) {
        showSnackbar(message);
    }

    @Override
    public void showHomeOptions(@NonNull List<HomeOptionViewModel> homeOptions) {
        final Renderer<HomeOptionViewModel> renderer = new HomeOptionRenderer(this::onHomeOptionClick, getCellHeight(homeOptions.size(), margin));
        final RendererBuilder<HomeOptionViewModel> rendererBuilder = new RendererBuilder<>(renderer);
        final AdapteeCollection<HomeOptionViewModel> collection = new ListAdapteeCollection<>(homeOptions);
        final RVRendererAdapter adapter = new RVRendererAdapter<>(rendererBuilder, collection);
        recyclerView.setAdapter(adapter);
    }

}