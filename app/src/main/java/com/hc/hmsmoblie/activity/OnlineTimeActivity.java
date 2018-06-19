package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.adapter.OnlineTimeAdapter;
import com.hc.hmsmoblie.adapter.SelectProjectVideoAdapter;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.LoginJson;
import com.hc.hmsmoblie.bean.json.OnlineTimeJson;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.LoginC;
import com.hc.hmsmoblie.mvp.contact.OnlineTimeC;
import com.hc.hmsmoblie.mvp.presenter.LoginP;
import com.hc.hmsmoblie.mvp.presenter.OnlineTimeP;
import com.hc.hmsmoblie.utils.TimePickerUtils;
import com.hc.hmsmoblie.widget.PercentageRing;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 */

public class OnlineTimeActivity extends BaseMvpActivity<OnlineTimeP> implements OnlineTimeC.V {
    private static final String PRO_ID = "pro_id";
    private String mProID;
    @BindView(R.id.tv_StartDate_OnlineTime)
    TextView tvStartDateOnlineTime;
    @BindView(R.id.tv_EndDate_OnlineTime)
    TextView tvEndDateOnlineTime;
    @BindView(R.id.btn_Search_OnlineTime)
    Button btnSearchOnlineTime;
    @BindView(R.id.rv_OnlineTime)
    RecyclerView rvOnlineTime;
    OnlineTimeAdapter onlineTimeAdapter;
    ArrayList<OnlineTimeJson.ListBean> dataList = new ArrayList<OnlineTimeJson.ListBean>();
    View headerView ;
    /**
     * 用户选中的开始时间
     */
    private String mSelectTimeStart = "";
    /**
     * 用户选中的结束时间
     */
    private String mSelectTimeEnd = "";

    public static void newInstance(Activity activity , String proId) {
        Intent intent = new Intent(activity, OnlineTimeActivity.class);
        intent.putExtra(PRO_ID, proId);
        activity.startActivity(intent);
    }
    @Override
    protected OnlineTimeP loadPresenter() {
        return new OnlineTimeP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.online_time_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("在线时间");
       mProID = getIntent().getStringExtra(PRO_ID);
        RecyclerView.LayoutManager layoutManager =
             new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        rvOnlineTime.setLayoutManager(layoutManager);
        onlineTimeAdapter = new OnlineTimeAdapter(R.layout.item_online_time, dataList);
        rvOnlineTime.setAdapter(onlineTimeAdapter);
    }


    @OnClick({R.id.btn_Search_OnlineTime,R.id.tv_StartDate_OnlineTime,R.id.tv_EndDate_OnlineTime})
    void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_StartDate_OnlineTime:

                TimePickerUtils.showPickerView(getActivity(), "开始时间", tvStartDateOnlineTime, mSelectTimeStart, "1234-10-11", tvEndDateOnlineTime.getText().toString());
                break;
            case R.id.tv_EndDate_OnlineTime:
                TimePickerUtils.showPickerView(getActivity(), "结束时间", tvEndDateOnlineTime, mSelectTimeEnd, tvStartDateOnlineTime.getText().toString(), "");
                break;
            case R.id.btn_Search_OnlineTime:
                mSelectTimeStart = tvStartDateOnlineTime.getText().toString();
                mSelectTimeEnd = tvEndDateOnlineTime.getText().toString();

                mPresenter.GetOnlineRate(mProID,mSelectTimeStart,mSelectTimeEnd);
                break;
        }
    }

    @Override
    public void onGetOnlineRateSuccess(ArrayList<OnlineTimeJson> onlineTimeJson) {
        dataList.clear();
        double sumPercent = 0;//算取平均百分率
        for (int i = 0 ; i< onlineTimeJson.get(0).getList().size() ; i++){
            sumPercent += onlineTimeJson.get(0).getList().get(i).getCamOnlineRate();
        }

        if(headerView != null) {//先把旧的头部view移除
            onlineTimeAdapter.removeHeaderView(headerView);
        }
        headerView = getHeaderView((int) (sumPercent/onlineTimeJson.get(0).getList().size()*100));
        onlineTimeAdapter.addHeaderView(headerView);
        dataList.addAll(onlineTimeJson.get(0).getList());
        onlineTimeAdapter.notifyDataSetChanged();
    }
    private View getHeaderView(int sumPercent) {
        View view = getLayoutInflater().inflate(R.layout.item_average_online_time, (ViewGroup) rvOnlineTime.getParent(), false);
        PercentageRing prAverage = (PercentageRing) view.findViewById(R.id.pr_Rate_OnlineTime_Item);
        prAverage.setTargetPercent(sumPercent);

        return view;
    }
    private View.OnClickListener getRemoveHeaderListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlineTimeAdapter.removeHeaderView(v);
            }
        };
    }
    @Override
    public void onGetOnlineRateFail(String msg) {

    }
}
