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
    @BindView(R.id.rv_SelectDriver)
    RecyclerView recyclerViewSelectDriver;
    ArrayList<VideoDriverJson> dataList = new ArrayList<VideoDriverJson>();
    SelectDriverVideoAdapter selectDriverVideoAdapter;

    public static void newInstance(Activity activity, String proId) {
        Intent intent = new Intent(activity, VideoSelectDriverActivity.class);
        intent.putExtra(PRO_ID, proId);
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
        mProID = getIntent().getStringExtra(PRO_ID);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewSelectDriver.setLayoutManager(linearLayoutManager);
        selectDriverVideoAdapter = new SelectDriverVideoAdapter(R.layout.item_select_driver_video, dataList);
        recyclerViewSelectDriver.setAdapter(selectDriverVideoAdapter);
        selectDriverVideoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    VideoBean videoBean  = VideoBeanXmlUtil.parseXMLWithPull(dataList.get(position).getCam_Config());

                    //videoBean.setCam_Dx_Puid(dataList.get(position).getCam_DX_PUID());
                    videoBean.setCam_Dx_Puid(dataList.get(position).getCam_DX_VideoId());
                    videoBean.setCamFlowState(dataList.get(position).getCamFlowState() + "");
                    videoBean.setVideoId(dataList.get(position).getVideoId() + "");
                    if (videoBean.getCamFlowState().equals("15")) {
                        String type = videoBean.getmType();
                        //2,5,8为互信、3中星微2.1、7中星微3.3、15海康8700
                        if (type.equals("2") || type.equals("5") || type.equals("8")) {
                            HuXinVideoActivity.newInstance(getActivity(),videoBean);
//                                        JumpToUtils.toHuXinVideoActivity(getActivity(), ivms_8700_bean);
                        } else if (type.equals("15")) {//海康8700
//                                        JumpToUtils.toHKVideoActivity(getActivity(), ivms_8700_bean);
                            HKVideoActivity.newInstance(getActivity(),videoBean);
                        } else {
//                                        JumpToUtils.toRtspVideoAc(getActivity(), ivms_8700_bean.getmRtsp());

                        }
                    } else {
                        showToast("此视频维护或不在线");
                    }
                    //VideoProjectDetailsActivity.newInstance(getActivity(), ((ProjectJson.ListBean) adapter.getItem(position)).getProjID());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*if (dataList.get(position).getCamFlowState() != 16) {
                    if (EmptyUtils.getString(dataList.get(position).getDevStatus()).equals("1")) {
                        //通电";
                        if (EmptyUtils.getString(dataList.get(0).getDevNetStatus()).equals("1")) {
                            //通网";
                            try {
                                VideoBean videoBean  = VideoBeanXmlUtil.parseXMLWithPull(dataList.get(position).getCam_Config());

                                //videoBean.setCam_Dx_Puid(dataList.get(position).getCam_DX_PUID());
                                videoBean.setCam_Dx_Puid(dataList.get(position).getCam_DX_VideoId());
                                videoBean.setCamFlowState(dataList.get(position).getCamFlowState() + "");
                                if (videoBean.getCamFlowState().equals("15")) {
                                    String type = videoBean.getmType();
                                    //2,5,8为互信、3中星微2.1、7中星微3.3、15海康8700
                                    if (type.equals("2") || type.equals("5") || type.equals("8")) {
                                        HuXinVideoActivity.newInstance(getActivity(),videoBean);
//                                        JumpToUtils.toHuXinVideoActivity(getActivity(), ivms_8700_bean);
                                    } else if (type.equals("15")) {//海康8700
//                                        JumpToUtils.toHKVideoActivity(getActivity(), ivms_8700_bean);
                                   } else {
//                                        JumpToUtils.toRtspVideoAc(getActivity(), ivms_8700_bean.getmRtsp());

                                    }
                                } else {
                                    showToast("此视频维护或不在线");
                                }
                            //VideoProjectDetailsActivity.newInstance(getActivity(), ((ProjectJson.ListBean) adapter.getItem(position)).getProjID());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (EmptyUtils.getString(dataList.get(0).getDevNetStatus()).equals("0")) {
                            //断网";
                            CommonDialog.newInstance(getActivity())
                                    .setTitle("警告")
                                    .setMsg("设备处于断网状态!")
                                    .setSingleBtnText("确定")
                                    .show();
                        } else {
                            //未开通断网设备";
                            CommonDialog.newInstance(getActivity())
                                    .setTitle("警告")
                                    .setMsg("未开通断网设备!")
                                    .setSingleBtnText("确定")
                                    .show();
                        }

                    } else if (EmptyUtils.getString(dataList.get(position).getDevStatus()).equals("0")) {
                        //断电";
                        CommonDialog.newInstance(getActivity())
                                .setTitle("警告")
                                .setMsg("设备处于断电状态!")
                                .setSingleBtnText("确定")
                                .show();
                    } else {
                        //未开通断电设备";
                        CommonDialog.newInstance(getActivity())
                                .setTitle("警告")
                                .setMsg("未开通断电设备!")
                                .setSingleBtnText("确定")
                                .show();
                    }

                }else{
                    //设备维修中
                    CommonDialog.newInstance(getActivity())
                            .setTitle("警告")
                            .setMsg("设备维护中...")
                            .setSingleBtnText("确定")
                            .show();
                }*/
            }
        });
        showLoading("正在搜索中...");
        mPresenter.getCameraListdetails(mProID,"11");
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