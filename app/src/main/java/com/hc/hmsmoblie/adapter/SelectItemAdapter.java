 package com.hc.hmsmoblie.adapter;

 import android.view.View;
 import android.widget.CheckBox;
 import android.widget.CompoundButton;
 import android.widget.RelativeLayout;
 import android.widget.TextView;

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
 
 public class SelectItemAdapter extends BaseItemDraggableAdapter<SelecItemJson, SelectItemAdapter.Holder> {
     ArrayList<Boolean> checkeds = new ArrayList<Boolean>();
     public SelectItemAdapter(int layoutResId, ArrayList<SelecItemJson> data) {
         super(layoutResId, data);

     }

     @Override
     protected void convert(Holder helper, SelecItemJson item) {
         helper.setText(R.id.select_Item_Sammll_CheckBox,item.getItemText());
         helper.checkBox.setChecked(item.getIsCheck());
         checkeds.add(helper.checkBox.isChecked());
         helper.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
             checkeds.add(helper.getLayoutPosition()+1,isChecked);
             checkeds.remove(helper.getLayoutPosition());
         });
     }
     public ArrayList<Boolean> getChecked(){
         return checkeds;
     }


     public static class Holder extends BaseViewHolder {
         CheckBox checkBox;
         public Holder(View view) {
             super(view);
             checkBox = view.findViewById(R.id.select_Item_Sammll_CheckBox);
         }
     }
 }
