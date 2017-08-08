package com.garagu.marvel.presentation.common.view;

import android.animation.ObjectAnimator;

import com.pedrogomez.renderers.AdapteeCollection;
import com.pedrogomez.renderers.RendererBuilder;
import com.pedrogomez.renderers.RendererViewHolder;

/**
 * Created by garagu.
 */
public class RVAnimRendererAdapter<T> extends com.pedrogomez.renderers.RVRendererAdapter {

    private int lastPosition;

    @SuppressWarnings("unchecked")
    public RVAnimRendererAdapter(RendererBuilder<T> rendererBuilder, AdapteeCollection<T> collection) {
        super(rendererBuilder, collection);
    }

    @Override
    public void onBindViewHolder(RendererViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        final int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition > lastPosition) {
            final ObjectAnimator scaleX = ObjectAnimator.ofFloat(viewHolder.itemView, "scaleX", 0.5f, 1f);
            scaleX.start();
            final ObjectAnimator scaleY = ObjectAnimator.ofFloat(viewHolder.itemView, "scaleY", 0.5f, 1f);
            scaleY.start();
            lastPosition = adapterPosition;
        }
    }

}
