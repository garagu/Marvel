package com.garagu.marvel.presentation.comic.view;

import android.app.Fragment;

import com.garagu.marvel.presentation.comic.view.list.ListFragment;
import com.garagu.marvel.presentation.common.BaseActivity;

/**
 * Created by garagu.
 */
public class ComicActivity extends BaseActivity {

    @Override
    protected Fragment getInitialFragment() {
        return ListFragment.newInstance();
    }

}