package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.VideoDriverJson;
import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.bean.json.VideoDriverJson;
import com.hc.hmsmoblie.bean.type.ProTypeEnum;
import com.hc.hmsmoblie.mvp.contact.EnvironmentDeviceListC;
import com.hc.hmsmoblie.mvp.contact.LadderControlDeviceListC;
import com.hc.hmsmoblie.mvp.contact.VideoSelectDriverC;
import com.hc.hmsmoblie.mvp.presenter.EnvironmentDeviceListP;
import com.hc.hmsmoblie.mvp.presenter.LadderControlDeviceListP;
import com.hc.hmsmoblie.mvp.presenter.VideoSelectDriverP;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.yc.yclibrary.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 影像日志设备列表
 */

public class ImageLogDeviceListActivity extends BaseMvpActivity<VideoSelectDriverP> implements VideoSelectDriverC.V {
    @BindView(R.id.rvEnvironmentDeviceList)
    RecyclerView mRecyclerView;
    @BindView(R.id.srlEnvironmentDeviceList)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private BaseItemDraggableAdapter<VideoDriverJson, BaseViewHolder> mAdapter;
    private String mProId;
    private static final String PRO_ID = "pro_id";

    public static void newInstance(Activity activity, String proId) {
        Intent intent = new Intent(activity, ImageLogDeviceListActivity.class);
        intent.putExtra(PRO_ID, proId);
        activity.startActivity(intent);
    }

    @Override
    protected VideoSelectDriverP loadPresenter() {
        return new VideoSelectDriverP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.environment_device_list_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("选择设备");
        mProId = getIntent().getStringExtra(PRO_ID);
        mAdapter = new BaseItemDraggableAdapter<VideoDriverJson, BaseViewHolder>(R.layout.environment_device_list_item, null) {
            @Override
            protected void convert(BaseViewHolder helper, VideoDriverJson item) {
                helper.setText(R.id.rvEnvironmentDeviceItemProName, item.getCamName());
            }
        };
        mAdapter.setOnLoadMoreListener(() -> {
            showToast("已经是最后一页了！");
            mAdapter.loadMoreEnd();
        }, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            initRefreshAndLoadMore();
            searchPro();
        });
        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) -> {
            VideoDriverJson item = (VideoDriverJson) adapter.getItem(position);
            ImageLogPanoramaListActivity.newInstance(getActivity(), item.getCamId() + "");
        });
        initRefreshAndLoadMore();
        searchPro();
    }

    private void searchPro() {
        mPresenter.getCameraListdetails(mProId, ProTypeEnum.IMG_LOG.getTypeId());
    }

    @Override
    public void onGetCameraListdetailsSuccess(ArrayList<VideoDriverJson> dataBean) {
        mAdapter.addData(dataBean);
        finishRefreshAndLoadMore();
    }

    @Override
    public void onGetCameraListdetailsFail(String msg) {
        showToast(msg);
        finishRefreshAndLoadMore();
    }

    private void initRefreshAndLoadMore() {
        mSwipeRefreshLayout.setRefreshing(true);
        mAdapter.setNewData(null);//重新开启下拉加载更多
    }

    private void finishRefreshAndLoadMore() {
        if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if (mAdapter != null && mAdapter.isLoading()) {
            mAdapter.loadMoreComplete();
        }
        hideLoading();
    }
}