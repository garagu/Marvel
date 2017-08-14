package com.garagu.marvel.presentation.common.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by garagu
 */
public abstract class BaseFragment extends Fragment {

    private View rootView;
    private Unbinder unbinder;

    protected abstract int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        onCreateView();
        return rootView;
    }

    @CallSuper
    protected void onCreateView() {
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> component) {
        Class<?> clazz = getActivity().getClass();
        if (!HasInjection.class.isAssignableFrom(clazz)) {
            throw new UnsupportedOperationException(clazz.getSimpleName() + " doesn't implement HasInjection");
        }
        HasInjection injectedActivity = (HasInjection<T>) getActivity();
        return component.cast(injectedActivity.getComponent());
    }

    protected void showMessage(String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
    }

    protected void showMessage(@StringRes int message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show();
    }

}