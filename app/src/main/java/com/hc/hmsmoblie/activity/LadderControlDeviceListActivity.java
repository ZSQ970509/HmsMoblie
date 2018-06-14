package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.mvp.contact.LadderControlDeviceListC;
import com.hc.hmsmoblie.mvp.presenter.LadderControlDeviceListP;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.yc.yclibrary.exception.ApiException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 梯控设备列表
 */

public class LadderControlDeviceListActivity extends BaseMvpActivity<LadderControlDeviceListP> implements LadderControlDeviceListC.V {
    @BindView(R.id.rvLadderControl)
    RecyclerView mRecyclerView;
    @BindView(R.id.edtLadderControl)
    EditText mEdtKey;
    @BindView(R.id.srlLadderControl)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private int mPageIndex = 1;
    private final int mPageSize = 10;
    private int mPageTotal = 1;
    private BaseItemDraggableAdapter<LadderControlDeviceListJson.ListBean, BaseViewHolder> mAdapter;
    private String mProID;
    private static final String PRO_ID = "pro_id";

    public static void newInstance(Activity activity, String proId) {
        Intent intent = new Intent(activity, LadderControlDeviceListActivity.class);
        intent.putExtra(PRO_ID, proId);
        activity.startActivity(intent);
    }

    @Override
    protected LadderControlDeviceListP loadPresenter() {
        return new LadderControlDeviceListP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ladder_control_projcet_list_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("选择设备");
        mEdtKey.setHint("请输入设备名称");
        mProID = getIntent().getStringExtra(PRO_ID);
        mAdapter = new BaseItemDraggableAdapter<LadderControlDeviceListJson.ListBean, BaseViewHolder>(R.layout.ladder_control_device_item, null) {
            @Override
            protected void convert(BaseViewHolder helper, LadderControlDeviceListJson.ListBean item) {
                helper.setText(R.id.tvLadderControlDeviceName, "设备名称：" + EmptyUtils.getString(item.getDeviceName()))
                        .setText(R.id.tvLadderControlDeviceHeight, "高度行程：" + item.getDistance())
                        .setText(R.id.tvLadderControlDeviceRemoveState, "防拆状态：" + EmptyUtils.getString((item.getTamper())))
                        .setText(R.id.tvLadderControlDeviceState, item.getState() == 0 ? "状态：打开" : "状态：关闭")
                        .setText(R.id.tvLadderControlDeviceBattle, "电池电量：" + EmptyUtils.getString(item.getBattleVolta()))
                        .setText(R.id.tvLadderControlDeviceBattleState, "充电状态：" + EmptyUtils.getString(item.getCharging()))
                        .setText(R.id.tvLadderControlDeviceTime, "最后一次操作：" + EmptyUtils.getString(item.getLastTime(), "暂时没有任何操作"));
            }
        };
        mAdapter.setOnLoadMoreListener(() -> {
            if (mPageIndex >= mPageTotal) {
                showToast("已经是最后一页了！");
                mAdapter.loadMoreEnd();
            } else {
                mPageIndex++;
                searchDeviceList();
            }
        }, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            initRefreshAndLoadMore();
            searchDeviceList();
        });
        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) -> {
//                JumpAc.toTakePhotoInstallOpenSelectTypeAc(getActivity(), mProjectListData.get(position), mProTypeId, mProTypeName);
        });

    }

    private void searchDeviceList() {
        mPresenter.getDeviceList(mEdtKey.getText().toString(), mPageIndex, mPageSize, mProID);
    }

    @Override
    public void onGetDeviceListSuccess(LadderControlDeviceListJson LadderControlDeviceListJson) {
        mPageTotal = (LadderControlDeviceListJson.getTotal() + mPageSize - 1) / mPageSize;
        mAdapter.addData(LadderControlDeviceListJson.getList());
        finishRefreshAndLoadMore();
    }

    @Override
    public void onGetDeviceListFail(ApiException apiException) {
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

    @OnClick({R.id.btnLadderControl})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLadderControl:
                initRefreshAndLoadMore();
                searchDeviceList();
                break;
        }
    }

}
