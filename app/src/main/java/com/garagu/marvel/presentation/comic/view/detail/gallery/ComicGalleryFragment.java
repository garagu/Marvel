package com.garagu.marvel.presentation.comic.view.detail.gallery;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.DisplayMetrics;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.di.ComicComponent;
import com.garagu.marvel.presentation.comic.view.Navigator;
import com.garagu.marvel.presentation.comic.view.detail.gallery.ComicGalleryPresenter.ComicGalleryView;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.common.view.ImageLoader;
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
public class ComicGalleryFragment extends BaseFragment implements ComicGalleryView {

    private static final String KEY_URLS = "urls";

    @Inject
    Navigator navigator;
    @Inject
    ImageLoader imageLoader;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindDimen(R.dimen.border)
    int border;

    private List<String> urls;

    public static ComicGalleryFragment newInstance(@NonNull List<String> urls) {
        final ComicGalleryFragment fragment = new ComicGalleryFragment();
        final Bundle args = new Bundle();
        args.putStringArrayList(KEY_URLS, new ArrayList<>(urls));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        urls = getArguments().getStringArrayList(KEY_URLS);
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        initDependencyInjection();
        initComponent();
    }

    private void initDependencyInjection() {
        getComponent(ComicComponent.class).inject(this);
    }

    private void initComponent() {
        final LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        final ItemDecoration itemDecoration = new GalleryDecoration(getActivity());
        recyclerView.addItemDecoration(itemDecoration);
        final Renderer<String> renderer = new GalleryRenderer(url -> navigator.openImageDetail(this, url), imageLoader, getCellSize());
        final RendererBuilder<String> rendererBuilder = new RendererBuilder<>(renderer);
        final AdapteeCollection<String> list = new ListAdapteeCollection<>(urls);
        final RVRendererAdapter adapter = new RVRendererAdapter<>(rendererBuilder, list);
        recyclerView.setAdapter(adapter);
    }

    private int getCellSize() {
        final DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return (displayMetrics.widthPixels - (border / 2)) / 2;
    }

}