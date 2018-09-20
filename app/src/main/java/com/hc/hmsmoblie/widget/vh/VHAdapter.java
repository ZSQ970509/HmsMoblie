 package com.hc.hmsmoblie.widget.vh;

 import android.view.View;
 import android.widget.LinearLayout;

 import com.chad.library.adapter.base.BaseItemDraggableAdapter;
 import com.chad.library.adapter.base.BaseViewHolder;
 import com.hc.hmsmoblie.R;
 import com.hc.hmsmoblie.bean.json.ProjectJson;
 import com.hc.hmsmoblie.bean.json.TiltSensorDataJson;

 import java.util.ArrayList;

 /**
  * Created by Administrator on 2018/6/11.
  */
 
 public class VHAdapter extends BaseItemDraggableAdapter<TiltSensorDataJson, BaseViewHolder> {
     private int mFixX;
     private ArrayList<View> mMoveViewList = new ArrayList<>();
     public VHAdapter(int layoutResId, ArrayList<TiltSensorDataJson> data) {
         super(layoutResId, data);
     }
     @Override
     protected void convert(BaseViewHolder helper, TiltSensorDataJson item) {
         LinearLayout moveLayout = helper.getView(R.id.id_move_layout);
         mMoveViewList.add(moveLayout);
         moveLayout.scrollTo(getFixX(), 0);
         helper.setText(R.id.tiltSensorItem1, item.name)
                 .setText(R.id.tiltSensorItem2, item.priceLast)
                 .setText(R.id.tiltSensorItem3, item.riseRate24)
                 .setText(R.id.tiltSensorItem4, item.vol24)
                 .setText(R.id.tiltSensorItem5, item.close)
                 .setText(R.id.tiltSensorItem6, item.open)
                 .setText(R.id.tiltSensorItem7, item.bid)
                 .setText(R.id.tiltSensorItem8, item.ask)
                 .setText(R.id.tiltSensorItem9, item.amountPercent);
          //helper.addOnClickListener(R.id.text_samll);
 
     }

     public void refreshLayoutMove(int moveOffsetX) {
         if (null != mMoveViewList) {
             for (int i = 0; i < mMoveViewList.size(); i++) {
                 //使每个item随着手指向右滚动
                 mMoveViewList.get(i).scrollTo(moveOffsetX, 0);
             }
         }
     }

     public int getFixX() {
         return mFixX;
     }

     public void setFixX(int fixX) {
         mFixX = fixX;
     }
 }
