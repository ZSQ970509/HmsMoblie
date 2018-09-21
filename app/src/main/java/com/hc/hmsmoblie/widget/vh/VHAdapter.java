package com.hc.hmsmoblie.widget.vh;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.TiltSensorDataJson;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/6/11.
 */

public class VHAdapter extends BaseItemDraggableAdapter<SensorLogJson.ListBean, BaseViewHolder> {
    private int mFixX;
    private ArrayList<View> mMoveViewList = new ArrayList<>();
    public VHAdapter(int layoutResId, ArrayList<SensorLogJson.ListBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, SensorLogJson.ListBean item) {
        LinearLayout moveLayout = helper.getView(R.id.id_move_layout);
        mMoveViewList.add(moveLayout);
        moveLayout.scrollTo(getFixX(), 0);
        Log.e("111",item.getRowNumber()+"");
        helper.setText(R.id.tiltSensorItem1, item.getRowNumber()+"")
                .setText(R.id.tiltSensorItem2, item.getParaName()+"")
                .setText(R.id.tiltSensorItem3, item.getCreateTime()+"")
                .setText(R.id.tiltSensorItem4, item.getOx()+"")
                .setText(R.id.tiltSensorItem5, item.getOy()+"")
                .setText(R.id.tiltSensorItem6, item.getOldx()+"")
                .setText(R.id.tiltSensorItem7, item.getOldy()+"")
                .setText(R.id.tiltSensorItem8, item.getOxDiff()+"")
                .setText(R.id.tiltSensorItem9, item.getOyDiff()+"")
                .setText(R.id.tiltSensorItem10, item.getOyDiff()+"")
                .setText(R.id.tiltSensorItem11, item.getOyDiff()+"");
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
