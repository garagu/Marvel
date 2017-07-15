package com.garagu.marvel.presentation.common.view;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrogomez.renderers.Renderer;

import butterknife.ButterKnife;

/**
 * Created by garagu.
 */
public abstract class RVRenderer<T> extends Renderer<T> {

    private OnRendererClickListener<T> clickListener;

    protected RVRenderer() {
    }

    protected RVRenderer(@NonNull OnRendererClickListener<T> clickListener) {
        this.clickListener = clickListener;
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View rootView = inflater.inflate(getLayoutResId(), parent, false);
        ButterKnife.bind(this, rootView);
        if (clickListener != null) {
            rootView.setOnClickListener(view -> clickListener.onRendererClick(getContent()));
        }
        return rootView;
    }

    @Override
    protected void setUpView(View rootView) {
        // do nothing
    }

    @Override
    protected void hookListeners(View rootView) {
        // do nothing
    }

    public interface OnRendererClickListener<T> {
        void onRendererClick(@NonNull T item);
    }

}