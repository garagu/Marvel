package com.garagu.marvel.data.datasource.local;

import com.garagu.marvel.data.datasource.FavoriteDatasource;
import com.garagu.marvel.data.entity.common.FavoriteEntity;
import com.garagu.marvel.data.local.FileManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class FavoriteLocalDatasource implements FavoriteDatasource {

    private static final String FILE_NAME = "favorites.json";

    private final FileManager fileManager;
    private final Gson gson;

    public FavoriteLocalDatasource(FileManager fileManager, Gson gson) {
        this.fileManager = fileManager;
        this.gson = gson;
    }

    @Override
    public Observable<List<FavoriteEntity>> getFavorites(String userId) {
        return getAllFavorites().flatMapIterable(list -> list)
                .filter(favorite -> favorite.getUserId().equals(userId))
                .toList()
                .toObservable();
    }

    @Override
    public Observable<Boolean> isFavorite(String userId, int id, int type) {
        return getAllFavorites().flatMapIterable(list -> list)
                .filter(favorite -> favorite.getUserId().equals(userId) && (favorite.getFavoriteId() == id) && (favorite.getType() == type))
                .toList()
                .toObservable()
                .map(list -> !list.isEmpty());
    }

    @Override
    public void setFavorite(FavoriteEntity entity) {
        getAllFavorites().subscribe(list -> {
            list.add(entity);
            final String fileContent = gson.toJson(list);
            fileManager.writeIntoInternalMemory(FILE_NAME, fileContent);
        });
    }

    private Observable<List<FavoriteEntity>> getAllFavorites() {
        final List<FavoriteEntity> list = new ArrayList<>();
        try {
            final String json = fileManager.readFromInternalMemory(FILE_NAME);
            final Type type = new TypeToken<List<FavoriteEntity>>() {
            }.getType();
            list.addAll(gson.fromJson(json, type));
        } catch (IOException | JsonSyntaxException e) {
            // do nothing
        }
        return Observable.just(list);
    }

}