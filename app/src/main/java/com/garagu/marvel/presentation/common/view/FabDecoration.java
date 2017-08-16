package com.garagu.marvel.presentation.common.view;

import android.annotation.SuppressLint;
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

public class FabDecoration extends ItemDecoration {

    private final int marginBottom;

    @SuppressLint("PrivateResource")
    public FabDecoration(@NonNull Context context) {
        final int fabSize = context.getResources().getDimensionPixelSize(android.support.design.R.dimen.design_fab_size_normal);
        final int fabMargin = context.getResources().getDimensionPixelSize(R.dimen.margin);
        marginBottom = fabSize + 2 * fabMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (isLastChild(view, parent, state)) {
            outRect.bottom = marginBottom;
        }
    }

    private boolean isLastChild(View view, RecyclerView parent, State state) {
        return (parent.getChildAdapterPosition(view) == state.getItemCount() - 1);
    }

}