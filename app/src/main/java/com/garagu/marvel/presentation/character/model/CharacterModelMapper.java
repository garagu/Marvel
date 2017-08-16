package com.garagu.marvel.presentation.character.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.character.Character;
import com.garagu.marvel.domain.model.common.Collection;
import com.garagu.marvel.domain.model.common.Link;
import com.garagu.marvel.domain.model.common.PaginatedList;
import com.garagu.marvel.presentation.common.model.CollectionViewModel;
import com.garagu.marvel.presentation.common.model.LinkViewModel;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class CharacterModelMapper {

    @NonNull
    public PaginatedListViewModel<CharacterViewModel> mapModelToViewModel(@NonNull PaginatedList<Character> model) {
        final int offset = model.getOffset() + model.getCount();
        final List<CharacterViewModel> characters = mapCharacters(model.getList());
        return new PaginatedListViewModel.Builder<CharacterViewModel>()
                .withHasMore(offset < model.getTotal())
                .withItems(characters)
                .withOffset(offset)
                .build();
    }

    @NonNull
    private List<CharacterViewModel> mapCharacters(@NonNull List<Character> modelList) {
        final List<CharacterViewModel> viewModelList = new ArrayList<>();
        for (Character modelItem : modelList) {
            final CollectionViewModel comics = mapCollectionModelToViewModel(modelItem.getComics());
            final List<LinkViewModel> links = mapLinkModelToViewModel(modelItem.getLinks());
            final CharacterViewModel viewModelItem = new CharacterViewModel.Builder()
                    .withComics(comics)
                    .withDescription(modelItem.getDescription())
                    //.withEvents()
                    .withId(modelItem.getId())
                    .withName(modelItem.getName())
                    //.withSeries()
                    //.withStories()
                    .withLinks(links)
                    .withUrlThumbnail(modelItem.getUrlThumbnail())
                    .build();
            viewModelList.add(viewModelItem);
        }
        return viewModelList;
    }

    @NonNull
    private CollectionViewModel mapCollectionModelToViewModel(@NonNull Collection model) {
        final List<String> firstItems = mapFirstItems(model.getFirstItems());
        final String totalNumber = String.valueOf(model.getTotalNumber());
        return new CollectionViewModel.Builder()
                .withFirstItems(firstItems)
                .withTotalNumber(totalNumber)
                .withUrl(model.getUrl())
                .build();
    }

    @NonNull
    private List<String> mapFirstItems(@NonNull List<Link> modelList) {
        final List<String> viewModelList = new ArrayList<>();
        for (Link model : modelList) {
            viewModelList.add(model.getName());
        }
        return viewModelList;
    }

    @NonNull
    private List<LinkViewModel> mapLinkModelToViewModel(@NonNull List<Link> modeList) {
        final List<LinkViewModel> viewModelList = new ArrayList<>();
        for (Link model : modeList) {
            final String name = model.getName().length() < 2
                    ? model.getName().toUpperCase()
                    : model.getName().substring(0, 1).toUpperCase() + model.getName().substring(1);
            final LinkViewModel viewModel = new LinkViewModel.Builder()
                    .withName(name)
                    .withUrl(model.getUrl())
                    .build();
            viewModelList.add(viewModel);
        }
        return viewModelList;
    }

}
