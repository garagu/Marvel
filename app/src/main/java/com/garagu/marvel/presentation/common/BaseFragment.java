package com.garagu.marvel.presentation.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garagu.marvel.presentation.application.di.NetComponent;

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

    protected NetComponent getNetComponent() {
        return ((BaseActivity) getActivity()).getNetComponent();
    }

    protected void showMessage(String message) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }

}