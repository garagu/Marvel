package com.garagu.marvel.presentation.myreviews.di;

import android.content.Context;

import com.garagu.marvel.data.datasource.ReviewDatasource;
import com.garagu.marvel.data.datasource.remote.ReviewRemoteDatasource;
import com.garagu.marvel.data.mapper.MyReviewEntityMapper;
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
    ReviewDatasource provideReviewDatasource(Context context, DatabaseReference dbReference) {
        return new ReviewRemoteDatasource(context, dbReference);
    }

    @Provides
    @ActivityScope
    ReviewRepository provideReviewRepository(ReviewDatasource datasource, ReviewEntityMapper comicReviewMapper, MyReviewEntityMapper userReviewMapper) {
        return new ReviewDataRepository(datasource, comicReviewMapper, userReviewMapper);
    }

}