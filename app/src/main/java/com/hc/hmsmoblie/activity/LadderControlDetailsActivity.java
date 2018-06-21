package com.hc.hmsmoblie.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.adapter.LadderControlDetailsAdapter;
import com.hc.hmsmoblie.base.BaseActivity;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.fragment.MainFragment;
import com.hc.hmsmoblie.mvp.contact.LadderControlDeviceListC;
import com.hc.hmsmoblie.mvp.presenter.LadderControlDeviceListP;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.yc.yclibrary.exception.ApiException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 梯控详情页面
 */

public class LadderControlDetailsActivity extends BaseActivity {

    @BindView(R.id.vpDataReport)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    private String mProId;
    private String mDeviceId;
    private static final String PRO_ID = "pro_id";
    private static final String DEVICE_ID = "device_id";

    public static void newInstance(Activity activity, String proId, String deviceId) {
        Intent intent = new Intent(activity, LadderControlDetailsActivity.class);
        intent.putExtra(PRO_ID, proId);
        intent.putExtra(DEVICE_ID, deviceId);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ladder_control_details_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        try {
            mProId = getIntent().getStringExtra(PRO_ID);
            mDeviceId = getIntent().getStringExtra(DEVICE_ID);
            setToolBar("操作详情");
//            String schemeID = getIntent().getStringExtra(JumpToUtils.KEY_SCHEME_ID);
            LadderControlDetailsAdapter mHomeAdapter = new LadderControlDetailsAdapter(getSupportFragmentManager(),mProId,mDeviceId);
            mViewPager.setOffscreenPageLimit(4);//设置缓存
            mViewPager.setAdapter(mHomeAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
