package com.garagu.marvel.presentation.common.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.application.MarvelApplication;
import com.garagu.marvel.presentation.application.di.AppComponent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private TextView txtUsername;
    private TextView txtEmail;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initInjection();
        initComponents();
    }

    private void initInjection() {
        ButterKnife.bind(this);
        final View headerView = navigationView.getHeaderView(0);
        txtUsername = ButterKnife.findById(headerView, R.id.txt_nav_header_username);
        txtEmail = ButterKnife.findById(headerView, R.id.txt_nav_header_email);
    }

    private void initComponents() {
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.setToolbarNavigationClickListener(view -> onBackPressed());
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    protected AppComponent getAppComponent() {
        return ((MarvelApplication) getApplication()).getAppComponent();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (getFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {
                super.onBackPressed();
            }
        }
    }

    protected void hideNavigationView() {
        drawerToggle.setDrawerIndicatorEnabled(false);
    }

    protected void initNavigationView(@Nullable String username, @Nullable String email, @NonNull OnLateralMenuItemSelectedListener onItemSelectedListener) {
        txtUsername.setText(username);
        txtEmail.setText(email);
        navigationView.setNavigationItemSelectedListener(item -> {
            onItemSelectedListener.onLateralMenuItemSelected(item.getItemId());
            drawerLayout.closeDrawer(GravityCompat.START);
            return false;
        });
    }

    protected void initToolbar(@StringRes int toolbarTitle) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(toolbarTitle);
        }
    }

    protected void showBackButton() {
        hideNavigationView();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

}