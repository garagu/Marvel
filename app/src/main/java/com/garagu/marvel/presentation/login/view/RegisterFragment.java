package com.garagu.marvel.presentation.login.view;

import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.common.view.BaseFragment;

/**
 * Created by garagu.
 */

public class RegisterFragment extends BaseFragment {

    @NonNull
    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

}