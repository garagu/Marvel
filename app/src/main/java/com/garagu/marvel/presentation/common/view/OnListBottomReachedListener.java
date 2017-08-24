package com.garagu.marvel.presentation.common.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

/**
 * Created by garagu.
 */
public abstract class OnListBottomReachedListener extends OnScrollListener {

    private final LinearLayoutManager layoutManager;

    public OnListBottomReachedListener(@NonNull LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public abstract void onBottomReached();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (isBottomReached(layoutManager, dy)) {
            onBottomReached();
        }
    }

    private boolean isBottomReached(@NonNull LinearLayoutManager layoutManager, int dy) {
        if (dy > 0) {
            final int visibleItems = layoutManager.getChildCount();
            final int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
            final int totalItems = layoutManager.getItemCount();
            return (visibleItems + firstVisibleItem >= totalItems);
        }
        return false;
    }

}
