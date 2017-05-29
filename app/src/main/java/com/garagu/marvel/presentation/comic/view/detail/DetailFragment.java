package com.garagu.marvel.presentation.comic.view.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.presentation.comic.di.DaggerComicComponent;
import com.garagu.marvel.presentation.common.BaseFragment;
import com.garagu.marvel.presentation.common.ImageLoader;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;

/**
 * Created by garagu.
 */
public class DetailFragment extends BaseFragment {

    private static final String KEY_COMIC = "comic";

    @Inject
    ImageLoader imageLoader;

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
    @BindView(R.id.rv_credits)
    RecyclerView rvCredits;
    @BindView(R.id.rv_characters)
    RecyclerView rvCharacters;

    @BindDimen(R.dimen.margin_small)
    int space;

    private Comic selectedComic;

    public static DetailFragment newInstance(Comic selectedComic) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_COMIC, selectedComic);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedComic = getArguments().getParcelable(KEY_COMIC);
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        initDependencyInjector();
        initComicInfo();
    }

    private void initDependencyInjector() {
        DaggerComicComponent.builder()
                .netComponent(getNetComponent())
                .build()
                .inject(this);
    }

    private void initComicInfo() {
        imageLoader.load(imgThumbnail, selectedComic.getUrlThumbnail());
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
        initCredits();
        initCharacters();
    }

    private void initCredits() {
        CreatorAdapter adapter = new CreatorAdapter(selectedComic.getCreators());
        initRecyclerView(rvCredits, adapter);
    }

    private void initCharacters() {
        CharacterAdapter adapter = new CharacterAdapter(selectedComic.getCharacters());
        initRecyclerView(rvCharacters, adapter);
    }

    private void initRecyclerView(RecyclerView recyclerView, Adapter adapter) {
        LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        ItemDecoration itemDecoration = new GridItemDecoration(space);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

}
