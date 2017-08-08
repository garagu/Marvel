package com.garagu.marvel.presentation.character.view.detail;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.character.view.detail.CharacterDetailPresenter.CharacterDetailView;
import com.garagu.marvel.presentation.common.model.CollectionViewModel;
import com.garagu.marvel.presentation.common.model.LinkViewModel;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class CharacterDetailPresenter extends BasePresenter<CharacterDetailView> {

    @Inject
    public CharacterDetailPresenter() {
    }

    @Override
    public void subscribe() {
        // do nothing
    }

    @Override
    public void unsubscribe() {
        // do nothing
    }

    void onInitView(@NonNull CharacterViewModel character) {
        getView().showName(character.getName());
        initThumbnail(character.getUrlThumbnail());
        initDescription(character.getDescription());
        initLinks(character.getLinks());
        initComics(character.getComics());
    }

    private void initComics(@NonNull CollectionViewModel comics) {
        if (comics.getFirstItems().isEmpty()) {
            getView().hideComics();
        } else {
            formatComics(comics.getFirstItems());
        }
    }

    private void initDescription(@NonNull String description) {
        if (TextUtils.isEmpty(description)) {
            getView().showUnavailableDescription();
        } else {
            getView().showDescription(description);
        }
    }

    private void initLinks(@NonNull List<LinkViewModel> links) {
        if (links.isEmpty()) {
            getView().hideLinks();
        } else {
            formatLinks(links);
        }
    }

    private void initThumbnail(@NonNull String urlThumbnail) {
        if (TextUtils.isEmpty(urlThumbnail)) {
            getView().hideImage();
        } else {
            getView().loadImage(urlThumbnail);
        }
    }

    private void formatComics(@NonNull List<String> firstComics) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < firstComics.size(); i++) {
            stringBuilder.append("\u2022 ");
            stringBuilder.append(firstComics.get(i));
            if (i != firstComics.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        getView().showComics(stringBuilder.toString());
    }

    @SuppressWarnings("deprecation")
    private void formatLinks(@NonNull List<LinkViewModel> links) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < links.size(); i++) {
            stringBuilder.append("\u2022 <a href=\"");
            stringBuilder.append(links.get(i).getUrl());
            stringBuilder.append("\">");
            stringBuilder.append(links.get(i).getName());
            stringBuilder.append("</a>");
            if (i != links.size() - 1) {
                stringBuilder.append("<br/>");
            }
        }
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(stringBuilder.toString(), Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(stringBuilder.toString());
        }
        getView().showLinks(result);
    }

    interface CharacterDetailView extends BaseView {
        void hideComics();

        void hideLinks();

        void loadImage(@NonNull String url);

        void showComics(@NonNull String comics);

        void hideImage();

        void showDescription(@NonNull String description);

        void showLinks(@NonNull Spanned links);

        void showName(@NonNull String name);

        void showUnavailableDescription();
    }

}