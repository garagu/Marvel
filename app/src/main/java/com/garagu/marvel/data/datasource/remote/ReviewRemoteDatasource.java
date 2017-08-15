package com.garagu.marvel.data.datasource.remote;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.datasource.ReviewDatasource;
import com.garagu.marvel.data.entity.review.ReviewEntity;
import com.garagu.marvel.data.net.exception.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class ReviewRemoteDatasource implements ReviewDatasource {

    private static final String CHILD_REVIEWS = "reviews";
    private static final String CHILD_USER_REVIEWS = "user-reviews";

    // TODO Inject
    private final DatabaseReference databaseReference;

    public ReviewRemoteDatasource() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public Observable<List<ReviewEntity>> getReviewsByComic(@NonNull String comicId) {
        // TODO handle connection
        final Query query = databaseReference.child(CHILD_REVIEWS).child(comicId);
        return Observable.create(subscriber -> {
            final ValueEventListener eventListener = new SingleValueEventListener(query) {
                @Override
                void onRecoverData(DataSnapshot dataSnapshot) {
                    final List<ReviewEntity> reviews = new ArrayList<>();
                    for (DataSnapshot children : dataSnapshot.getChildren()) {
                        final ReviewEntity review = children.getValue(ReviewEntity.class);
                        reviews.add(review);
                    }
                    subscriber.onNext(reviews);
                    subscriber.onComplete();
                }

                @Override
                void onError(DatabaseError databaseError) {
                    final FirebaseException exception = new FirebaseException(databaseError.getMessage());
                    subscriber.onError(exception);
                }
            };
            query.addListenerForSingleValueEvent(eventListener);
        });
    }

    @Override
    public Observable<Boolean> addReviewToComic(@NonNull String comicId, @NonNull ReviewEntity review) {
        // TODO handle connection
        final Map<String, Object> reviewValues = review.toMap();
        final String newKey = databaseReference.push().getKey();
        final String path = "/" + CHILD_REVIEWS + "/" + comicId + "/" + newKey;
        final Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(path, reviewValues);
        // String otherPath = "/" + CHILD_USER_REVIEWS + "/" + usernameId + "/" + newKey;
        // childUpdates.put(otherPath, reviewValues);
        return Observable.create(subscriber -> databaseReference.updateChildren(childUpdates, (databaseError, databaseReference) -> {
            if (databaseError == null) {
                subscriber.onNext(true);
                subscriber.onComplete();
            } else {
                final FirebaseException exception = new FirebaseException(databaseError.getMessage());
                subscriber.onError(exception);
            }
        }));
    }
}