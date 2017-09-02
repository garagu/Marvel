package com.garagu.marvel.presentation.character.model;

import android.support.annotation.NonNull;

import com.garagu.marvel.domain.model.character.Link;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.garagu.marvel.presentation.common.model.ModelToViewModelMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
@ActivityScope
public class LinkModelToViewModelMapper extends ModelToViewModelMapper<Link, LinkViewModel> {

    @Inject
    public LinkModelToViewModelMapper() {
    }

    @NonNull
    @Override
    public LinkViewModel simpleModelToViewModel(@NonNull Link model) {
        final String name = (model.getName().length() < 2)
                ? model.getName().toUpperCase()
                : model.getName().substring(0, 1).toUpperCase() + model.getName().substring(1);
        return new LinkViewModel.Builder()
                .withName(name)
                .withUrl(model.getUrl())
                .build();
    }

    @NonNull
    public List<String> listLinkNames(@NonNull List<Link> modelList) {
        final List<String> viewModelList = new ArrayList<>();
        for (Link model : modelList) {
            viewModelList.add(model.getName());
        }
        return viewModelList;
    }

}