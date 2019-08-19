package com.hc.hmsmoblie.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseActivity;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.fragment.SelectProjectFragment;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.hc.hmsmoblie.utils.PhoneSystemUtils;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivityNew extends BaseActivity {
    @BindView(R.id.main_drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_navigationView)
    NavigationView mNavigationView;

    public static void newInstance(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivityNew.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        initFragment();
        initNavigationView();
    }

    private void initFragment() {
        SelectProjectFragment selectProjectFragment = SelectProjectFragment.newInstance("11");
        // 添加显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fl, selectProjectFragment)
                .show(selectProjectFragment)
                .commit();
    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    public void toggleDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    private void initNavigationView() {
        View headerView = mNavigationView.getHeaderView(0);
        CircleImageView mUserAvatarView = (CircleImageView) headerView.findViewById(R.id.nav_header_pic);
        TextView mUserName = (TextView) headerView.findViewById(R.id.nav_header_name);
        mUserName.setText(EmptyUtils.getString(UserInfoPref.getUserName(), UserInfoPref.getUserAccount()));
        mNavigationView.setItemIconTintList(null);
    }

    @OnClick({R.id.main_RelativeLayout_UpadtePass, R.id.main_RelativeLayout_Setting, R.id.main_RelativeLayout_About, R.id.main_RelativeLayout_Exit})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_RelativeLayout_UpadtePass://修改密码
                ChangePassWordActivity.newInstance(getActivity());
                break;
            case R.id.main_RelativeLayout_Setting://权限设置（暂时隐藏）
                showToast("权限设置");
                break;
            case R.id.main_RelativeLayout_About://关于
//                showToast("关于");
                AboutActivity.newInstance(getActivity());
                break;
            case R.id.main_RelativeLayout_Exit://退出登录
//                ActivityUtils.INSTANCE.exitApp(getActivity());
                // showToast("退出");
                LoginActivity.newInstance(getActivity());
                break;
        }
    }

    /**
     * 监听back键处理DrawerLayout和SearchView
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                PhoneSystemUtils.exitApp((BaseActivity) getActivity());
            }
        }
        return true;
    }

    /**
     * 解决App重启后导致Fragment重叠的问题
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }
}
