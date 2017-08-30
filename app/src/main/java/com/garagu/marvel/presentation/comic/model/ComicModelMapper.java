package com.garagu.marvel.presentation.comic.model;

import android.os.Build;
import android.text.Html;

import com.garagu.marvel.domain.model.comic.Comic;
import com.garagu.marvel.domain.model.comic.ComicCharacter;
import com.garagu.marvel.domain.model.comic.ComicCreator;
import com.garagu.marvel.domain.model.comic.ComicDate;
import com.garagu.marvel.domain.model.comic.ComicSeries;
import com.garagu.marvel.domain.model.comic.PaginatedComicList;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class ComicModelMapper {

    @Inject
    public ComicModelMapper() {
    }

    public PaginatedListViewModel<ComicViewModel> listModelToViewModel(PaginatedComicList model) {
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
            final ComicViewModel viewModel = simpleModelToViewModel(comic);
            list.add(viewModel);
        }
        return list;
    }

    public ComicViewModel simpleModelToViewModel(Comic model) {
        final List<String> characters = mapCharacters(model.getCharacters());
        final List<CreatorViewModel> creators = mapCreators(model.getCreators());
        final String description = mapDescription(model.getDescription());
        final String pages = String.valueOf(model.getPages());
        final String seriesTitle = mapSeriesTitle(model.getSeries());
        final List<ComicDateViewModel> dates = mapDates(model.getDates());
        return new ComicViewModel.Builder()
                .withCharacters(characters)
                .withCreators(creators)
                .withDescription(description)
                .withFormat(model.getFormat())
                .withId(model.getId())
                .withIsbn(model.getIsbn())
                .withPages(pages)
                .withSeriesTitle(seriesTitle)
                .withTitle(model.getTitle())
                .withUrlThumbnail(model.getUrlThumbnail())
                .withImages(model.getImages())
                .withDates(dates)
                .build();
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

    private List<ComicDateViewModel> mapDates(List<ComicDate> modelList) {
        final List<ComicDateViewModel> viewModelList = new ArrayList<>();
        for (ComicDate model : modelList) {
            final ComicDateViewModel viewModel = new ComicDateViewModel.Builder()
                    .withDate(model.getDate())
                    .withType(model.getType())
                    .build();
            viewModelList.add(viewModel);
        }
        return viewModelList;
    }

}