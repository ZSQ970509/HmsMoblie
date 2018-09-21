package com.hc.hmsmoblie.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.adapter.SelectProjectVideoAdapter;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.TiltSensorDataJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.TiltSensorAbleFragmentC;
import com.hc.hmsmoblie.mvp.contact.VideoSelectProjectFragmentC;
import com.hc.hmsmoblie.mvp.presenter.TiltSensorAbleFragmentP;
import com.hc.hmsmoblie.mvp.presenter.VideoSelectProjectFragmentP;
import com.hc.hmsmoblie.utils.TimePickerUtils;
import com.hc.hmsmoblie.widget.CustomLoadMoreView;

import com.hc.hmsmoblie.widget.vh.VHAdapter;
import com.hc.hmsmoblie.widget.vh.VHAdapterOld2;
import com.hc.hmsmoblie.widget.vh.VHLayout;
import com.hc.hmsmoblie.widget.vh.VHViewHolder;
import com.yc.yclibrary.base.YcLazyFragment;
import com.yc.yclibrary.base.YcMvpLazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *  倾角流水数据表格
 */

public class TiltSensorAbleFragment extends YcMvpLazyFragment<TiltSensorAbleFragmentP> implements TiltSensorAbleFragmentC.V {
    @BindView(R.id.VHLayout)
    VHLayout VHLayout;
    @BindView(R.id.tiltSensorTypeSp)
    Spinner tiltSensorTypeSp;
    @BindView(R.id.tiltSensorTimeEndTv)
    TextView tiltSensorTimeEndTv;
    @BindView(R.id.tiltSensorTimeStartTv)
    TextView tiltSensorTimeStartTv;
    @BindView(R.id.btCruiseDataSearch)
    ImageView   btCruiseDataSearch;
    //流水数据
    ArrayList<SensorLogJson.ListBean> mDataModels = new ArrayList<SensorLogJson.ListBean>();
    //侦测点
    ArrayList<TiltSensorParaJson.ListBean>  tiltSensorParas = new ArrayList<TiltSensorParaJson.ListBean>();
    int pageIndex = 1;
    int sumPage;
    String paraID ;
    public static TiltSensorAbleFragment newInstance(){
        return new TiltSensorAbleFragment();
    }
    @Override
    protected TiltSensorAbleFragmentP loadPresenter() {
        return new TiltSensorAbleFragmentP();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.tilt_sensor_able_fragent;
    }
    VHAdapter mAdapter;

    @Override
    public void initView() {
        initSpinner();
        initData();

    }


    @Override
    public void onGetTiltSensorLogSuccess(SensorLogJson sensorLogJson) {
        if (sensorLogJson.getList().size() == 0) {
            showToast("暂无数据！");
        } else {
            // showToast("数据加载成功！");
        }
        mDataModels.addAll(sensorLogJson.getList());
        mAdapter.notifyDataSetChanged();
        sumPage = (sensorLogJson.getTotal() + 15 - 1) / 15;
        if (pageIndex <= sumPage) {
            pageIndex++;
        }
        mAdapter.loadMoreComplete();
        VHLayout.finishRefresh();
    }

    @Override
    public void onGetTiltSensorLogFail(String msg) {

    }

    @Override
    public void onGetGetTiltSensorParauccess(TiltSensorParaJson dataBean) {
        mPresenter.getTiltSensorLog("1053613",paraID,pageIndex,15,tiltSensorTimeStartTv.getText().toString(),tiltSensorTimeEndTv.getText().toString());
        tiltSensorParas.addAll(dataBean.getList());
        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add("全部");
        for (TiltSensorParaJson.ListBean jsonBean:dataBean.getList()) {
            dataList.add(jsonBean.getParaName());
        }
        tiltSensorTypeSp.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,dataList));
    }

    @Override
    public void onGetGetTiltSensorParaFail(String msg) {

    }
    public void initData(){
        mPresenter.getGetTiltSensorPara("1053613");
        VHLayout.setHeaderListData(getResources().getStringArray(R.array.tiltSensorTitleName));
        mAdapter = new VHAdapter(R.layout.tilt_sensor_item,mDataModels);
        VHLayout.setAdapter(mAdapter);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(() -> VHLayout.getRecyclerView().postDelayed(() -> {
            if (pageIndex > sumPage) {
                showToast("已经是最后一页了");
                mAdapter.loadMoreEnd();
            } else {
                //sysId:11视频监控、26超视野、31梯控、21环境
                mPresenter.getTiltSensorLog("1053613",paraID,pageIndex,15,tiltSensorTimeStartTv.getText().toString(),tiltSensorTimeEndTv.getText().toString());
            }

        }, 1000),VHLayout.getRecyclerView());
        VHLayout.setRefresh(()->{
            pageIndex = 1;
            mDataModels.clear();
            mPresenter.getTiltSensorLog("1053613",paraID,pageIndex,15,tiltSensorTimeStartTv.getText().toString(),tiltSensorTimeEndTv.getText().toString());
        });
    }
    public void initSpinner(){
        tiltSensorTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    paraID = "";
                }else{
                    paraID = tiltSensorParas.get(position-1).getParaID()+"";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    @OnClick({R.id.tiltSensorTimeStartTv, R.id.tiltSensorTimeEndTv,R.id.btCruiseDataSearch})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiltSensorTimeStartTv:
                TimePickerUtils.showPickerView(getActivity(), "", tiltSensorTimeStartTv, tiltSensorTimeStartTv.getText().toString(), "1234-10-11", tiltSensorTimeEndTv.getText().toString());
                break;
            case R.id.tiltSensorTimeEndTv:
                if (TextUtils.isEmpty(tiltSensorTimeStartTv.getText().toString()))
                    TimePickerUtils.showPickerView(getActivity(), "", tiltSensorTimeEndTv, tiltSensorTimeEndTv.getText().toString(), "1234-10-11", "");
                else
                    TimePickerUtils.showPickerView(getActivity(), "", tiltSensorTimeEndTv, tiltSensorTimeEndTv.getText().toString(), tiltSensorTimeStartTv.getText().toString(), "");
                break;
            case R.id.btCruiseDataSearch:
                pageIndex = 1;
                mDataModels.clear();
                mPresenter.getTiltSensorLog("1053613",paraID,pageIndex,15,tiltSensorTimeStartTv.getText().toString(),tiltSensorTimeEndTv.getText().toString());
                break;
        }
    }
}
