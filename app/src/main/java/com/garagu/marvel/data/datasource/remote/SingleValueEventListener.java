package com.garagu.marvel.data.datasource.remote;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by garagu.
 */

abstract class SingleValueEventListener implements ValueEventListener {

    private final Query query;

    SingleValueEventListener(@NonNull Query query) {
        this.query = query;
    }

    abstract void onRecoverData(DataSnapshot dataSnapshot);

    abstract void onError(DatabaseError databaseError);

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        onRecoverData(dataSnapshot);
        query.removeEventListener(this);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        onError(databaseError);
        query.removeEventListener(this);
    }

}