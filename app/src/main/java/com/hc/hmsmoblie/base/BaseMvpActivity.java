package com.hc.hmsmoblie.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
        mTitle = (TextView) findViewById(R.id.tvActionbarMid);
        mTitle.setText(title);
        mLeftBtn = (ImageView) findViewById(R.id.ivActionbarLeft);
        mLeftTv = (TextView) findViewById(R.id.tvActionbarLeft);
        mRightBtn = (ImageView) findViewById(R.id.ivActionbarRight);
        mRightTv = (TextView) findViewById(R.id.tvActionbarRight);
        findViewById(R.id.ivActionbarLeft).setOnClickListener(v -> onLeftClick(v));
        findViewById(R.id.tvActionbarLeft).setOnClickListener(v -> onLeftClick(v));
        findViewById(R.id.LlActionbarRight).setOnClickListener(v -> onRightClick(v));
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
