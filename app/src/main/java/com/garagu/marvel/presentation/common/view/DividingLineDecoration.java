package com.garagu.marvel.presentation.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

import com.garagu.marvel.R;

/**
 * Created by garagu.
 */

public class DividingLineDecoration extends ItemDecoration {

    private final Drawable drawable;
    private final int margin;

    public DividingLineDecoration(@NonNull Context context) {
        drawable = ContextCompat.getDrawable(context, R.drawable.divider);
        margin = context.getResources().getDimensionPixelSize(R.dimen.margin_xlarge);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, State state) {
        final int left = parent.getPaddingLeft() + margin;
        final int right = parent.getWidth() - parent.getPaddingRight() - margin;
        for (int i = 0; i < parent.getChildCount() - 1; i++) {
            final View child = parent.getChildAt(i);
            final LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }
    }

}