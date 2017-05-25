package com.garagu.marvel.presentation.comic.view.list;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.widget.Toast;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.presentation.comic.di.ComicModule;
import com.garagu.marvel.presentation.comic.di.DaggerComicComponent;
import com.garagu.marvel.presentation.comic.view.list.ListPresenter.ListView;
import com.garagu.marvel.presentation.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by garagu.
 */
public class ListFragment extends BaseFragment implements ListView {

    @Inject
    ListPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ComicAdapter adapter;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void onCreateView() {
        super.onCreateView();
        initDependencyInjector();
        initList();
        initPresenter();
    }

    private void initDependencyInjector() {
        DaggerComicComponent.builder()
                .appComponent(getAppComponent())
                .comicModule(new ComicModule())
                .build()
                .inject(this);
    }

    private void initPresenter() {
        presenter.setView(this);
        //presenter.init();
    }

    private void initList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new ComicAdapter(getMock(), comic -> presenter.onComicClicked(comic));
        recyclerView.setAdapter(adapter);
    }

    private List<Comic> getMock() {
        List<Comic> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Comic comic = new Comic.Builder()
                    .withTitle("Title " + i)
                    .withSeriesTitle("Series title (0000)")
                    .withUrlThumbnail(i == 0 ? "http://i.annihil.us/u/prod/marvel/i/mg/6/50/58d97d2b532a0/portrait_medium.jpg" : "test")
                    .build();
            list.add(comic);
        }
        return list;
    }

    @Override
    public void openDetail(Comic comic) {
        // TODO
        Toast.makeText(getActivity(), comic.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComics(List<Comic> comics) {
        // TODO
    }

    @Override
    public void showError(String message) {
        // TODO
    }

}