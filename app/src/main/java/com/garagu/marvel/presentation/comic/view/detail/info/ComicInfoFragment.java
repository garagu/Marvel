package com.garagu.marvel.presentation.comic.view.detail.info;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.comic.di.ComicComponent;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.common.view.BaseFragment;
import com.garagu.marvel.presentation.common.view.ImageLoader;
import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * Created by garagu.
 */
public class ComicInfoFragment extends BaseFragment {

    private static final String KEY_SELECTED_COMIC = "selectedComic";

    @Inject
    ImageLoader imageLoader;

    @BindView(R.id.nested_scroll_view)
    NestedScrollView nestedScrollView;
    @BindView(R.id.img_thumbnail)
    ImageView imgThumbnail;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.layout_summary)
    LinearLayout layoutSummary;
    @BindView(R.id.txt_summary)
    TextView txtSummary;
    @BindView(R.id.txt_series)
    TextView txtSeries;
    @BindView(R.id.txt_pages)
    TextView txtPages;
    @BindView(R.id.txt_format)
    TextView txtFormat;
    @BindView(R.id.txt_isbn)
    TextView txtIsbn;
    @BindView(R.id.rv_dates)
    RecyclerView rvDates;
    @BindView(R.id.rv_credits)
    RecyclerView rvCredits;
    @BindView(R.id.rv_characters)
    RecyclerView rvCharacters;
    @BindDrawable(R.mipmap.placeholder)
    Drawable placeholder;

    @BindDimen(R.dimen.margin_small)
    int space;

    private ComicViewModel selectedComic;

    @NonNull
    public static ComicInfoFragment newInstance(ComicViewModel selectedComic) {
        ComicInfoFragment fragment = new ComicInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_SELECTED_COMIC, selectedComic);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comic_info;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedComic = getArguments().getParcelable(KEY_SELECTED_COMIC);
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        initDependencyInjection();
        initComicInfo();
    }

    private void initDependencyInjection() {
        getComponent(ComicComponent.class).inject(this);
    }

    private void initComicInfo() {
        imageLoader.load(imgThumbnail, selectedComic.getUrlThumbnail(), placeholder);
        txtTitle.setText(selectedComic.getTitle());
        if (!selectedComic.getDescription().isEmpty()) {
            txtSummary.setText(selectedComic.getDescription());
        } else {
            layoutSummary.setVisibility(View.GONE);
        }
        txtSeries.setText(selectedComic.getSeriesTitle());
        txtPages.setText(selectedComic.getPages());
        txtFormat.setText(selectedComic.getFormat());
        txtIsbn.setText(selectedComic.getIsbn());
        initDates();
        initCredits();
        initCharacters();
        nestedScrollView.smoothScrollTo(0, 0);
    }

    private void initDates() {
        initRecyclerView(rvDates, new DateRenderer(), selectedComic.getDates());
    }

    private void initCredits() {
        initRecyclerView(rvCredits, new CreatorRenderer(), selectedComic.getCreators());
    }

    private void initCharacters() {
        initRecyclerView(rvCharacters, new CharacterRenderer(), selectedComic.getCharacters());
    }

    private <T> void initRecyclerView(@NonNull RecyclerView recyclerView, @NonNull Renderer<T> renderer, @NonNull List<T> list) {
        final LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        final ItemDecoration itemDecoration = new GridItemDecoration(space);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setNestedScrollingEnabled(false);
        final RendererBuilder<T> rendererBuilder = new RendererBuilder<>(renderer);
        final AdapteeCollection<T> collection = new ListAdapteeCollection<>(list);
        final RVRendererAdapter<T> adapter = new RVRendererAdapter<>(rendererBuilder, collection);
        recyclerView.setAdapter(adapter);
    }

}
