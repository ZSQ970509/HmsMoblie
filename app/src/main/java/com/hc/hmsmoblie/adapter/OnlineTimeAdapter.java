 package com.hc.hmsmoblie.adapter;

 import com.chad.library.adapter.base.BaseItemDraggableAdapter;
 import com.chad.library.adapter.base.BaseViewHolder;
 import com.hc.hmsmoblie.R;
 import com.hc.hmsmoblie.bean.json.OnlineTimeJson;
 import com.hc.hmsmoblie.bean.json.ProjectJson;
 import com.hc.hmsmoblie.widget.PercentageRing;

 import java.util.ArrayList;

 /**
  * Created by Administrator on 2018/6/11.
  */
 
 public class OnlineTimeAdapter extends BaseItemDraggableAdapter<OnlineTimeJson.ListBean, BaseViewHolder> {
     public OnlineTimeAdapter(int layoutResId, ArrayList<OnlineTimeJson.ListBean> data) {
         super(layoutResId, data);

     }
     @Override
     protected void convert(BaseViewHolder helper, OnlineTimeJson.ListBean item) {
        helper.setText(R.id.tv_Name_OnlineTime_Item,item.getCamName())
                 .setText(R.id.tv_Plan_Time_OnlineTime_Item,"应监控时长:"+item.getCamOnlineMustTimeH()+"小时")
                 .setText(R.id.tv_Current_Time_OnlineTime_Item,"有效监控时长:"+item.getCamOnlineDurationH()+"小时");
         ((PercentageRing)helper.getView(R.id.pr_Rate_OnlineTime_Item)).setTargetPercent((int)(item.getCamOnlineRate()*100));

          //helper.addOnClickListener(R.id.text_samll);
 
     }
 }
