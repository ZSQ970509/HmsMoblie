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
import com.hc.hmsmoblie.adapter.SelectDriverVideoAdapter;
import com.hc.hmsmoblie.base.BaseMvpActivity;
import com.hc.hmsmoblie.bean.domain.VideoBean;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.VideoDriverJson;
import com.hc.hmsmoblie.db.UserInfoPref;
import com.hc.hmsmoblie.mvp.contact.VideoSelectDriverC;
import com.hc.hmsmoblie.mvp.contact.VideoSelectProjectC;
import com.hc.hmsmoblie.mvp.presenter.VideoSelectDriverP;
import com.hc.hmsmoblie.mvp.presenter.VideoSelectProjectP;
import com.hc.hmsmoblie.utils.EmptyUtils;
import com.hc.hmsmoblie.utils.VideoBeanXmlUtil;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.hc.hmsmoblie.widget.CommonListDialog;
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
    private static final String SYS_ID = "sys_id";
    private String sysId;
    @BindView(R.id.rv_SelectDriver)
    RecyclerView recyclerViewSelectDriver;
    ArrayList<VideoDriverJson> dataList = new ArrayList<VideoDriverJson>();
    SelectDriverVideoAdapter selectDriverVideoAdapter;

    public static void newInstance(Activity activity, String proId, String sysId) {
        Intent intent = new Intent(activity, VideoSelectDriverActivity.class);
        intent.putExtra(PRO_ID, proId);
        intent.putExtra(SYS_ID, sysId);
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
        sysId = getIntent().getStringExtra(SYS_ID);
        mProID = getIntent().getStringExtra(PRO_ID);
//        mProID = "239";
//        sysId = "58";
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSelectDriver.setLayoutManager(linearLayoutManager);
        selectDriverVideoAdapter = new SelectDriverVideoAdapter(R.layout.item_select_driver_video, dataList);
        recyclerViewSelectDriver.setAdapter(selectDriverVideoAdapter);
        selectDriverVideoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    VideoDriverJson item = dataList.get(position);
                    //判断设备是否有绑定，无绑定不可看视频
                    if(dataList.get(position).getCam_Config()!= null) {
                        VideoBean videoBean = VideoBeanXmlUtil.parseXMLWithPull(dataList.get(position).getCam_Config());

                        //videoBean.setCam_Dx_Puid(dataList.get(position).getCam_DX_PUID());
                        videoBean.setCam_Dx_Puid(dataList.get(position).getCam_DX_VideoId());
                        videoBean.setCamFlowState(dataList.get(position).getCamFlowState() + "");
                        videoBean.setVideoId(dataList.get(position).getVideoId() + "");
                        if(item.getCamTypeId().equals("501")){
                            //地磅的条件不满足
                            WeighingMachineActivity.newInstance(getActivity(), mProID);
                        } else   if (item.getCamTypeId().equals("401") || item.getCamTypeId().equals("421")) {
                            //塔吊
                            LadderControlDeviceListActivity.newInstance(getActivity(), mProID, item.getCamId());
                        } else if (item.getCamTypeId().equals("210") || item.getCamTypeId().equals("230")) {
                            //环境
                            EnvironmentDetailsActivity.newInstance(getActivity(), item.getCamId(), item.getCamSeqId(), item.getCamProjId());
                        } else if (item.getCamTypeId().equals("118") || item.getCamTypeId().equals("128") || item.getCamTypeId().equals("116") || item.getCamTypeId().equals("136")
                                || item.getCamTypeId().equals("117") || item.getCamTypeId().equals("137") || item.getCamTypeId().equals("115") || item.getCamTypeId().equals("135")
                                || Integer.parseInt(item.getCamTypeId()) <= 110) {
                            if (item.getCamTypeId().equals("103") || item.getCamTypeId().equals("118") || item.getCamTypeId().equals("128")) {

                                if(item.getSysID().equals("58")){
                                CommonListDialog.newInstance(getActivity())
                                        .setTextViewDipAngleClick(v -> {
                                            TiltSensorActivity.newInstance(getActivity(), item.getCamId() + "");
//                                            DipRealTimeDataActivity.newInstance(getActivity(), item.getCamId() + "");
                                        })
                                        .setTextViewImageLogClick(v -> {
                                            ImageLogPanoramaListActivity.newInstance(getActivity(), item.getCamId() + "");
                                        })
                                        .setTextViewVideoClick(v -> {
                                            if (videoBean.getCamFlowState().equals("15")) {
                                                String type = videoBean.getmType();
                                                //2,5,8为互信、3中星微2.1、7中星微3.3、15海康8700
                                                if (type.equals("2") || type.equals("5") || type.equals("8")) {
                                                    HuXinVideoActivity.newInstance(getActivity(), videoBean);
//                                        JumpToUtils.toHuXinVideoActivity(getActivity(), ivms_8700_bean);
                                                } else if (type.equals("15")) {//海康8700
//                                        JumpToUtils.toHKVideoActivity(getActivity(), ivms_8700_bean);


                                                    HKVideoActivity.newInstance(getActivity(), videoBean);
                                                } else if(type.equals("13")){//慧眼视频
                                                    //HuiYanVideoActivity.newInstance(getActivity(),videoBean);
                                                }else{
                                                    showToast("此视频暂不支持播放");
//                                        JumpToUtils.toRtspVideoAc(getActivity(), ivms_8700_bean.getmRtsp());
                                                }
                                            } else {
                                                showToast("此视频维护或不在线");
                                            }
                                        })
                                        .show();

                                }else {
                                    CommonListDialog.newInstanceType(getActivity(),2)
                                            .setTextViewImageLogClick(v -> {
                                                ImageLogPanoramaListActivity.newInstance(getActivity(), item.getCamId() + "");
                                            })
                                            .setTextViewVideoClick(v -> {
                                                if (videoBean.getCamFlowState().equals("15")) {
                                                    String type = videoBean.getmType();
                                                    //2,5,8为互信、3中星微2.1、7中星微3.3、15海康8700
                                                    if (type.equals("2") || type.equals("5") || type.equals("8")) {
                                                        HuXinVideoActivity.newInstance(getActivity(), videoBean);
//                                        JumpToUtils.toHuXinVideoActivity(getActivity(), ivms_8700_bean);
                                                    } else if (type.equals("15")) {//海康8700
//                                        JumpToUtils.toHKVideoActivity(getActivity(), ivms_8700_bean);

                                                        HKVideoActivity.newInstance(getActivity(), videoBean);
                                                    }  else if(type.equals("13")){//慧眼视频
                                                        //HuiYanVideoActivity.newInstance(getActivity(),videoBean);
                                                    }else {
                                                        showToast("此视频暂不支持播放");
//                                        JumpToUtils.toRtspVideoAc(getActivity(), ivms_8700_bean.getmRtsp());

                                                    }
                                                } else {
                                                    showToast("此视频维护或不在线");
                                                }
                                            })
                                            .show();
                                }
                            } else {
                                if (videoBean.getCamFlowState().equals("15")) {
                                    String type = videoBean.getmType();
                                    //2,5,8为互信、3中星微2.1、7中星微3.3、15海康8700
                                    if (type.equals("2") || type.equals("5") || type.equals("8")) {
                                        HuXinVideoActivity.newInstance(getActivity(), videoBean);
//                                        JumpToUtils.toHuXinVideoActivity(getActivity(), ivms_8700_bean);
                                    } else if (type.equals("15")) {//海康8700
//                                        JumpToUtils.toHKVideoActivity(getActivity(), ivms_8700_bean);
                                        HKVideoActivity.newInstance(getActivity(), videoBean);
                                    }  else if(type.equals("13")){//慧眼视频
                                        //HuiYanVideoActivity.newInstance(getActivity(),videoBean);

                                    }else {
                                        showToast("此视频暂不支持播放");
//                                        JumpToUtils.toRtspVideoAc(getActivity(), ivms_8700_bean.getmRtsp());

                                    }
                                } else {
                                    showToast("此视频维护或不在线");
                                }
                            }
                        }
                    }else{

                        if(item.getCamTypeId().equals("501")){
                            //地磅的条件不满足
                            WeighingMachineActivity.newInstance(getActivity(), mProID);
                        } else {
                            CommonListDialog.newInstanceType(getActivity(),3)
                                    .setTextViewDipAngleClick(v -> {
                                        TiltSensorActivity.newInstance(getActivity(), item.getCamId() + "");
                                    })
                                    .setTextViewImageLogClick(v -> {
                                        ImageLogPanoramaListActivity.newInstance(getActivity(), item.getCamId() + "");
                                    })
                                    .show();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        selectDriverVideoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                VideoDriverJson item = dataList.get(position);
                switch (view.getId()) {
                    case R.id.iv_Driver_Panorama:
                        //MonitorID1 Aha1 Ava1 Shift1 Path1a nextPath1a oldPath1a
                        ImageLogPanoramaListActivity.newInstance(getActivity(), item.getCamId() + "");
                        break;
                }
            }
        });
        showLoading("正在搜索中...");
        mPresenter.getCameraListdetails(mProID, sysId);
    }

    @Override
    public void onGetCameraListdetailsSuccess(ArrayList<VideoDriverJson> dataBean) {
        dataList.clear();
        if (dataBean.size() == 0) {
            showToast("暂无数据！");
        } else {
            // showToast("数据加载成功！");
        }
        dataList.addAll(dataBean);
        selectDriverVideoAdapter.notifyDataSetChanged();

    }

    @Override
    public void onGetCameraListdetailsFail(String msg) {
        showToast(msg);
    }
}