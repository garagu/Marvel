package com.garagu.marvel.data.datasource.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.garagu.marvel.data.datasource.ReviewDatasource;
import com.garagu.marvel.data.entity.review.ReviewEntity;
import com.garagu.marvel.data.net.NetworkUtils;
import com.garagu.marvel.data.net.exception.ConnectionException;
import com.garagu.marvel.data.net.exception.FirebaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class ReviewRemoteDatasource implements ReviewDatasource {

    private static final String CHILD_REVIEWS = "reviews";
    private static final String CHILD_USER_REVIEWS = "user-reviews";

    private final Context context;
    private final DatabaseReference databaseReference;

    @Inject
    public ReviewRemoteDatasource(Context context, DatabaseReference databaseReference) {
        this.context = context;
        this.databaseReference = databaseReference;
    }

    @Override
    public Observable<Boolean> addReviewToComic(@NonNull String comicId, @NonNull String userId, @NonNull ReviewEntity review) {
        if (NetworkUtils.isOnline(context)) {
            final Map<String, Object> reviewValues = review.toMap();
            final String newKey = databaseReference.push().getKey();
            final String pathReviews = "/" + CHILD_REVIEWS + "/" + comicId + "/" + newKey;
            final Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put(pathReviews, reviewValues);
            String pathUserReviews = "/" + CHILD_USER_REVIEWS + "/" + userId + "/" + newKey;
            childUpdates.put(pathUserReviews, reviewValues);
            return Observable.create(subscriber -> databaseReference.updateChildren(childUpdates, (databaseError, databaseReference) -> {
                if (databaseError == null) {
                    subscriber.onNext(true);
                    subscriber.onComplete();
                } else {
                    final FirebaseException exception = new FirebaseException(databaseError.getMessage());
                    subscriber.onError(exception);
                }
            }));
        } else {
            final ConnectionException exception = new ConnectionException(context);
            return Observable.error(exception);
        }

    }

    @Override
    public Observable<List<ReviewEntity>> getReviewsByComic(@NonNull String comicId) {
        final Query query = databaseReference.child(CHILD_REVIEWS).child(comicId);
        return getReviews(query);
    }


    @Override
    public Observable<List<ReviewEntity>> getReviewsByUser(@NonNull String userId) {
        final Query query = databaseReference.child(CHILD_USER_REVIEWS).child(userId);
        return getReviews(query);
    }

    @SuppressWarnings("unchecked")
    private Observable<List<ReviewEntity>> getReviews(@NonNull Query query) {
        return Observable.create(subscriber -> {
            if (NetworkUtils.isOnline(context)) {
                final ValueEventListener eventListener = new SingleValueEventListener(query, subscriber, ReviewEntity.class);
                query.addListenerForSingleValueEvent(eventListener);
            } else {
                final ConnectionException exception = new ConnectionException(context);
                subscriber.onError(exception);
            }
        });
    }

}