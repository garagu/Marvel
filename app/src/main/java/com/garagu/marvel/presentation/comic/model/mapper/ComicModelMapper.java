package com.garagu.marvel.presentation.comic.model.mapper;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;

import com.garagu.marvel.domain.model.comic.Comic;
import com.garagu.marvel.domain.model.comic.ComicCharacter;
import com.garagu.marvel.domain.model.comic.ComicSeries;
import com.garagu.marvel.domain.model.comic.PaginatedComicList;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.comic.model.DateViewModel;
import com.garagu.marvel.presentation.comic.model.ComicViewModel;
import com.garagu.marvel.presentation.comic.model.CreatorViewModel;
import com.garagu.marvel.presentation.common.model.ModelToViewModelMapper;
import com.garagu.marvel.presentation.common.model.PaginatedListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class ComicModelMapper extends ModelToViewModelMapper<Comic, ComicViewModel> {

    private final CreatorModelMapper creatorMapper;
    private final DateModelMapper dateMapper;

    @Inject
    public ComicModelMapper(CreatorModelMapper creatorMapper, DateModelMapper dateMapper) {
        this.creatorMapper = creatorMapper;
        this.dateMapper = dateMapper;
    }

    @NonNull
    public PaginatedListViewModel<ComicViewModel> paginatedListModelToViewModel(@NonNull PaginatedComicList model) {
        final List<ComicViewModel> items = listModelToViewModel(model.getList());
        final int offset = model.getOffset() + model.getCount();
        return new PaginatedListViewModel.Builder<ComicViewModel>()
                .withHasMore(offset < model.getTotal())
                .withOffset(offset)
                .withItems(items)
                .build();
    }

    @NonNull
    @Override
    public ComicViewModel simpleModelToViewModel(@NonNull Comic model) {
        final List<String> characters = mapCharacters(model.getCharacters());
        final List<CreatorViewModel> creators = creatorMapper.listModelToViewModel(model.getCreators());
        final String description = mapDescription(model.getDescription());
        final String pages = String.valueOf(model.getPages());
        final String seriesTitle = mapSeriesTitle(model.getSeries());
        final List<DateViewModel> dates = dateMapper.listModelToViewModel(model.getDates());
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

    @SuppressWarnings("deprecation")
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