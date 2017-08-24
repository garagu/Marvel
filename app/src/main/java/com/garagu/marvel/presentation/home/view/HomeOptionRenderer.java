package com.garagu.marvel.presentation.home.view;

import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.presentation.common.view.RVRenderer;
import com.garagu.marvel.presentation.home.model.HomeOptionViewModel;

import butterknife.BindView;

/**
 * Created by garagu.
 */
class HomeOptionRenderer extends RVRenderer<HomeOptionViewModel> {

    private final int cellHeight;
    @BindView(R.id.img_home_option)
    ImageView imgHomeOption;
    @BindView(R.id.txt_home_option)
    TextView txtHomeOption;

    HomeOptionRenderer(@NonNull OnRendererClickListener<HomeOptionViewModel> clickListener, int cellHeight) {
        super(clickListener);
        this.cellHeight = cellHeight;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.item_home;
    }

    @Override
    public void render() {
        final HomeOptionViewModel homeItem = getContent();
        overrideSize();
        imgHomeOption.setImageResource(homeItem.getBgImage());
        txtHomeOption.setText(homeItem.getText());
        txtHomeOption.setGravity(homeItem.getTextPosition());
    }

    private void overrideSize() {
        LayoutParams params = new LayoutParams(getRootView().getLayoutParams().width, cellHeight);
        getRootView().setLayoutParams(params);
    }

}