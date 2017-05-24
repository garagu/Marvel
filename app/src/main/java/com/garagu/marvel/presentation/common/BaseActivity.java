package com.garagu.marvel.presentation.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.application.MarvelApplication;
import com.garagu.marvel.presentation.application.di.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    protected abstract Fragment getInitialFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initInjection();
        initView();
    }

    private void initInjection() {
        ButterKnife.bind(this);
        getAppComponent().inject(this);
    }

    protected AppComponent getAppComponent() {
        return ((MarvelApplication) getApplication()).getAppComponent();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, getInitialFragment())
                .commit();
    }

}
