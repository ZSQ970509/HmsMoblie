package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.adapter.SelectProjectVideoAdapter;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.VideoDriverJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.VideoSelectDriverC;
import com.hc.hmsmoblie.mvp.contact.VideoSelectProjectC;
import com.hc.hmsmoblie.mvp.presenter.VideoSelectDriverP;
import com.hc.hmsmoblie.mvp.presenter.VideoSelectProjectP;
import com.hc.hmsmoblie.widget.CustomLoadMoreView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/11.
 */

public class VideoSelectDriverActivity extends BaseMvpActivity<VideoSelectDriverP> implements VideoSelectDriverC.V {
    private static final String PRO_ID = "pro_id";
    private String mProID;
    @BindView(R.id.rv_SelectDriver)
    RecyclerView recyclerViewSelectDriver;
    ArrayList<ProjectJson.ListBean> dataList = new ArrayList<ProjectJson.ListBean>();
    SelectProjectVideoAdapter selectProjectVideoAdapter;

    public static void newInstance(Activity activity, String proId) {
        Intent intent = new Intent(activity, VideoSelectDriverActivity.class);
        intent.putExtra(PRO_ID, proId);
        activity.startActivity(intent);
    }

    @Override
    protected VideoSelectDriverP loadPresenter() {
        return new VideoSelectDriverP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_select_driver_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("设备列表");
        mProID = getIntent().getStringExtra(PRO_ID);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSelectDriver.setLayoutManager(linearLayoutManager);
        selectProjectVideoAdapter = new SelectProjectVideoAdapter(R.layout.item_select_project, dataList);
        recyclerViewSelectDriver.setAdapter(selectProjectVideoAdapter);
        selectProjectVideoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                VideoProjectDetailsActivity.newInstance(getActivity(), ((ProjectJson.ListBean) adapter.getItem(position)).getProjID());

            }
        });
        showLoading("正在搜索中...");
        mPresenter.getCameraListdetails(mProID,"11");
    }

    @Override
    public void onGetCameraListdetailsSuccess(ArrayList<VideoDriverJson> dataBean) {
        if (dataBean.get(0).getList().size() == 0) {
            showToast("暂无数据！");
        } else {
            showToast("数据加载成功！");
        }
       // dataList.addAll(dataBean.get(0).getList());
        //selectProjectVideoAdapter.notifyDataSetChanged();

    }

    @Override
    public void onGetCameraListdetailsFail(String msg) {
        showToast(msg);
    }
}