package com.garagu.marvel.presentation.common;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

import com.garagu.marvel.R;

/**
 * Created by garagu.
 */
public class CardDecoration extends ItemDecoration {

    private int space;

    public CardDecoration(Context context) {
        space = context.getResources().getDimensionPixelSize(R.dimen.margin);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, recyclerView, state);
        outRect.left = space;
        if (recyclerView.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
        }
        outRect.right = space;
        outRect.bottom = space;
    }

}