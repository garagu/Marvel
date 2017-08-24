package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;
import android.util.Log;

import com.garagu.marvel.data.entity.comic.ComicDateEntity;
import com.garagu.marvel.domain.model.comic.ComicDate;
import com.garagu.marvel.presentation.application.di.ActivityScope;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class ComicDateEntityMapper {

    private static final String DATE_FORMAT_API = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    @Inject
    public ComicDateEntityMapper() {
    }

    @NonNull
    List<ComicDate> mapListEntityToModel(@NonNull ComicDateEntity[] entityArray) {
        final List<ComicDate> modelList = new ArrayList<>();
        for (ComicDateEntity entity : entityArray) {
            final ComicDate model = mapEntityToModel(entity);
            modelList.add(model);
        }
        return modelList;
    }

    @NonNull
    private ComicDate mapEntityToModel(@NonNull ComicDateEntity entity) {
        final String formattedType = formatType(entity.getType());
        final String formattedDate = formatDate(entity.getDate());
        return new ComicDate(formattedType, formattedDate);
    }

    @NonNull
    private String formatType(@NonNull String entity) {
        if (!entity.isEmpty()) {
            String formattedType = "";
            String[] typeWords = entity.split("(?=\\p{Lu})");
            final String firstLetter = typeWords[0].substring(0, 1).toUpperCase();
            typeWords[0] = (typeWords[0].length() == 1) ? firstLetter : firstLetter + typeWords[0].substring(1);
            for (int i = 0; i < typeWords.length; i++) {
                formattedType = formattedType + typeWords[i];
                if (i != typeWords.length - 1) {
                    formattedType = formattedType + " ";
                }
            }
            return formattedType;
        }
        return entity;
    }

    @NonNull
    private String formatDate(@NonNull String entity) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_API, Locale.getDefault());
        try {
            final Date date = dateFormat.parse(entity);
            dateFormat.applyPattern(DATE_FORMAT);
            return dateFormat.format(date);
        } catch (ParseException e) {
            Log.e("ComicDateMapper", e.getMessage());
            return "";
        }
    }

}