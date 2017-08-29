package com.garagu.marvel.data.datasource.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.garagu.marvel.data.datasource.ReviewDatasource;
import com.garagu.marvel.data.entity.review.MyReviewEntity;
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
    public Observable<Boolean> addReview(int comicId, @NonNull ReviewEntity comicReview, @NonNull String userId, @NonNull MyReviewEntity userReview) {
        if (NetworkUtils.isOnline(context)) {
            final Map<String, Object> childUpdates = new HashMap<>();
            addComicReviewToDbUpdates(childUpdates, comicId, comicReview);
            addUserReviewToDbUpdates(childUpdates, userId, userReview);
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

    private void addComicReviewToDbUpdates(@NonNull Map<String, Object> childUpdates, int comicId, @NonNull ReviewEntity comicReview) {
        final Map<String, Object> reviewValues = comicReview.toMap();
        final String path = "/" + CHILD_REVIEWS + "/" + comicId + "/" + databaseReference.push().getKey();
        childUpdates.put(path, reviewValues);
    }

    private void addUserReviewToDbUpdates(@NonNull Map<String, Object> childUpdates, @NonNull String userId, @NonNull MyReviewEntity userReview) {
        final Map<String, Object> reviewValues = userReview.toMap();
        final String path = "/" + CHILD_USER_REVIEWS + "/" + userId + "/" + databaseReference.push().getKey();
        childUpdates.put(path, reviewValues);
    }

    @Override
    public Observable<List<ReviewEntity>> getReviewsByComic(int comicId) {
        return getList(CHILD_REVIEWS, String.valueOf(comicId), ReviewEntity.class);
    }


    @Override
    public Observable<List<MyReviewEntity>> getReviewsByUser(@NonNull String userId) {
        return getList(CHILD_USER_REVIEWS, userId, MyReviewEntity.class);
    }

    @SuppressWarnings("unchecked")
    private <T> Observable<List<T>> getList(@NonNull String table, @NonNull String id, Class<T> itemClass) {
        final Query query = databaseReference.child(table).child(id);
        return Observable.create(subscriber -> {
            if (NetworkUtils.isOnline(context)) {
                final ValueEventListener eventListener = new SingleValueEventListener(query, subscriber, itemClass);
                query.addListenerForSingleValueEvent(eventListener);
            } else {
                final ConnectionException exception = new ConnectionException(context);
                subscriber.onError(exception);
            }
        });
    }

}