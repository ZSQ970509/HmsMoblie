package com.hc.hmsmoblie.widget.vh;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.json.ProjectJson;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.TiltSensorDataJson;
import com.hc.hmsmoblie.utils.FormatUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/6/11.
 */

public class VHAdapter extends BaseItemDraggableAdapter<SensorLogJson.ListBean, BaseViewHolder> {
    private int mFixX;
    private ArrayList<View> mMoveViewList = new ArrayList<>();
    private SparseArray<Boolean> mTitleVisibility = new SparseArray<>();

    public VHAdapter(int layoutResId, ArrayList<SensorLogJson.ListBean> data) {
        super(layoutResId, data);
    }

    private int[] itemId = new int[]{
            R.id.tiltSensorItem1, R.id.tiltSensorItem2, R.id.tiltSensorItem3, R.id.tiltSensorItem4,
            R.id.tiltSensorItem5, R.id.tiltSensorItem6, R.id.tiltSensorItem7, R.id.tiltSensorItem8,
            R.id.tiltSensorItem9, R.id.tiltSensorItem10, R.id.tiltSensorItem11, R.id.tiltSensorItem12, R.id.tiltSensorItem13, R.id.tiltSensorItem14,R.id.tiltSensorItem15};

    @Override
    protected void convert(BaseViewHolder helper, SensorLogJson.ListBean item) {
        LinearLayout moveLayout = helper.getView(R.id.id_move_layout);
        mMoveViewList.add(moveLayout);
        moveLayout.scrollTo(getFixX(), 0);
        DecimalFormat df = new DecimalFormat("0.#");
        String data[] = new String[]{item.getRowNumber() + "",
                item.getParaName() + "",
                item.getCreateTime() + "",

                item.getOx() + "",
                item.getOy() + "",
                df.format(item.getObd()/1000) + "",
                item.getOldx() + "," + item.getOldy(),
                df.format(item.getCdObd()*1000) + "(" + df.format(item.getObdOldx()* 1000) + "," + df.format(item.getObdOldy()* 1000) + "," + df.format(item.getObdOldz()* 1000) + ")",
                item.getStagex() + "," + item.getStagey(),
                df.format(item.getCdObdDiff() * 1000) + "(" + df.format(item.getObdStagex() * 1000) + "," + df.format(item.getObdStagey() * 1000) + "," + df.format(item.getObdStagez() * 1000) + ")",
                item.getFirstOldx() + "," + item.getFirstOldy(),
                df.format(item.getCdObdAdd() * 1000) + "(" + df.format(item.getObdFirstOldx() * 1000) + "," + df.format(item.getObdFirstOldy() * 1000) + "," + df.format(item.getObdFirstOldz() * 1000) + ")",
                df.format(FormatUtils.roundOff(item.getObdLeft() * 1000, 1)) + ","+FormatUtils.roundOff(item.getObdRight() * 1000, 1),
                df.format(FormatUtils.roundOff(item.getStageObdLeft() * 1000, 1)) + ","+FormatUtils.roundOff(item.getStageObdRight() * 1000, 1),
                df.format(FormatUtils.roundOff(item.getFloatObdLeft() * 1000, 1)) + ","+FormatUtils.roundOff(item.getFloatObdRight() * 1000, 1)};
        helper.setText(itemId[0], data[0]);
//        String[] name = mContext.getResources().getStringArray(R.array.tiltSensorTitleName);
        for (int i = 1; i < itemId.length; i++) {
            if (mTitleVisibility.get(i - 1) == null || mTitleVisibility.get(i - 1)) {
                helper.getView(itemId[i]).setVisibility(View.VISIBLE);
                helper.setText(itemId[i] , data[i]);
//                helper.setText(itemId[i], i + "-" + name[i-1]);
            } else {
                helper.getView(itemId[i]).setVisibility(View.GONE);
            }
        }
    }

    public void refreshLayoutMove(int moveOffsetX) {
        if (null != mMoveViewList) {
            for (int i = 0; i < mMoveViewList.size(); i++) {
                //使每个item随着手指向右滚动
                mMoveViewList.get(i).scrollTo(moveOffsetX, 0);
            }
        }
    }

    public void setTitleVisibility(SparseArray<Boolean> titleVisibility) {
        mTitleVisibility = titleVisibility;
        this.notifyDataSetChanged();
    }

    public int getFixX() {
        return mFixX;
    }

    public void setFixX(int fixX) {
        mFixX = fixX;
    }
}
