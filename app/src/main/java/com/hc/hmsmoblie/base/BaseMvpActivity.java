package com.hc.hmsmoblie.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc.hmsmoblie.R;
import com.yc.yclibrary.base.YcMvpAppCompatActivity;
import com.yc.yclibrary.mvp.BasePresenter;

/**
 *
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends YcMvpAppCompatActivity<P> {
    protected TextView mTitle;
    protected ImageView mLeftBtn;
    protected TextView mLeftTv;
    protected ImageView mRightBtn;
    protected TextView mRightTv;

    /**
     * 设置标题栏
     */
    protected void setToolBar(String title) {
        mTitle = (TextView) findViewById(R.id.actionbar_mid_tv);
        mTitle.setText(title);
        mLeftBtn = (ImageView) findViewById(R.id.actionbar_left_btn);
        mLeftTv = (TextView) findViewById(R.id.actionbar_left_tv);
        mRightBtn = (ImageView) findViewById(R.id.actionbar_right_btn);
        mRightTv = (TextView) findViewById(R.id.actionbar_right_tv);
        mLeftBtn.setOnClickListener(v -> onLeftClick(v));
        mLeftTv.setOnClickListener(v -> onLeftClick(v));
        mRightBtn.setOnClickListener(v -> onRightClick(v));
        mRightTv.setOnClickListener(v -> onRightClick(v));
    }

    /**
     * 标题左边按钮事件（含左边文字）
     */
    public void onLeftClick(View v) {
        finish();
    }

    /**
     * 标题右边按钮事件（含右边文字）
     */
    public void onRightClick(View v) {
    }
}
