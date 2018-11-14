 package com.hc.hmsmoblie.adapter;

 import android.widget.CheckBox;
 import android.widget.CompoundButton;

 import com.chad.library.adapter.base.BaseItemDraggableAdapter;
 import com.chad.library.adapter.base.BaseViewHolder;
 import com.hc.hmsmoblie.R;
 import com.hc.hmsmoblie.bean.json.OnlineTimeJson;
 import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
 import com.hc.hmsmoblie.widget.PercentageRing;

 import java.util.ArrayList;
 import java.util.List;

 /**
  * Created by Administrator on 2018/6/11.
  */
 
 public class AllDeriverAdapter extends BaseItemDraggableAdapter<TiltSensorParaJson.ListBean, BaseViewHolder> {
     private List<Boolean> states = new ArrayList<>();
     public AllDeriverAdapter(int layoutResId, List<TiltSensorParaJson.ListBean> data) {
         super(layoutResId, data);

     }
     @Override
     protected void convert(BaseViewHolder helper, TiltSensorParaJson.ListBean item) {
        helper.setText(R.id.itemDeriverCB,item.getParaName());
        states.add(((CheckBox)helper.getView(R.id.itemDeriverCB)).isChecked());
         ((CheckBox) helper.getView(R.id.itemDeriverCB)).setOnCheckedChangeListener(
                 (buttonView, isChecked) -> {
                     states.set(helper.getLayoutPosition(),isChecked);
                 });
     }
     public List<Boolean> getCheckeds(){
         return states;
     }
 }
