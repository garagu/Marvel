package com.garagu.marvel.presentation.reviews.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.garagu.marvel.presentation.common.view.BaseActivity;
import com.garagu.marvel.presentation.common.view.HasInjection;
import com.garagu.marvel.presentation.reviews.di.DaggerMyReviewsComponent;
import com.garagu.marvel.presentation.reviews.di.MyReviewsComponent;

import javax.inject.Inject;

/**
 * Created by garagu.
 */
public class MyReviewsActivity extends BaseActivity implements HasInjection<MyReviewsComponent> {

    @Inject
    Navigator navigator;

    private MyReviewsComponent component;

    @NonNull
    public static Intent getCallingIntent(@NonNull Activity activity) {
        return new Intent(activity, MyReviewsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDependencyInjection();
        showBackButton();
        navigator.openMyReviews(this);
    }

    private void initDependencyInjection() {
        component = DaggerMyReviewsComponent.builder()
                .appComponent(getAppComponent())
                .build();
        component.inject(this);
    }

    @NonNull
    @Override
    public MyReviewsComponent getComponent() {
        return component;
    }

}