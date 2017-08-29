package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.common.Favorite;
import com.garagu.marvel.domain.repository.FavoriteRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class GetFavorites extends UseCase<String, List<Favorite>> {

    private final FavoriteRepository repository;

    @Inject
    public GetFavorites(ExecutorThread executorThread, PostExecutionThread postExecutionThread, FavoriteRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<Favorite>> buildObservable(String userId) {
        return repository.getFavorites(userId);
    }

}