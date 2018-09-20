package com.hc.hmsmoblie.fragment;

import android.support.v7.widget.LinearLayoutManager;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.adapter.SelectProjectVideoAdapter;
import com.hc.hmsmoblie.bean.json.TiltSensorDataJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.widget.CustomLoadMoreView;

import com.hc.hmsmoblie.widget.vh.VHAdapter;
import com.hc.hmsmoblie.widget.vh.VHAdapterOld2;
import com.hc.hmsmoblie.widget.vh.VHLayout;
import com.hc.hmsmoblie.widget.vh.VHViewHolder;
import com.yc.yclibrary.base.YcLazyFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 *  倾角流水数据表格
 */

public class TiltSensorAbleFragment extends YcLazyFragment {
    @BindView(R.id.VHLayout)
    VHLayout VHLayout;
    ArrayList mDataModels = new ArrayList<>();
    private int mPageIndex = 1;
    private final int mPageSize = 10;
    private int mPageTotal = 10;
    public static TiltSensorAbleFragment newInstance(){
        return new TiltSensorAbleFragment();
    }
    @Override
    public int getLayoutResId() {
        return R.layout.tilt_sensor_able_fragent;
    }
    VHAdapter mAdapter;

    @Override
    public void initView() {
        mDataModels = getData();
/*        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());*/
        VHLayout.setHeaderListData(getResources().getStringArray(R.array.tiltSensorTitleName));
       /* VHLayout.getRecyclerView().setLayoutManager(linearLayoutManager);*/
        mAdapter = new VHAdapter(R.layout.tilt_sensor_item,mDataModels);
        VHLayout.setAdapter(mAdapter);
        //recyclerViewSelectProject.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(() -> VHLayout.getRecyclerView().postDelayed(() -> {
            if (mPageIndex > mPageTotal) {
                showToast("已经是最后一页了");
                mAdapter.loadMoreEnd();
            } else {
                //sysId:11视频监控、26超视野、31梯控、21环境
                mDataModels.addAll(getData());
                mAdapter.notifyDataSetChanged();
                mAdapter.loadMoreComplete();
            }

        }, 1000),VHLayout.getRecyclerView());
//        mAdapter.setOnLoadMoreListener(() -> {
//            if (mPageIndex >= mPageTotal) {
//                showToast("已经是最后一页了！");
//                mAdapter.loadMoreEnd();
//            } else {
//                mPageIndex++;
////                searchDeviceList();
//            }
//        }, VHLayout.getRecyclerView());
    }
    public ArrayList getData(){
        ArrayList mDataModels = new ArrayList<>();
        for(int i=0;i<20;i++) {
            TiltSensorDataJson coinInfo = new TiltSensorDataJson();
            coinInfo.name = ""+(20-i);
            coinInfo.priceLast="20.0";
            coinInfo.riseRate24="0.2";
            coinInfo.vol24="10020";
            coinInfo.close="22.2";
            coinInfo.open="40.0";
            coinInfo.bid="33.2";
            coinInfo.ask="19.0";
            coinInfo.amountPercent = "33.3%";
            mDataModels.add(coinInfo);
        }
        return  mDataModels;
    }
}
