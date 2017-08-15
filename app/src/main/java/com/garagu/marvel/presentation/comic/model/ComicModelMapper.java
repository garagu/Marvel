package com.garagu.marvel.presentation.comic.model;

import android.os.Build;
import android.text.Html;

import com.garagu.marvel.domain.model.comic.Comic;
import com.garagu.marvel.domain.model.comic.ComicCharacter;
import com.garagu.marvel.domain.model.comic.ComicCreator;
import com.garagu.marvel.domain.model.comic.ComicList;
import com.garagu.marvel.domain.model.comic.ComicSeries;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public class ComicModelMapper {

    public PaginatedListViewModel<ComicViewModel> mapModelToViewModel(ComicList model) {
        final List<ComicViewModel> items = mapList(model.getList());
        final int offset = model.getOffset() + model.getCount();
        return new PaginatedListViewModel.Builder<ComicViewModel>()
                .withHasMore(offset < model.getTotal())
                .withOffset(offset)
                .withItems(items)
                .build();
    }

    private List<ComicViewModel> mapList(List<Comic> model) {
        final List<ComicViewModel> list = new ArrayList<>();
        for (Comic comic : model) {
            final List<String> characters = mapCharacters(comic.getCharacters());
            final List<CreatorViewModel> creators = mapCreators(comic.getCreators());
            final String description = mapDescription(comic.getDescription());
            final String pages = String.valueOf(comic.getPages());
            final String seriesTitle = mapSeriesTitle(comic.getSeries());
            final ComicViewModel viewModel = new ComicViewModel.Builder()
                    .withCharacters(characters)
                    .withCreators(creators)
                    .withDescription(description)
                    .withFormat(comic.getFormat())
                    .withId(String.valueOf(comic.getId()))
                    .withIsbn(comic.getIsbn())
                    .withPages(pages)
                    .withSeriesTitle(seriesTitle)
                    .withTitle(comic.getTitle())
                    .withUrlThumbnail(comic.getUrlThumbnail())
                    .withImages(comic.getImages())
                    .build();
            list.add(viewModel);
        }
        return list;
    }

    private List<String> mapCharacters(List<ComicCharacter> model) {
        final List<String> items = new ArrayList<>();
        for (ComicCharacter item : model) {
            final String name = item.getName();
            items.add(name);
        }
        return items;
    }

    private List<CreatorViewModel> mapCreators(List<ComicCreator> model) {
        final List<CreatorViewModel> items = new ArrayList<>();
        for (ComicCreator item : model) {
            final String role = mapRole(item.getRole());
            final CreatorViewModel viewModel = new CreatorViewModel.Builder()
                    .withName(item.getName())
                    .withRole(role)
                    .build();
            items.add(viewModel);
        }
        return items;
    }

    private String mapRole(String role) {
        if ((role != null) && (role.length() > 1)) {
            return Character.toUpperCase(role.charAt(0)) + role.substring(1);
        }
        return role;
    }

    private String mapDescription(String description) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return Html.fromHtml(description).toString();
        } else {
            return Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY).toString();
        }
    }

    private String mapSeriesTitle(ComicSeries model) {
        return model.getName();
    }

}