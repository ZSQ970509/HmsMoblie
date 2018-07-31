package com.hc.hmsmoblie.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.adapter.SelectProjectVideoAdapter;
import com.hc.hmsmoblie.base.BaseActivity;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.fragment.MainFragment;
import com.hc.hmsmoblie.fragment.VideoSelectProjectFragment;
import com.hc.hmsmoblie.mvp.contact.VideoSelectProjectC;
import com.hc.hmsmoblie.mvp.presenter.VideoSelectProjectP;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.hc.hmsmoblie.utils.PhoneSystemUtils;
import com.hc.hmsmoblie.widget.CustomLoadMoreView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/6/11.
 */

public class VideoSelectProjectActivity extends BaseActivity {
    private static final String SYS_ID = "sys_id";
    private  String sysId;
    @BindView(R.id.main_drawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_navigationView)
    NavigationView mNavigationView;

    @BindView(R.id.main_RelativeLayout_UpadtePass)
    RelativeLayout mainRelativeLayoutUpadtePass;
    @BindView(R.id.main_RelativeLayout_Setting)
    RelativeLayout mainRelativeLayoutSetting;
    @BindView(R.id.main_RelativeLayout_About)
    RelativeLayout mainRelativeLayoutAbout;
    @BindView(R.id.main_RelativeLayout_Exit)
    RelativeLayout mainRelativeLayoutExit;
  /*  @BindView(R.id.btn_Search_SelectProject)
    Button btnSearchSelectProject;
    @BindView(R.id.edit_Search_SelectProject)
    EditText editSearchSelectProject;
    @BindView(R.id.rv_SelectProject)
    RecyclerView recyclerViewSelectProject;
    ArrayList<ProjectJson.ListBean> dataList = new ArrayList<ProjectJson.ListBean>();
    SelectProjectVideoAdapter selectProjectVideoAdapter;
    int pageIndex = 1;
    int sumPage;*/

    public static void newInstance(Activity activity , String sysId) {
        Intent intent = new Intent(activity, VideoSelectProjectActivity.class);
        intent.putExtra(SYS_ID, sysId);
        activity.startActivity(intent);
    }

   /* @Override
    protected VideoSelectProjectP loadPresenter() {
        return new VideoSelectProjectP();
    }*/

    @Override
    protected int getLayoutId() {
        return R.layout.video_select_project_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        //setToolBar("选择项目");
        sysId = getIntent().getStringExtra(SYS_ID);
        initFragment();
        initNavigationView();
       /* showLoading("正在搜索中...");
        mPresenter.getVideoProject(editSearchSelectProject.getText().toString(), pageIndex, 10, sysId,  UserInfoPref.getUserId());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSelectProject.setLayoutManager(linearLayoutManager);
        selectProjectVideoAdapter = new SelectProjectVideoAdapter(R.layout.item_select_project, dataList);
        recyclerViewSelectProject.setAdapter(selectProjectVideoAdapter);
        //recyclerViewSelectProject.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        selectProjectVideoAdapter.setLoadMoreView(new CustomLoadMoreView());
        selectProjectVideoAdapter.setOnLoadMoreListener(() -> recyclerViewSelectProject.postDelayed(() -> {
            if (pageIndex > sumPage) {
                showToast("已经是最后一页了");
                selectProjectVideoAdapter.loadMoreEnd();
            } else {
                //sysId:11视频监控、26超视野、31梯控、21环境
                mPresenter.getVideoProject(editSearchSelectProject.getText().toString(), pageIndex, 10, sysId,  UserInfoPref.getUserId());
            }

        }, 1000), recyclerViewSelectProject);
        //selectProjectVideoAdapter.disableLoadMoreIfNotFullPage();
        selectProjectVideoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoProjectDetailsActivity.newInstance(getActivity(), ((ProjectJson.ListBean) adapter.getItem(position)).getProjID(),sysId);

            }
        });*/
    }

  /*  @OnClick({R.id.btn_Search_SelectProject})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Search_SelectProject:
                dataList.clear();
                pageIndex = 1;
                selectProjectVideoAdapter.notifyDataSetChanged();
                //sysId:11视频监控、26超视野、31梯控、21环境
                showLoading("正在搜索中...");
                mPresenter.getVideoProject(editSearchSelectProject.getText().toString(), pageIndex, 10, sysId,  UserInfoPref.getUserId());
                break;
        }
    }

    @Override
    public void onGetVideoProjectSuccess(ProjectJson dataBean) {
        if (dataBean.getList().size() == 0) {
            showToast("暂无数据！");
        } else {
           // showToast("数据加载成功！");
        }
        dataList.addAll(dataBean.getList());
        selectProjectVideoAdapter.notifyDataSetChanged();
        sumPage = (dataBean.getTotal() + 10 - 1) / 10;
        if (pageIndex <= sumPage) {
            pageIndex++;
        }
        selectProjectVideoAdapter.loadMoreComplete();

    }

    @Override
    public void onGetVideoProjectFail(String msg) {
        showToast(msg);
    }
    */
    public String iniData(){
       return sysId;
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
    private void initFragment() {
        VideoSelectProjectFragment videoSelectProjectFragment = VideoSelectProjectFragment.newInstance();
        // 添加显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_fl, videoSelectProjectFragment)
                .show(videoSelectProjectFragment)
                .commit();
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