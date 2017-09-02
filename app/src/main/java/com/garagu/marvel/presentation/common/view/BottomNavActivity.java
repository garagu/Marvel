package com.garagu.marvel.presentation.common.view;

import android.support.design.widget.BottomNavigationView;
import android.view.View;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.model.ViewConfig;

import butterknife.BindView;

/**
 * Created by garagu.
 */
public class BottomNavActivity extends BaseActivity {

    @BindView(R.id.bottom_nav_view)
    BottomNavigationView bottomNavView;

    protected void initBaseView(ViewConfig viewConfig) {
        initToolbar(viewConfig.getToolbarTitle());
        if (viewConfig.showBack()) {
            showBackButton();
        }
        // TODO create here the bottom nav and not change app_bar_base
        bottomNavView.inflateMenu(viewConfig.getBottomNavMenu());
        bottomNavView.setOnNavigationItemSelectedListener(viewConfig.getOnBottomNavItemSelectedListener());
        bottomNavView.setSelectedItemId(viewConfig.getBottomNavItemSelected());
        bottomNavView.setVisibility(View.VISIBLE);
    }

}