package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.model.favorite.Favorite;
import com.garagu.marvel.domain.repository.FavoriteRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class AddFavorite extends UseCase<Favorite, Boolean> {

    private final FavoriteRepository repository;

    @Inject
    public AddFavorite(ExecutorThread executorThread, PostExecutionThread postExecutionThread, FavoriteRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Boolean> buildObservable(Favorite favorite) {
        return repository.addFavorite(favorite);
    }

}