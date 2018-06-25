package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.mikephil.charting.charts.LineChart;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.EnvironmentDeviceListJson;
import com.hc.hmsmoblie.bean.json.EnvironmentDeviceListJson;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.mvp.contact.EnvironmentDeviceListC;
import com.hc.hmsmoblie.mvp.presenter.EnvironmentDeviceListP;
import com.hc.hmsmoblie.mvp.presenter.LadderControlProjectListP;
import com.hc.hmsmoblie.utils.chart.ChartUtils;
import com.yc.yclibrary.exception.ApiException;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 环境设备列表页面
 */

public class EnvironmentDeviceListActivity extends BaseMvpActivity<EnvironmentDeviceListP> implements EnvironmentDeviceListC.V {
    @BindView(R.id.rvEnvironmentDeviceList)
    RecyclerView mRecyclerView;
    @BindView(R.id.srlEnvironmentDeviceList)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private int mPageIndex = 1;
    private final int mPageSize = 10;
    private int mPageTotal = 1;
    private BaseItemDraggableAdapter<EnvironmentDeviceListJson, BaseViewHolder> mAdapter;
    private String mProId;
    private static final String PRO_ID = "pro_id";

    public static void newInstance(Activity activity, String proId) {
        Intent intent = new Intent(activity, EnvironmentDeviceListActivity.class);
        intent.putExtra(PRO_ID, proId);
        activity.startActivity(intent);
    }

    @Override
    protected EnvironmentDeviceListP loadPresenter() {
        return new EnvironmentDeviceListP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.environment_device_list_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("选择设备");
        mProId = getIntent().getStringExtra(PRO_ID);
        mAdapter = new BaseItemDraggableAdapter<EnvironmentDeviceListJson, BaseViewHolder>(R.layout.environment_device_list_item, null) {
            @Override
            protected void convert(BaseViewHolder helper, EnvironmentDeviceListJson item) {
                helper.setText(R.id.rvEnvironmentDeviceItemProName, item.getCamName());
            }
        };
        mAdapter.setOnLoadMoreListener(() -> {
            if (mPageIndex >= mPageTotal) {
                showToast("已经是最后一页了！");
                mAdapter.loadMoreEnd();
            } else {
                mPageIndex++;
                searchPro();
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            initRefreshAndLoadMore();
            searchPro();
        });
        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) ->{
            EnvironmentDeviceListJson item =(EnvironmentDeviceListJson) adapter.getItem(position);
            EnvironmentDetailsActivity.newInstance(getActivity(),item.getCamId(),item.getCamSeqId(),item.getCamProjId());
        });
        initRefreshAndLoadMore();
        searchPro();
    }

    private void searchPro() {
        mPresenter.getEnvironmentDeviceList(mProId);
    }

    @Override
    public void onEnvironmentDeviceListSuccess(List<EnvironmentDeviceListJson> EnvironmentDeviceListJson) {
        mAdapter.addData(EnvironmentDeviceListJson);
        finishRefreshAndLoadMore();
    }

    @Override
    public void onEnvironmentDeviceListFail(ApiException apiException) {
        showToast(apiException.getMessage());
        finishRefreshAndLoadMore();
    }

    private void initRefreshAndLoadMore() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPageIndex = 1;
        mPageTotal = 1;
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