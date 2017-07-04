package com.garagu.marvel.data.datasource.local;

import android.util.Log;

import com.garagu.marvel.data.FileManager;
import com.garagu.marvel.data.datasource.CharacterDatasource;
import com.garagu.marvel.data.entity.ResultEntity;
import com.garagu.marvel.data.entity.character.CharacterListEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class CharacterLocalDatasource implements CharacterDatasource {

    private final FileManager fileManager;
    private final Gson gson;

    public CharacterLocalDatasource(FileManager fileManager, Gson gson) {
        this.fileManager = fileManager;
        this.gson = gson;
    }

    @Override
    public Observable<ResultEntity<CharacterListEntity>> getCharacters(int offset) {
        ResultEntity<CharacterListEntity> entity = new ResultEntity<>();
        try {
            String json = fileManager.readAsset("characters.json");
            Type type = new TypeToken<ResultEntity<CharacterListEntity>>() {
            }.getType();
            entity = gson.fromJson(json, type);
        } catch (IOException | JsonSyntaxException e) {
            Log.e("Error reading file!", e.getMessage());
        }
        return Observable.just(entity);
    }

}