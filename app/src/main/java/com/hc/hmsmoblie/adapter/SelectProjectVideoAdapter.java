 package com.hc.hmsmoblie.adapter;
 
 import com.chad.library.adapter.base.BaseItemDraggableAdapter;
 import com.chad.library.adapter.base.BaseViewHolder;
 import com.hc.hmsmoblie.R;
 import com.hc.hmsmoblie.bean.domain.ProjectVideoBean;

 import java.util.ArrayList;

 /**
  * Created by Administrator on 2018/6/11.
  */
 
 public class SelectProjectVideoAdapter extends BaseItemDraggableAdapter<ProjectVideoBean.DataBean, BaseViewHolder> {
     public SelectProjectVideoAdapter(int layoutResId, ArrayList<ProjectVideoBean.DataBean> data) {
         super(layoutResId, data);
     }
     @Override
     protected void convert(BaseViewHolder helper, ProjectVideoBean.DataBean item) {
         helper.setText(R.id.item_Project_Video_Name,item.getProjName()+"")
                 .setText(R.id.item_Project_Video_Address,item.getProjAddress())
                 .setText(R.id.item_Project_Video_Status,item.getProjStatusCurrent());
          //helper.addOnClickListener(R.id.text_samll);
 
     }
 }
