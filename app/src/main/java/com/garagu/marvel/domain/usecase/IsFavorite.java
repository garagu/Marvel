package com.garagu.marvel.domain.usecase;

import com.garagu.marvel.domain.repository.FavoriteRepository;
import com.garagu.marvel.domain.thread.ExecutorThread;
import com.garagu.marvel.domain.thread.PostExecutionThread;
import com.garagu.marvel.domain.usecase.IsFavorite.InputParam;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class IsFavorite extends UseCase<InputParam, Boolean> {

    private final FavoriteRepository repository;

    @Inject
    public IsFavorite(ExecutorThread executorThread, PostExecutionThread postExecutionThread, FavoriteRepository repository) {
        super(executorThread, postExecutionThread);
        this.repository = repository;
    }

    @Override
    protected Observable<Boolean> buildObservable(InputParam inputParam) {
        return repository.isFavorite(inputParam.getUserId(), inputParam.getId(), inputParam.getType());
    }

    public static class InputParam {

        private final String userId;
        private final int id;
        private final int type;

        public InputParam(String userId, int id, int type) {
            this.userId = userId;
            this.id = id;
            this.type = type;
        }

        public String getUserId() {
            return userId;
        }

        public int getId() {
            return id;
        }

        public int getType() {
            return type;
        }

    }

}