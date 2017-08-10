package com.garagu.marvel.data.datasource.remote;

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
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by garagu.
 */
public class ReviewRemoteDatasource implements ReviewDatasource {

    private static final String CHILD_REVIEWS = "reviews";

    // TODO Inject
    private final DatabaseReference database;

    public ReviewRemoteDatasource() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public Observable<List<ReviewEntity>> getReviewsByComic(String comicId) {
        // TODO handle connection
        final Query query = database.child(CHILD_REVIEWS).child(comicId);
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

}