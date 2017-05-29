package com.garagu.marvel.data.mapper;

import android.os.Build;
import android.text.Html;

import com.garagu.marvel.data.entity.CharacterEntity;
import com.garagu.marvel.data.entity.CharacterListEntity;
import com.garagu.marvel.data.entity.ComicEntity;
import com.garagu.marvel.data.entity.ComicListEntity;
import com.garagu.marvel.data.entity.CreatorEntity;
import com.garagu.marvel.data.entity.CreatorListEntity;
import com.garagu.marvel.data.entity.ImageEntity;
import com.garagu.marvel.data.entity.SeriesEntity;
import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.domain.model.ComicCreator;
import com.garagu.marvel.domain.model.PaginatedList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class ComicMapper {

    public PaginatedList<Comic> mapEntityToModel(ComicListEntity entity) {
        List<Comic> items = mapComicList(entity.getResults());
        int offset = entity.getOffset() + entity.getCount();
        return new PaginatedList.Builder<Comic>()
                .withHasMore(offset < entity.getTotal())
                .withOffset(offset)
                .withItems(items)
                .build();
    }

    private List<Comic> mapComicList(ComicEntity[] entity) {
        List<Comic> model = new ArrayList<>();
        for (ComicEntity entityItem : entity) {
            Comic modelItem = mapComic(entityItem);
            model.add(modelItem);
        }
        return model;
    }

    private Comic mapComic(ComicEntity entity) {
        String description = mapDescription(entity.getDescription());
        String series = mapSeries(entity.getSeries());
        List<ComicCreator> creators = mapCreators(entity.getCreators());
        List<String> characters = mapCharacters(entity.getCharacters());
        String thumbnail = mapThumbnail(entity.getThumbnail());
        return new Comic.Builder()
                .withTitle(entity.getTitle())
                .withDescription(description)
                .withPages(String.valueOf(entity.getPageCount()))
                .withSeriesTitle(series)
                .withCreators(creators)
                .withCharacters(characters)
                .withUrlThumbnail(thumbnail)
                .withFormat(entity.getFormat())
                .withIsbn(entity.getIsbn())
                .build();
    }

    private String mapDescription(String description) {
        if ((description != null) && !description.isEmpty()) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                return Html.fromHtml(description).toString();
            } else {
                return Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY).toString();
            }
        }
        return "";
    }

    private String mapSeries(SeriesEntity entity) {
        return (entity != null) ? entity.getName() : null;
    }

    private List<ComicCreator> mapCreators(CreatorListEntity entity) {
        List<ComicCreator> items = new ArrayList<>();
        if (entity.getItems() != null) {
            for (CreatorEntity item : entity.getItems()) {
                String formattedRole = mapRole(item.getRole());
                ComicCreator comicCreator = new ComicCreator.Builder()
                        .withName(item.getName())
                        .withRole(formattedRole)
                        .build();
                items.add(comicCreator);
            }
        }
        return items;
    }

    private String mapRole(String role) {
        if ((role != null) && (role.length() > 1)) {
            return Character.toUpperCase(role.charAt(0)) + role.substring(1);
        }
        return role;
    }

    private List<String> mapCharacters(CharacterListEntity entity) {
        List<String> items = new ArrayList<>();
        if (entity.getItems() != null) {
            for (CharacterEntity item : entity.getItems()) {
                items.add(item.getName());
            }
        }
        return items;
    }

    private String mapThumbnail(ImageEntity entity) {
        if (entity.getPath().contains("image_not_available")) {
            return null;
        }
        return entity.getPath() + "/portrait_xlarge." + entity.getExtension();
    }

}