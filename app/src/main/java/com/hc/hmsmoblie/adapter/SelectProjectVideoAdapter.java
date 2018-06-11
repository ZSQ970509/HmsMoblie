//package com.hc.hmsmoblie.adapter;
//
//import com.chad.library.adapter.base.BaseViewHolder;
//
///**
// * Created by Administrator on 2018/6/11.
// */
//
//public class SelectProjectVideoAdapter extends BaseItemDraggableAdapter<ProjectBean.ListBean, BaseViewHolder> {
//    public SelectProjectVideoAdapter(int layoutResId, ArrayList<ProjectBean.ListBean> data) {
//        super(layoutResId, data);
//    }
//    @Override
//    protected void convert(BaseViewHolder helper, ProjectBean.ListBean item) {
//        helper.setText(R.id.tvProjectId,item.getRow_number()+"")
//                .setText(R.id.tvProjectName,item.getProjName())
//                .setText(R.id.tvProjectStatic,item.getProjStatusCurrent())
//                .setText(R.id.tvProjectAddress,item.getProjAddress());
//        //helper.addOnClickListener(R.id.text_samll);
//
//    }
//}
