package com.garagu.marvel.presentation.comic.view.detail.gallery;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

import com.garagu.marvel.R;

/**
 * Created by garagu.
 */
class GalleryDecoration extends ItemDecoration {

    private final int space;

    GalleryDecoration(@NonNull Context context) {
        space = context.getResources().getDimensionPixelSize(R.dimen.border);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(outRect, view, recyclerView, state);
        outRect.bottom = isLastRow(view, recyclerView, state) ? 0 : space;
        outRect.top = 0;
        outRect.left = (recyclerView.getChildAdapterPosition(view) % 2 != 0) ? space : 0;
        outRect.right = (recyclerView.getChildAdapterPosition(view) % 2 == 0) ? space : 0;
    }

    private boolean isLastRow(View view, RecyclerView recyclerView, State state) {
        return (recyclerView.getChildAdapterPosition(view) == state.getItemCount() - 1) || (recyclerView.getChildAdapterPosition(view) == state.getItemCount() - 2);
    }

}