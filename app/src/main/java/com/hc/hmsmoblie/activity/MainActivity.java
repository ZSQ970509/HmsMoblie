package com.hc.hmsmoblie.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.fragment.MainFragment;
import com.yc.yclibrary.base.YcAppCompatActivity;

import butterknife.BindView;

public class MainActivity extends YcAppCompatActivity {
    @BindView(R.id.main_drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_navigationView)
    NavigationView mNavigationView;
    public static void newInstance(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
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
        MainFragment mainFragment = MainFragment.newInstance();
        // 添加显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fl, mainFragment)
                .show(mainFragment)
                .commit();
    }

    private void initNavigationView() {
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //隐藏侧滑栏
                mDrawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.nav_menu1:
                        showToast("点击了菜单1");
                        return true;
                    case R.id.nav_menu2:
                        showToast("点击了菜单2");
                        return true;
                    case R.id.nav_menu3:
                        showToast("点击了菜单3");
                        return true;
                    case R.id.nav_menu4:
                        showToast("点击了菜单4");
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * 监听back键处理DrawerLayout和SearchView
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(mDrawerLayout.getChildAt(1))) {
                mDrawerLayout.closeDrawers();
            } else {
//                exitApp();
            }
        }
        return true;
    }
    /**
     * 解决App重启后导致Fragment重叠的问题
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {}
}
