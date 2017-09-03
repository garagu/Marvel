package com.garagu.marvel.data.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public abstract class EntityToModelMapper<E, M> {

    @NonNull
    public List<M> listEntityToModel(@NonNull List<E> entityList) {
        final List<M> modelList = new ArrayList<>();
        for (E entity : entityList) {
            final M model = simpleEntityToModel(entity);
            modelList.add(model);
        }
        return modelList;
    }

    @NonNull
    public abstract M simpleEntityToModel(@NonNull E entity);

}