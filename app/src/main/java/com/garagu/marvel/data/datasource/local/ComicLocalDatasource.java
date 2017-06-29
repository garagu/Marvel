package com.garagu.marvel.data.datasource.local;

import android.content.Context;
import android.util.Log;

import com.garagu.marvel.data.datasource.ComicDatasource;
import com.garagu.marvel.data.entity.ComicListEntity;
import com.garagu.marvel.data.entity.ResultEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class ComicLocalDatasource implements ComicDatasource {

    private final Context context;

    public ComicLocalDatasource(Context context) {
        this.context = context;
    }

    @Override
    public Observable<ResultEntity<ComicListEntity>> getComicsByCharacter(String id, int offset) {
        ResultEntity<ComicListEntity> entity = new ResultEntity<>();
        BufferedReader br;
        try {
            InputStream is = context.getAssets().open("comics.json");
            br = new BufferedReader(new InputStreamReader(is));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            br.close();
            String json = stringBuilder.toString();
            Gson gson = new GsonBuilder().create();
            Type type = new TypeToken<ResultEntity<ComicListEntity>>() {
            }.getType();
            entity = gson.fromJson(json, type);
        } catch (IOException | JsonSyntaxException e) {
            Log.e("Error reading file!", e.getMessage());
        }
        return Observable.just(entity);
    }

}