package com.garagu.marvel.presentation.character.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.character.Character;
import com.garagu.marvel.domain.model.common.PaginatedList;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class CharacterModelMapper {

    @NonNull
    public PaginatedListViewModel<CharacterViewModel> mapModelToViewModel(PaginatedList<Character> model) {
        final int offset = model.getOffset() + model.getCount();
        final List<CharacterViewModel> characters = mapCharacters(model.getList());
        return new PaginatedListViewModel.Builder<CharacterViewModel>()
                .withHasMore(offset < model.getTotal())
                .withItems(characters)
                .withOffset(offset)
                .build();
    }

    @NonNull
    private List<CharacterViewModel> mapCharacters(List<Character> modelList) {
        final List<CharacterViewModel> viewModelList = new ArrayList<>();
        // TODO
        for (Character modelItem : modelList) {
            final CharacterViewModel viewModelItem = new CharacterViewModel.Builder()
                    //.withComics()
                    .withDescription(modelItem.getDescription())
                    //.withEvents()
                    .withId(modelItem.getId())
                    .withName(modelItem.getName())
                    //.withSeries()
                    //.withStories()
                    //.withUrls()
                    //.withUrlThumbnail()
                    .build();
            viewModelList.add(viewModelItem);
        }
        return viewModelList;
    }

}
