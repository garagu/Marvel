package com.garagu.marvel.data.datasource.local;

import android.util.Log;

import com.garagu.marvel.data.local.FileManager;
import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.entity.common.ResultEntity;
import com.garagu.marvel.data.entity.comic.ComicListEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class ComicLocalDatasource implements ComicDatasource {

    private final FileManager fileManager;
    private final Gson gson;

    public ComicLocalDatasource(FileManager fileManager, Gson gson) {
        this.fileManager = fileManager;
        this.gson = gson;
    }

    @Override
    public Observable<ResultEntity<ComicListEntity>> getComicsByCharacter(String id, int offset) {
        ResultEntity<ComicListEntity> entity = new ResultEntity<>();
        try {
            String json = fileManager.readAsset("comics.json");
            Type type = new TypeToken<ResultEntity<ComicListEntity>>() {
            }.getType();
            entity = gson.fromJson(json, type);
        } catch (IOException | JsonSyntaxException e) {
            Log.e("Error reading file!", e.getMessage());
        }
        return Observable.just(entity);
    }

}