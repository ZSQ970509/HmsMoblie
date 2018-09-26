package com.hc.hmsmoblie.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.mvp.contact.TiltSensorAbleFragmentC;
import com.hc.hmsmoblie.mvp.presenter.TiltSensorAbleFragmentP;
import com.hc.hmsmoblie.utils.TimePickerUtils;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.hc.hmsmoblie.widget.CustomLoadMoreView;

import com.hc.hmsmoblie.widget.vh.VHAdapter;
import com.hc.hmsmoblie.widget.vh.VHLayout;
import com.yc.yclibrary.base.YcMvpLazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 倾角流水数据表格
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

    private String mCamId;
    //流水数据
    private ArrayList<SensorLogJson.ListBean> mDataModels = new ArrayList<SensorLogJson.ListBean>();
    //侦测点
    private List<TiltSensorParaJson.ListBean> mParaIds = new ArrayList<TiltSensorParaJson.ListBean>();
    private int pageIndex = 1;
    private int sumPage;
    private String paraID;

    public static TiltSensorAbleFragment newInstance(String camId, List<TiltSensorParaJson.ListBean> paraIds) {
        TiltSensorAbleFragment fragment = new TiltSensorAbleFragment();
        fragment.mCamId = camId;
        fragment.mParaIds = paraIds;
        return fragment;
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
        CommonDialog.newInstanceSingle(getActivity())
                .setTitle("提示")
                .setMsg("此设备暂无倾角数据！")
                .setSingleBtnText("确定")
                .setSingleClick(v -> {
//                    getActivity().finish();
                })
                .show();
    }

    public void initData() {
        VHLayout.setHeaderListData(getResources().getStringArray(R.array.tiltSensorTitleName));
        mAdapter = new VHAdapter(R.layout.tilt_sensor_item, mDataModels);
        VHLayout.setAdapter(mAdapter);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(() -> VHLayout.getRecyclerView().postDelayed(() -> {
            if (pageIndex > sumPage) {
                showToast("已经是最后一页了");
                mAdapter.loadMoreEnd();
            } else {
                mPresenter.getTiltSensorLog(mCamId, paraID, pageIndex, 15, tiltSensorTimeStartTv.getText().toString(), tiltSensorTimeEndTv.getText().toString());
            }

        }, 1000), VHLayout.getRecyclerView());
        VHLayout.setRefresh(() -> {
            pageIndex = 1;
            mDataModels.clear();
            mPresenter.getTiltSensorLog(mCamId, paraID, pageIndex, 15, tiltSensorTimeStartTv.getText().toString(), tiltSensorTimeEndTv.getText().toString());
        });
        mPresenter.getTiltSensorLog(mCamId, paraID, pageIndex, 15, tiltSensorTimeStartTv.getText().toString(), tiltSensorTimeEndTv.getText().toString());
        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add("全部");
        for (TiltSensorParaJson.ListBean jsonBean : mParaIds) {
            dataList.add(jsonBean.getParaName());
        }
        tiltSensorTypeSp.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dataList));
    }

    public void initSpinner() {
        tiltSensorTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    paraID = "";
                } else {
                    paraID = mParaIds.get(position - 1).getParaID() + "";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @OnClick({R.id.tiltSensorTimeStartTv, R.id.tiltSensorTimeEndTv, R.id.btCruiseDataSearch})
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
                mPresenter.getTiltSensorLog(mCamId, paraID, pageIndex, 15, tiltSensorTimeStartTv.getText().toString(), tiltSensorTimeEndTv.getText().toString());
                break;
        }
    }
}
