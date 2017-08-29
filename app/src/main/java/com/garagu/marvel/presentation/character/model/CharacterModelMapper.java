package com.garagu.marvel.presentation.character.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.character.Character;
import com.garagu.marvel.domain.model.character.Link;
import com.garagu.marvel.domain.model.common.Collection;
import com.garagu.marvel.domain.model.common.PaginatedList;
import com.garagu.marvel.presentation.common.model.CollectionViewModel;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class CharacterModelMapper {

    @NonNull
    public PaginatedListViewModel<CharacterViewModel> listModelToViewModel(@NonNull PaginatedList<Character> model) {
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
        for (Character model : modelList) {
            final CharacterViewModel viewModel = simpleModelToViewModel(model);
            viewModelList.add(viewModel);
        }
        return viewModelList;
    }

    @NonNull
    public CharacterViewModel simpleModelToViewModel(Character model) {
        final CollectionViewModel comics = mapCollectionModelToViewModel(model.getComics());
        final List<LinkViewModel> links = mapLinkModelToViewModel(model.getLinks());
        return new CharacterViewModel.Builder()
                .withComics(comics)
                .withDescription(model.getDescription())
                //.withEvents()
                .withId(model.getId())
                .withName(model.getName())
                //.withSeries()
                //.withStories()
                .withLinks(links)
                .withUrlThumbnail(model.getUrlThumbnail())
                .build();
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
