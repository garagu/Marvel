package com.garagu.marvel.presentation.common.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by garagu.
 */
public abstract class ModelToViewModelMapper<M, VM> {

    @NonNull
    public List<VM> listModelToViewModel(@NonNull List<M> modelList) {
        final List<VM> viewModelList = new ArrayList<>();
        for (M model : modelList) {
            final VM viewModel = simpleModelToViewModel(model);
            viewModelList.add(viewModel);
        }
        return viewModelList;
    }

    @NonNull
    public abstract VM simpleModelToViewModel(@NonNull M model);

}