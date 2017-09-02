package com.garagu.marvel.presentation.character.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.character.Character;
import com.garagu.marvel.domain.model.character.Link;
import com.garagu.marvel.domain.model.common.Collection;
import com.garagu.marvel.domain.model.common.PaginatedList;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.common.model.CollectionViewModel;
import com.garagu.marvel.presentation.common.model.ModelToViewModelMapper;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class CharacterModelMapper extends ModelToViewModelMapper<Character, CharacterViewModel> {

    private final LinkModelToViewModelMapper linkMapper;

    @Inject
    public CharacterModelMapper(LinkModelToViewModelMapper linkMapper) {
        this.linkMapper = linkMapper;
    }

    @NonNull
    @Override
    public CharacterViewModel simpleModelToViewModel(@NonNull Character model) {
        final CollectionViewModel comics = mapCollectionModelToViewModel(model.getComics());
        final List<LinkViewModel> links = linkMapper.listModelToViewModel(model.getLinks());
        return new CharacterViewModel.Builder()
                .withComics(comics)
                .withDescription(model.getDescription())
                .withId(model.getId())
                .withName(model.getName())
                .withLinks(links)
                .withUrlThumbnail(model.getUrlThumbnail())
                .build();
    }

    @NonNull
    public PaginatedListViewModel<CharacterViewModel> paginatedListModelToViewModel(@NonNull PaginatedList<Character> model) {
        final int offset = model.getOffset() + model.getCount();
        final List<CharacterViewModel> characters = listModelToViewModel(model.getList());
        return new PaginatedListViewModel.Builder<CharacterViewModel>()
                .withHasMore(offset < model.getTotal())
                .withItems(characters)
                .withOffset(offset)
                .build();
    }

    @NonNull
    private CollectionViewModel mapCollectionModelToViewModel(@NonNull Collection model) {
        final List<String> firstItems = linkMapper.listLinkNames(model.getFirstItems());
        final String totalNumber = String.valueOf(model.getTotalNumber());
        return new CollectionViewModel.Builder()
                .withFirstItems(firstItems)
                .withTotalNumber(totalNumber)
                .withUrl(model.getUrl())
                .build();
    }

}