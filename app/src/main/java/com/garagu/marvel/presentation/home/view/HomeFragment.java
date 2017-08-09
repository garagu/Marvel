package com.garagu.marvel.presentation.home.view;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.common.view.CardDecoration;
import com.garagu.marvel.presentation.common.view.RVRenderer.OnRendererClickListener;
import com.garagu.marvel.presentation.home.di.HomeComponent;
import com.garagu.marvel.presentation.home.model.HomeOptionType;
import com.garagu.marvel.presentation.home.model.HomeOptionViewModel;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;

/**
 * Created by garagu.
 */
public class HomeFragment extends BaseFragment {

    @Inject
    Navigator navigator;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindDimen(R.dimen.margin_medium)
    int margin;

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
    }

    private void initDependencyInjection() {
        getComponent(HomeComponent.class).inject(this);
    }

    private void initComponents() {
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        final LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final List<HomeOptionViewModel> items = new ArrayList<>();
        items.add(new HomeOptionViewModel(HomeOptionType.CHARACTERS, R.mipmap.iron_man, R.string.home_characters, Gravity.BOTTOM));
        items.add(new HomeOptionViewModel(HomeOptionType.COMICS, R.mipmap.ant_man, R.string.home_comics, Gravity.BOTTOM|Gravity.END));
        items.add(new HomeOptionViewModel(HomeOptionType.FAVORITES, R.mipmap.vision, R.string.home_favorites, Gravity.BOTTOM));
        items.add(new HomeOptionViewModel(HomeOptionType.REVIEWS, R.mipmap.captain_america, R.string.home_reviews, Gravity.BOTTOM|Gravity.END));

        final ItemDecoration itemDecoration = new CardDecoration(margin);
        recyclerView.addItemDecoration(itemDecoration);
        final Renderer<HomeOptionViewModel> renderer = new HomeOptionRenderer(
                getOnHomeOptionClickListener(),
                getCellHeight(items.size(), margin)
        );
        final RendererBuilder<HomeOptionViewModel> rendererBuilder = new RendererBuilder<>(renderer);
        final AdapteeCollection<HomeOptionViewModel> collection = new ListAdapteeCollection<>(items);
        final RVRendererAdapter adapter = new RVRendererAdapter<>(rendererBuilder, collection);
        recyclerView.setAdapter(adapter);
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

    private OnRendererClickListener<HomeOptionViewModel> getOnHomeOptionClickListener() {
        return homeOption -> {
            switch (homeOption.getType()) {
                case HomeOptionType.CHARACTERS:
                    navigator.openCharacters(getActivity());
                    break;
                case HomeOptionType.COMICS:
                    navigator.openComics(getActivity());
                    break;
                case HomeOptionType.FAVORITES:
                case HomeOptionType.REVIEWS:
                    Toast.makeText(getActivity(), R.string.message_next_version, Toast.LENGTH_SHORT).show();
            }
        };
    }

}