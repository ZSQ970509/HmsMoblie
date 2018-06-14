 package com.hc.hmsmoblie.adapter;
 
 import com.chad.library.adapter.base.BaseItemDraggableAdapter;
 import com.chad.library.adapter.base.BaseViewHolder;
 import com.hc.hmsmoblie.R;
 import com.hc.hmsmoblie.bean.json.ProjectJson;

 import java.util.ArrayList;

 /**
  * Created by Administrator on 2018/6/11.
  */
 
 public class SelectProjectVideoAdapter extends BaseItemDraggableAdapter<ProjectJson.ListBean, BaseViewHolder> {
     public SelectProjectVideoAdapter(int layoutResId, ArrayList<ProjectJson.ListBean> data) {
         super(layoutResId, data);
     }
     @Override
     protected void convert(BaseViewHolder helper, ProjectJson.ListBean item) {
         helper.setText(R.id.item_Project_Video_Name,item.getProjName())
                 .setText(R.id.item_Project_Video_Address,item.getProjAddress())
                 .setText(R.id.item_Project_Video_Status,item.getProjStatusCurrent());
          //helper.addOnClickListener(R.id.text_samll);
 
     }
 }
