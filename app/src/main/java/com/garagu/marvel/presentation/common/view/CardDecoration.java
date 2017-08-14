package com.garagu.marvel.presentation.common.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

import com.garagu.marvel.R;

/**
 * Created by garagu.
 */
public class CardDecoration extends ItemDecoration {

    private final int space;

    public CardDecoration(@DimenRes int space) {
        this.space = space;
    }

    public CardDecoration(Context context) {
        space = context.getResources().getDimensionPixelSize(R.dimen.margin_xsmall);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView recyclerView, State state) {
        super.getItemOffsets(outRect, view, recyclerView, state);
        if (recyclerView.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
        }
        outRect.bottom = space;
        outRect.left = space;
        outRect.right = space;
    }

}