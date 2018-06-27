package com.hc.hmsmoblie.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.adapter.SelectProjectVideoAdapter;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.json.ProjectDetailsJson;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.VideoProjectDetailsC;
import com.hc.hmsmoblie.mvp.contact.VideoSelectProjectC;
import com.hc.hmsmoblie.mvp.presenter.VideoProjectDetailsP;
import com.hc.hmsmoblie.mvp.presenter.VideoSelectProjectP;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.hc.hmsmoblie.utils.LoadImgUtils;
import com.hc.hmsmoblie.widget.CustomLoadMoreView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/11.
 */

public class VideoProjectDetailsActivity extends BaseMvpActivity<VideoProjectDetailsP> implements VideoProjectDetailsC.V {
    private static final String PRO_ID = "pro_id";
    private String mProID;
    private static final String SYS_ID = "sys_id";
    private  String sysId;
    private ProjectDetailsJson proDetailsBean;
    @BindView(R.id.video_Project_Detail_Name)
    TextView videoProjectDetailName;
    @BindView(R.id.video_Project_Detail_Type)
    TextView videoProjectDetailType;
    @BindView(R.id.video_Project_Detail_Address)
    TextView videoProjectDetailAddress;
    @BindView(R.id.video_Project_Detail_Quality_Supervision)
    TextView videoProjectDetailQualitySupervision;
    @BindView(R.id.video_Project_Detail_Safety_Supervision)
    TextView videoProjectDetailSafetySupervision;
    @BindView(R.id.video_Project_Detail_Money)
    TextView videoProjectDetailMoney;
    @BindView(R.id.video_Project_Detail_Area)
    TextView videoProjectDetailArea;
    @BindView(R.id.video_Project_Detail_Start_Time)
    TextView videoProjectDetailStartTime;
    @BindView(R.id.video_Project_Detail_End_Time)
    TextView videoProjectDetailEndTime;
    @BindView(R.id.video_Project_Detail_Humen)
    TextView videoProjectDetailHumen;
    @BindView(R.id.video_Project_Detail_Phone)
    TextView videoProjectDetailPhone;
    @BindView(R.id.video_Project_Detail_Affiliated_Area)
    TextView videoProjectDetailAffiliatedArea;
    @BindView(R.id.video_Project_Detail_Image)
    ImageView videoProjectDetailImage;
    @BindView(R.id.video_Project_Detail_Online_Time)
    RelativeLayout videoProjectDetailOnlineTime;
    @BindView(R.id.video_Project_Detail_Driver_List)
    RelativeLayout videoProjectDetailDriverList;

    public static void newInstance(Activity activity , String proId, String sysId) {
        Intent intent = new Intent(activity, VideoProjectDetailsActivity.class);
        intent.putExtra(PRO_ID, proId);
        intent.putExtra(SYS_ID, sysId);
        activity.startActivity(intent);
    }

    @Override
    protected VideoProjectDetailsP loadPresenter() {
        return new VideoProjectDetailsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_project_details_activity;
    }

    @Override
    protected void initView(Bundle bundle) {
        setToolBar("项目详情");
        sysId = getIntent().getStringExtra(SYS_ID);
        mProID = getIntent().getStringExtra(PRO_ID);
        mPresenter.getCameradetails(mProID,11);
    }

    @OnClick({R.id.video_Project_Detail_Online_Time,R.id.video_Project_Detail_Driver_List})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.video_Project_Detail_Online_Time:
               OnlineTimeActivity.newInstance(getActivity(),mProID);
                break;
            case R.id.video_Project_Detail_Driver_List:
                VideoSelectDriverActivity.newInstance(getActivity(),mProID,sysId);
                break;
        }
    }


    @Override
    public void onGetCameradetailsSuccess(ArrayList<ProjectDetailsJson> dataBean) {
        proDetailsBean = dataBean.get(0);
        videoProjectDetailName.setText("项目名称:"+ EmptyUtils.getString(proDetailsBean.getEtpName()));
        videoProjectDetailType.setText("工程类别:"+EmptyUtils.getString(proDetailsBean.getProjProperty()));
        videoProjectDetailAddress.setText("工程地址:"+EmptyUtils.getString(proDetailsBean.getProjAddress()));
        videoProjectDetailQualitySupervision.setText("工程质量监督机构:"+EmptyUtils.getString(proDetailsBean.getProjSuperviseName()));
        videoProjectDetailSafetySupervision.setText("工程安全监督机构:"+EmptyUtils.getString(proDetailsBean.getProjSafeOrgz()));
        videoProjectDetailMoney.setText("工程造价:"+proDetailsBean.getProjCost()+"元");
        videoProjectDetailArea.setText("总建筑面积:"+proDetailsBean.getProjAreaSize()+"m²");
        videoProjectDetailStartTime.setText("计划开工日期:"+EmptyUtils.getString(proDetailsBean.getProjDateStart()));
        videoProjectDetailEndTime.setText("计划竣工日期:"+EmptyUtils.getString(proDetailsBean.getProjDateComplete()));
        videoProjectDetailHumen.setText("项目负责人:"+EmptyUtils.getString(proDetailsBean.getProjChargePerson()));
        videoProjectDetailPhone.setText("联系电话:"+EmptyUtils.getString(proDetailsBean.getProjChargePersonPhone()));
        videoProjectDetailAffiliatedArea.setText("所属地区:"+proDetailsBean.getProjRegionCode());
        LoadImgUtils.loadImg(getActivity(),proDetailsBean.getProj_CAD_Pics(),videoProjectDetailImage);


    }

    @Override
    public void onGetCameradetailsFail(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
}