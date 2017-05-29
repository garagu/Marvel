package com.garagu.marvel.presentation.comic.view.detail;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by garagu.
 */
class GridItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    GridItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, recyclerView, state);
        if (recyclerView.getChildAdapterPosition(view) % 2 == 0) {
            outRect.right = space;
        } else {
            outRect.left = space;
        }
        outRect.bottom = space;
    }

}