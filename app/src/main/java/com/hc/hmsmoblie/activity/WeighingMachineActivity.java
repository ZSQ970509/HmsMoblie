package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ImageLogPanoramaListJson;
import com.hc.hmsmoblie.bean.json.LadderControlDeviceListJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineJson;
import com.hc.hmsmoblie.bean.json.WeighingMachineMsg;
import com.hc.hmsmoblie.mvp.contact.LadderControlDeviceListC;
import com.hc.hmsmoblie.mvp.contact.WeighingMachineC;
import com.hc.hmsmoblie.mvp.presenter.LadderControlDeviceListP;
import com.hc.hmsmoblie.mvp.presenter.WeighingMachineP;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.hc.hmsmoblie.utils.TimePickerUtils;
import com.hc.hmsmoblie.widget.WeighingMachineDialog;
import com.yc.yclibrary.exception.ApiException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 地磅列表
 */

public class WeighingMachineActivity extends BaseMvpActivity<WeighingMachineP> implements WeighingMachineC.V {
    @BindView(R.id.rvWeighingMachine)
    RecyclerView mRecyclerView;
    @BindView(R.id.srlWeighingMachine)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.tvWeighingMachineStartTime)
    TextView mTvStartTime;
    @BindView(R.id.tvWeighingMachineEndTime)
    TextView mTvEndTime;
    @BindView(R.id.tvWeighingMachineSearch)
    Button tvWeighingMachineSearch;
    private int mPageIndex = 1;
    private final int mPageSize = 10;
    private int mPageTotal = 1;
    private BaseItemDraggableAdapter<WeighingMachineJson.ListBean, BaseViewHolder> mAdapter;

    private String mProID;
    private static final String PRO_ID = "pro_id";

    public static void newInstance(Activity activity, String proId) {
        Intent intent = new Intent(activity, WeighingMachineActivity.class);
        intent.putExtra(PRO_ID, proId);
        activity.startActivity(intent);
    }

    @Override
    protected WeighingMachineP loadPresenter() {
        return new WeighingMachineP();
    }

    @Override
    protected int getLayoutId() {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); // 必须
        return R.layout.weight_machine_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("智能地磅");
        mProID = getIntent().getStringExtra(PRO_ID);
        mTvStartTime.setText(TimePickerUtils.getMonthFirstDay());
        mTvEndTime.setText(TimePickerUtils.getMonthToday());

        mAdapter = new BaseItemDraggableAdapter<WeighingMachineJson.ListBean, BaseViewHolder>(R.layout.weighing_machine_item, null) {
            @Override
            protected void convert(BaseViewHolder helper, WeighingMachineJson.ListBean item) {
                helper.setText(R.id.tvWeighingMachineProName, EmptyUtils.getString(item.getProjName(), "-"))
                        .setText(R.id.tvWeighingMachineSerialNumber, "流水号:" + EmptyUtils.getString(item.getSwiftNumber(), "-"))
                        .setText(R.id.tvWeighingMachineWeighingAgent, "司磅员:" + EmptyUtils.getString(item.getWeighing(), "-"))
                        .setText(R.id.tvWeighingMachineSpecifications, "品名规格:" + EmptyUtils.getString(item.getMerchandise(), "-"))
                        .setText(R.id.tvWeighingMachineDriverNum, "车牌号:" + EmptyUtils.getString(item.getPlate(), "-"))
                        .setText(R.id.tvWeighingMachineDriver, "司机:" + EmptyUtils.getString(item.getCart(), "-"))
                        .setText(R.id.tvWeighingMachineWeighingWeight, "称重重量:" + EmptyUtils.getString(item.getWeighM() + "", "-", "kg"))
                        .setText(R.id.tvWeighingMachineSettlementWeight, "结算重量:" + EmptyUtils.getString(item.getWeighW() + "", "-", "kg"))
                        .setText(R.id.tvWeighingMachineDate, "称重日期:" + EmptyUtils.getString(item.getCreateTime(), "-"));
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
        mAdapter.setOnItemClickListener((BaseQuickAdapter adapter, View view, int position) ->
                mPresenter.getWeighbridge(((WeighingMachineJson.ListBean) adapter.getItem(position)).getRecordId() + "")
        );
        initRefreshAndLoadMore();
        searchDeviceList();
    }

    private void searchDeviceList() {
        mPresenter.getWeighbridgeList(mProID, mPageIndex, mPageSize, mTvStartTime.getText().toString(), mTvEndTime.getText().toString());
    }

    @Override
    public void onGetWeighbridgeListSuccess(WeighingMachineJson weighingMachineJson) {
        mPageTotal = (weighingMachineJson.getTotal() + mPageSize - 1) / mPageSize;
        mAdapter.addData(weighingMachineJson.getList());
        finishRefreshAndLoadMore();
    }

    @Override
    public void onGetWeighbridgeListFail(ApiException apiException) {
        showToast(apiException.getMessage());
        finishRefreshAndLoadMore();
    }

    @Override
    public void onGetWeighbridgeSuccess(WeighingMachineMsg weighingMachineMsg) {
        Log.e("test", weighingMachineMsg.toString() + "");
        WeighingMachineDialog.newInstance(getActivity())
                .setData(getActivity(), weighingMachineMsg)
                .show();
    }

    @Override
    public void onGetWeighbridgeFail(ApiException apiException) {
        showToast(apiException.getMessage());
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

    @OnClick({R.id.tvWeighingMachineSearch, R.id.tvWeighingMachineEndTime, R.id.tvWeighingMachineStartTime})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvWeighingMachineSearch:
                initRefreshAndLoadMore();
                searchDeviceList();
                break;
            case R.id.tvWeighingMachineStartTime:
                TimePickerUtils.showPickerView(getActivity(), "", mTvStartTime, mTvStartTime.getText().toString(), "1234-10-11", mTvEndTime.getText().toString());
                break;
            case R.id.tvWeighingMachineEndTime:
                if (TextUtils.isEmpty(mTvStartTime.getText().toString()))
                    TimePickerUtils.showPickerView(getActivity(), "", mTvEndTime, mTvEndTime.getText().toString(), "1234-10-11", "");
                else
                    TimePickerUtils.showPickerView(getActivity(), "", mTvEndTime, mTvEndTime.getText().toString(), mTvStartTime.getText().toString(), "");
                break;
        }
    }

}
