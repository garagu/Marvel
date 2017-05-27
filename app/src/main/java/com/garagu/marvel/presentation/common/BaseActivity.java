package com.garagu.marvel.presentation.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.application.MarvelApplication;
import com.garagu.marvel.presentation.application.di.NetComponent;

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
        getNetComponent().inject(this);
    }

    protected NetComponent getNetComponent() {
        return ((MarvelApplication) getApplication()).getNetComponent();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_container, getInitialFragment())
                .commit();
    }

}