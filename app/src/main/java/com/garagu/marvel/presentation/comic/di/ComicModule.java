package com.garagu.marvel.presentation.comic.di;

import com.garagu.marvel.domain.repository.ComicRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class ComicModule {

    @Provides
    ComicRepository provideComicRepository() {
        // TODO
        return new ComicRepository();
    }
}
