package com.garagu.marvel.presentation.character.view;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.character.di.CharacterComponent;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.character.view.CharacterListPresenter.CharacterListView;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;
import com.garagu.marvel.presentation.common.view.BaseFragment;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class CharacterListFragment extends BaseFragment implements CharacterListView {

    @Inject
    CharacterListPresenter presenter;

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
        initPresenter();
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
        for (CharacterViewModel character : paginatedListOfCharacters.getItems()) {
            System.out.println(character.getName());
        }
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showProgress() {

    }
}