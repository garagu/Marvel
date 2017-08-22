package com.garagu.marvel.presentation.reviews.di;

import android.app.Application;

import com.garagu.marvel.data.datasource.ReviewDatasource;
import com.garagu.marvel.data.datasource.remote.ReviewRemoteDatasource;
import com.garagu.marvel.data.mapper.ReviewEntityMapper;
import com.garagu.marvel.data.repository.ReviewDataRepository;
import com.garagu.marvel.domain.repository.ReviewRepository;
import com.garagu.marvel.presentation.application.di.ActivityScope;
import com.google.firebase.database.DatabaseReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by garagu.
 */
@Module
public class MyReviewsModule {

    @Provides
    @ActivityScope
    ReviewDatasource provideReviewDatasource(Application application, DatabaseReference dbReference) {
        return new ReviewRemoteDatasource(application, dbReference);
    }

    @Provides
    @ActivityScope
    ReviewRepository provideReviewRepository(ReviewDatasource datasource, ReviewEntityMapper mapper) {
        return new ReviewDataRepository(datasource, mapper);
    }

}