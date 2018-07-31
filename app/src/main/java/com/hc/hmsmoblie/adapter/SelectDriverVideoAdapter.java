package com.hc.hmsmoblie.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.VideoDriverJson;
import com.hc.hmsmoblie.utils.EmptyUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/11.
 */

public class SelectDriverVideoAdapter extends BaseItemDraggableAdapter<VideoDriverJson, BaseViewHolder> {
    public SelectDriverVideoAdapter(int layoutResId, ArrayList<VideoDriverJson> data) {
        super(layoutResId, data);
    }

    //iv_Driver_Icon_Select_Driver  tv_Driver_Name_Select_Driver  iv_Power_Select_Driver  iv_Net_Select_Driver
    @Override
    protected void convert(BaseViewHolder helper, VideoDriverJson item) {
        helper.setText(R.id.tv_Driver_Name_Select_Driver, item.getCamName());
        //1:通电" 0:断电 null:未开通断电设备

        if (item.getCamTypeId().equals("401") || item.getCamTypeId().equals("421")) {
            //塔吊
            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.zhinengceju_fail);

        } else if (item.getCamTypeId().equals("210") || item.getCamTypeId().equals("230")) {
            //环境
            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.zhinengceju_fail);
        } else if (item.getCamTypeId().equals("118") || item.getCamTypeId().equals("128") || item.getCamTypeId().equals("116") || item.getCamTypeId().equals("136")
                || item.getCamTypeId().equals("117") || item.getCamTypeId().equals("137") || item.getCamTypeId().equals("115") || item.getCamTypeId().equals("135")
                || Integer.parseInt(item.getCamTypeId())<=110) {
            helper.getView(R.id.iv_Net_Select_Driver).setVisibility(View.VISIBLE);
            helper.getView(R.id.iv_Power_Select_Driver).setVisibility(View.VISIBLE);
            helper.setImageResource(R.id.iv_Power_Select_Driver, EmptyUtils.isState(item.getDevStatus(), R.drawable.dian1, R.drawable.dian2, R.drawable.weikaitong));
            //1:通电" 0:断电 null:未开通断电设备
            helper.setImageResource(R.id.iv_Net_Select_Driver, EmptyUtils.isState(item.getDevNetStatus(), R.drawable.wang1, R.drawable.wang2, R.drawable.weikaitong));
            if (item.getDevTypePic() != null) {
                switch (item.getDevTypePic()) {
                    case "/lib/images/dev/zhinengceju.png":
                        if (item.getCamFlowState() == 16) {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.zhinengceju_fail);
                        } else {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.zhinengceju);
                        }
                        break;
                    case "/lib/images/dev/yuntaikekong.png":
                        if (item.getCamFlowState() == 16) {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.yuntaikekong_fail);
                        } else {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.yuntaikekong);
                        }
                        break;
                    case "/lib/images/dev/renlian.png":
                        helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.image);
                        break;
                    case "/lib/images/dev/qiuxing.png":
                        if (item.getCamFlowState() == 16) {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.qiuxing_fail);
                        } else {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.qiuxing);
                        }
                        break;
                    case "/lib/images/dev/kekong.png":
                        if (item.getCamFlowState() == 16) {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.kekong_fail);
                        } else {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.kekong);
                        }
                        break;
                    case "/lib/images/dev/wuxian.png":
                        if (item.getCamFlowState() == 16) {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.wuxian_fail);
                        } else {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.wuxian);
                        }
                        break;
                    case "/lib/images/dev/changjiao.png":
                        if (item.getCamFlowState() == 16) {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.changjiao_fail);
                        } else {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.changjiao);
                        }
                        break;
                    case "/lib/images/dev/danbing.png":
                        helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.image);
                        break;
                    case "/lib/images/dev/chaoshiyenew.png":
                        if (item.getCamFlowState() == 16) {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.chaoshiye_fail);
                        } else {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.chaoshiye);
                        }
                        break;
                    case "/lib/images/dev/chaoshiye.png":
                        if (item.getCamFlowState() == 16) {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.chaoshiye_fail);
                        } else {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.chaoshiye);
                        }
                        break;
                    case "/lib/images/dev/xunhang.png":
                        if (item.getCamFlowState() == 16) {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.xunhang_fail);
                        } else {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.xunhang);
                        }
                        break;
                    case "/lib/images/dev/qiangshi.png":
                        if (item.getCamFlowState() == 16) {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.qiangshi_fail);
                        } else {
                            helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.qiangshi);
                        }
                        break;
                    default:
                        break;
                }
            }else{
                if (item.getCamFlowState() == 16) {
                    helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.kekong_fail);
                } else {
                    helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver, R.drawable.kekong);
                }
            }
        }
        //getCamFlowState() == 16 维护中。


    }
}
