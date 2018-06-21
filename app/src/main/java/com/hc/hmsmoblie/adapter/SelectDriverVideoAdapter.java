 package com.hc.hmsmoblie.adapter;

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
         helper.setText(R.id.tv_Driver_Name_Select_Driver,item.getCamName());
         //1:通电" 0:断电 null:未开通断电设备
         helper.setImageResource(R.id.iv_Power_Select_Driver,EmptyUtils.isState(item.getDevStatus(),R.drawable.cb_select,R.drawable.cb_select,R.drawable.cb_select));
         //1:通电" 0:断电 null:未开通断电设备
         helper.setImageResource(R.id.iv_Power_Select_Driver,EmptyUtils.isState(item.getDevNetStatus(),R.drawable.cb_select,R.drawable.cb_select,R.drawable.cb_select));


         //getCamFlowState() == 16 维护中。
         switch (item.getDevTypePic()) {
             case "/lib/images/dev/zhinengceju.png":
                 if (item.getCamFlowState() == 16) {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 } else {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 }
                 break;
             case "/lib/images/dev/yuntaikekong.png":
                 if (item.getCamFlowState() == 16) {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 } else {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 }
                 break;
             case "/lib/images/dev/renlian.png":
                 helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 break;
             case "/lib/images/dev/qiuxing.png":
                 if (item.getCamFlowState() == 16) {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 } else {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 }
                 break;
             case "/lib/images/dev/kekong.png":
                 if (item.getCamFlowState() == 16) {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 } else {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 }
                 break;
             case "/lib/images/dev/wuxian.png":
                 if (item.getCamFlowState() == 16) {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 } else {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 }
                 break;
             case "/lib/images/dev/changjiao.png":
                 if (item.getCamFlowState() == 16) {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 } else {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 }
                 break;
             case "/lib/images/dev/danbing.png":
                 helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 break;
             case "/lib/images/dev/chaoshiyenew.png":
                 if (item.getCamFlowState() == 16) {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 } else {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 }
                 break;
             case "/lib/images/dev/chaoshiye.png":
                 if (item.getCamFlowState() == 16) {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 } else {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 }
                 break;
             case "/lib/images/dev/xunhang.png":
                 if (item.getCamFlowState() == 16) {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 } else {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 }
                 break;
             case "/lib/images/dev/qiangshi.png":
                 if (item.getCamFlowState() == 16) {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 } else {
                     helper.setImageResource(R.id.iv_Driver_Icon_Select_Driver,R.drawable.cb_select);
                 }
                 break;
             default:
                 break;
         }

     }
 }
