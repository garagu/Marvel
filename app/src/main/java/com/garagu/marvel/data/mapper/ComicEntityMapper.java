package com.garagu.marvel.data.mapper;

import com.garagu.marvel.data.entity.comic.ComicEntity;
import com.garagu.marvel.data.entity.comic.ComicListEntity;
import com.garagu.marvel.data.entity.comic.CreatorCollectionEntity;
import com.garagu.marvel.data.entity.comic.CreatorEntity;
import com.garagu.marvel.data.entity.common.DefaultCollectionEntity;
import com.garagu.marvel.data.entity.common.ImageEntity;
import com.garagu.marvel.data.entity.common.NameEntity;
import com.garagu.marvel.data.entity.common.ResultEntity;
import com.garagu.marvel.domain.model.comic.Comic;
import com.garagu.marvel.domain.model.comic.ComicCharacter;
import com.garagu.marvel.domain.model.comic.ComicCreator;
import com.garagu.marvel.domain.model.comic.ComicList;
import com.garagu.marvel.domain.model.comic.ComicSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class ComicEntityMapper {

    private static final String IMAGE_NAME_GALLERY = ".";
    private static final String IMAGE_NAME_THUMBNAIL = "/portrait_xlarge.";

    public ComicList mapEntityToModel(ResultEntity<ComicListEntity> entity) {
        final List<Comic> comicList = mapComicList(entity.getData().getResults());
        return new ComicList(
                entity.getData().getCount(),
                entity.getData().getOffset(),
                entity.getData().getTotal(),
                comicList
        );
    }

    private List<Comic> mapComicList(ComicEntity[] entity) {
        final List<Comic> model = new ArrayList<>();
        for (ComicEntity entityItem : entity) {
            final Comic modelItem = mapComic(entityItem);
            model.add(modelItem);
        }
        return model;
    }

    private Comic mapComic(ComicEntity entity) {
        return new Comic(
                entity.getId(),
                entity.getTitle(),
                (entity.getDescription() != null) ? entity.getDescription() : "",
                entity.getPageCount(),
                mapSeries(entity.getSeries()),
                mapCreators(entity.getCreators()),
                mapCharacters(entity.getCharacters()),
                entity.getIsbn(),
                entity.getFormat(),
                mapThumbnail(entity.getThumbnail()),
                mapGallery(entity.getImages())
        );
    }

    private ComicSeries mapSeries(NameEntity entity) {
        return new ComicSeries(
                entity.getResourceURI(),
                entity.getName()
        );
    }

    private List<ComicCreator> mapCreators(CreatorCollectionEntity entity) {
        final List<ComicCreator> items = new ArrayList<>();
        if (entity.getItems() != null) {
            for (CreatorEntity item : entity.getItems()) {
                final ComicCreator comicCreator = new ComicCreator(
                        item.getResourceURI(),
                        item.getName(),
                        item.getRole()
                );
                items.add(comicCreator);
            }
        }
        return items;
    }


    private List<ComicCharacter> mapCharacters(DefaultCollectionEntity entity) {
        final List<ComicCharacter> items = new ArrayList<>();
        if (entity.getItems() != null) {
            for (NameEntity item : entity.getItems()) {
                final ComicCharacter character = new ComicCharacter(
                        item.getResourceURI(),
                        item.getName()
                );
                items.add(character);
            }
        }
        return items;
    }

    private String mapThumbnail(ImageEntity entity) {
        return mapImage(entity, IMAGE_NAME_THUMBNAIL);
    }

    private List<String> mapGallery(ImageEntity[] entityArray) {
        final List<String> modelList = new ArrayList<>();
        for (ImageEntity entity : entityArray) {
            final String url = mapImage(entity, IMAGE_NAME_GALLERY);
            if (url != null) {
                modelList.add(url);
            }
        }
        return modelList;
    }

    private String mapImage(ImageEntity entity, String name) {
        if (entity.getPath().contains("image_not_available")) {
            return null;
        }
        return entity.getPath() + name + entity.getExtension();
    }

}