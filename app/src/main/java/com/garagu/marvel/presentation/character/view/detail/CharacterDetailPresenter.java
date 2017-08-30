package com.garagu.marvel.presentation.character.view.detail;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.model.favorite.Favorite;
import com.garagu.marvel.domain.usecase.AddFavorite;
import com.garagu.marvel.domain.usecase.DeleteFavorite;
import com.garagu.marvel.domain.usecase.GetUser;
import com.garagu.marvel.domain.usecase.IsFavorite;
import com.garagu.marvel.presentation.character.model.CharacterViewModel;
import com.garagu.marvel.presentation.character.model.LinkViewModel;
import com.garagu.marvel.presentation.character.view.detail.CharacterDetailPresenter.CharacterDetailView;
import com.garagu.marvel.presentation.common.model.CollectionViewModel;
import com.garagu.marvel.presentation.common.model.FavoriteType;
import com.garagu.marvel.presentation.common.view.BasePresenter;
import com.garagu.marvel.presentation.common.view.BaseView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by garagu.
 */
public class CharacterDetailPresenter extends BasePresenter<CharacterDetailView> {

    private final GetUser getUser;
    private final AddFavorite addFavorite;
    private final DeleteFavorite deleteFavorite;
    private final IsFavorite isFavorite;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public CharacterDetailPresenter(GetUser getUser, AddFavorite addFavorite, DeleteFavorite deleteFavorite, IsFavorite isFavorite) {
        this.getUser = getUser;
        this.addFavorite = addFavorite;
        this.deleteFavorite = deleteFavorite;
        this.isFavorite = isFavorite;
    }

    @Override
    public void subscribe() {
        // do nothing
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    void onInitView(@NonNull CharacterViewModel character) {
        initCharacterInfo(character);
        getUser.execute(null)
                .map(user -> new IsFavorite.InputParam(user.getId(), character.getId(), FavoriteType.CHARACTER))
                .flatMap(isFavorite::execute)
                .subscribe(
                        isFavorite -> getView().showFab(isFavorite),
                        error -> { // do nothing
                        },
                        () -> { // do nothing
                        },
                        compositeDisposable::add
                );
    }

    void onFavoriteClick(@NonNull FloatingActionButton fab, @NonNull CharacterViewModel character) {
        if (isMarkedAsFavorite(fab)) {
            deleteFavorite(character.getId());
        } else {
            addFavorite(character.getId(), character.getName(), character.getUrlThumbnail());
        }
    }

    void onMoreComicsClick() {
        getView().openComics();
    }

    private void addFavorite(int characterId, String characterName, String urlThumbnail) {
        getUser.execute(null)
                .map(user -> new Favorite(user.getId(), characterId, characterName, urlThumbnail, FavoriteType.CHARACTER))
                .flatMap(addFavorite::execute)
                .subscribe(
                        success -> getView().showFab(true),
                        error -> getView().showError(R.string.error_generic),
                        () -> { // do nothing
                        },
                        compositeDisposable::add
                );
    }

    private void deleteFavorite(int characterId) {
        getUser.execute(null)
                .map(user -> new DeleteFavorite.InputParam(user.getId(), characterId, FavoriteType.CHARACTER))
                .flatMap(deleteFavorite::execute)
                .subscribe(
                        success -> getView().showFab(false),
                        error -> getView().showError(R.string.error_generic),
                        () -> { // do nothing
                        },
                        compositeDisposable::add
                );
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

    private void initCharacterInfo(@NonNull CharacterViewModel character) {
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

    private boolean isMarkedAsFavorite(@NonNull FloatingActionButton fab) {
        final Drawable favoriteDrawable = ContextCompat.getDrawable(fab.getContext(), R.drawable.ic_favorite_on);
        return (favoriteDrawable.getConstantState() == fab.getDrawable().getConstantState());
    }

    interface CharacterDetailView extends BaseView {
        void hideComics();

        void hideImage();

        void hideLinks();

        void loadImage(@NonNull String url);

        void openComics();

        void showComics(@NonNull String comics);

        void showDescription(@NonNull String description);

        void showError(@StringRes int errorStringRes);

        void showFab(boolean isFavorite);

        void showLinks(@NonNull Spanned links);

        void showName(@NonNull String name);

        void showUnavailableDescription();
    }

}