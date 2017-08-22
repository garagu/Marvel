package com.garagu.marvel.data.datasource.remote;

import android.support.annotation.NonNull;

import com.garagu.marvel.data.net.exception.FirebaseException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.ObservableEmitter;

/**
 * Created by garagu.
 */

class SingleValueEventListener<T> implements ValueEventListener {

    private final Query query;
    private final ObservableEmitter<List<T>> subscriber;
    private final Class<T> itemClass;

    SingleValueEventListener(@NonNull Query query, @NonNull ObservableEmitter<List<T>> subscriber, @NonNull Class<T> itemClass) {
        this.query = query;
        this.subscriber = subscriber;
        this.itemClass = itemClass;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        query.removeEventListener(this);
        final List<T> items = getList(dataSnapshot);
        subscriber.onNext(items);
        subscriber.onComplete();
    }

    private List<T> getList(@NonNull DataSnapshot dataSnapshot) {
        final List<T> list = new ArrayList<>();
        for (DataSnapshot children : dataSnapshot.getChildren()) {
            final T item = children.getValue(itemClass);
            list.add(item);
        }
        return list;
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        query.removeEventListener(this);
        final FirebaseException exception = new FirebaseException(databaseError.getMessage());
        subscriber.onError(exception);
    }

}