 package com.hc.hmsmoblie.adapter;

 import com.chad.library.adapter.base.BaseItemDraggableAdapter;
 import com.chad.library.adapter.base.BaseViewHolder;
 import com.hc.hmsmoblie.R;
 import com.hc.hmsmoblie.bean.json.OnlineTimeJson;
 import com.hc.hmsmoblie.bean.json.SelecItemJson;
 import com.hc.hmsmoblie.widget.PercentageRing;

 import java.util.ArrayList;

 /**
  * Created by Administrator on 2018/6/11.
  */
 
 public class SelectItemAdapter extends BaseItemDraggableAdapter<SelecItemJson, BaseViewHolder> {
     public SelectItemAdapter(int layoutResId, ArrayList<SelecItemJson> data) {
         super(layoutResId, data);

     }
     @Override
     protected void convert(BaseViewHolder helper, SelecItemJson item) {
        helper.setText(R.id.select_Item_Sammll_CheckBox,item.getItemText());
 
     }
 }
