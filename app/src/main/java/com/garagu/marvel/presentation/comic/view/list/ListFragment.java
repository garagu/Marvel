package com.garagu.marvel.presentation.comic.view.list;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.widget.Toast;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.Comic;
import com.garagu.marvel.presentation.comic.di.ComicModule;
import com.garagu.marvel.presentation.comic.di.DaggerComicComponent;
import com.garagu.marvel.presentation.comic.view.list.ListPresenter.ListView;
import com.garagu.marvel.presentation.common.BaseFragment;
import com.garagu.marvel.presentation.common.CardDecoration;

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
    }

    private void initDependencyInjector() {
        DaggerComicComponent.builder()
                .appComponent(getAppComponent())
                .comicModule(new ComicModule())
                .build()
                .inject(this);
    }

    private void initList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ItemDecoration itemDecoration = new CardDecoration(getActivity());
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new ComicAdapter(new ArrayList<>(), comic -> presenter.onComicClicked(comic));
        recyclerView.setAdapter(adapter);
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