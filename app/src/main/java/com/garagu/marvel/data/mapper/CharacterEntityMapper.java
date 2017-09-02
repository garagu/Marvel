package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.entity.character.CharacterEntity;
import com.garagu.marvel.data.entity.character.CharacterListEntity;
import com.garagu.marvel.data.entity.character.LinkEntity;
import com.garagu.marvel.data.entity.common.DefaultCollectionEntity;
import com.garagu.marvel.data.entity.common.ImageEntity;
import com.garagu.marvel.data.entity.common.NameEntity;
import com.garagu.marvel.data.entity.common.ResultEntity;
import com.garagu.marvel.domain.model.character.Character;
import com.garagu.marvel.domain.model.character.Link;
import com.garagu.marvel.domain.model.character.PaginatedCharacterList;
import com.garagu.marvel.domain.model.common.Collection;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class CharacterEntityMapper {

    @Inject
    public CharacterEntityMapper() {
    }

    @NonNull
    public PaginatedCharacterList listEntityToModel(ResultEntity<CharacterListEntity> entity) {
        final List<Character> characters = mapCharacters(entity.getData().getResults());
        return new PaginatedCharacterList.Builder()
                .withCount(entity.getData().getCount())
                .withList(characters)
                .withOffset(entity.getData().getOffset())
                .withTotal(entity.getData().getTotal())
                .build();
    }

    @NonNull
    private List<Character> mapCharacters(CharacterEntity[] entityArray) {
        final List<Character> modelList = new ArrayList<>();
        for (CharacterEntity entityItem : entityArray) {
            final Character modelItem = simpleEntityToModel(entityItem);
            modelList.add(modelItem);
        }
        return modelList;
    }

    @NonNull
    public Character simpleEntityToModel(CharacterEntity entity) {
        final Collection comics = mapCollection(entity.getComics());
        final Collection events = mapCollection(entity.getEvents());
        final Collection series = mapCollection(entity.getSeries());
        final Collection stories = mapCollection(entity.getStories());
        final List<Link> links = mapLinks(entity.getLinks());
        final String urlThumbnail = mapUrlThumbnail(entity.getThumbnail());
        return new Character.Builder()
                .withComicsList(comics)
                .withDescription(entity.getDescription() != null ? entity.getDescription() : "")
                .withEventsList(events)
                .withId(entity.getId())
                .withName(entity.getName() != null ? entity.getName() : "")
                .withSeriesList(series)
                .withStoriesList(stories)
                .withLinks(links)
                .withUrlThumbnail(urlThumbnail)
                .build();
    }

    @NonNull
    private Collection mapCollection(DefaultCollectionEntity entity) {
        final List<Link> firstItems = mapFirstItemsOfCollection(entity.getItems());
        return new Collection.Builder()
                .withFirstItems(firstItems)
                .withTotalNumber(entity.getAvailable())
                .withUrl(entity.getCollectionURI())
                .build();
    }

    @NonNull
    private List<Link> mapFirstItemsOfCollection(NameEntity[] entityArray) {
        final List<Link> modelList = new ArrayList<>();
        for (NameEntity entityItem : entityArray) {
            final Link modelItem = new Link.Builder()
                    .withName(entityItem.getName() != null ? entityItem.getName() : "")
                    .withUrl(entityItem.getResourceURI() != null ? entityItem.getResourceURI() : "")
                    .build();
            modelList.add(modelItem);
        }
        return modelList;
    }

    @NonNull
    private List<Link> mapLinks(LinkEntity[] entityArray) {
        final List<Link> modelList = new ArrayList<>();
        for (LinkEntity entityItem : entityArray) {
            final Link modelItem = new Link.Builder()
                    .withName(entityItem.getType() != null ? entityItem.getType() : "")
                    .withUrl(entityItem.getUrl() != null ? entityItem.getUrl() : "")
                    .build();
            modelList.add(modelItem);
        }
        return modelList;
    }

    @NonNull
    private String mapUrlThumbnail(ImageEntity entity) {
        return entity.getPath().contains("image_not_available")
                ? ""
                : entity.getPath() + "/landscape_incredible." + entity.getExtension();
    }

}