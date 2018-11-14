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
import com.hc.hmsmoblie.utils.TiltSensorStateUtils;

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
            R.id.tiltSensorItem9, R.id.tiltSensorItem10, R.id.tiltSensorItem11, R.id.tiltSensorItem12, R.id.tiltSensorItem13, R.id.tiltSensorItem14, R.id.tiltSensorItem15};

    @Override
    protected void convert(BaseViewHolder helper, SensorLogJson.ListBean item) {
        LinearLayout moveLayout = helper.getView(R.id.id_move_layout);
        mMoveViewList.add(moveLayout);
        moveLayout.scrollTo(getFixX(), 0);
        String data[] = new String[]{item.getRowNumber() + "",
                item.getParaName() + "",
                item.getCreateTime() + "",
                isZero(item.getOx(), TiltSensorStateUtils.formatX(item.getOx())),
                //FormatUtils.stripTrailingZeros(item.getOx()) + "",
                isZero(item.getOy(), TiltSensorStateUtils.formatY(item.getOy())),
                //FormatUtils.stripTrailingZeros(item.getOy()) + "",
                FormatUtils.stripTrailingZeros(item.getObd()) + "",
                isZero(item.getOldx(), TiltSensorStateUtils.formatX(item.getOldx())) +" , "+ isZero(item.getOldy(), TiltSensorStateUtils.formatY(item.getOldy())),
                //FormatUtils.stripTrailingZeros(item.getOldx()) + "," + FormatUtils.stripTrailingZeros(item.getOldy()),
                isZeroNum4(item.getCdObd(),TiltSensorStateUtils.formatSettlement(item.getCdObd()))+" + " + (item.getHightObd() == 0 ? "\\" : item.getHightObd()),
                //FormatUtils.stripTrailingZeros(item.getCdObd()) + "(" + FormatUtils.stripTrailingZeros(item.getObdOldx()) + "," + FormatUtils.stripTrailingZeros(item.getObdOldy()) + "," + FormatUtils.stripTrailingZeros(item.getObdOldz()) + ")",
                isZero(item.getStagex(), TiltSensorStateUtils.formatX(item.getStagex()))  +" , "+ isZero(item.getStagey(), TiltSensorStateUtils.formatY(item.getStagey())),
                //FormatUtils.stripTrailingZeros(item.getStagex()) + "," + FormatUtils.stripTrailingZeros(item.getStagey()),
                isZeroNum4(item.getCdObdDiff(),TiltSensorStateUtils.formatSettlement(item.getCdObdDiff()))+" + "+(item.getHightObd() == 0 ? "\\" : item.getHightObdDiff()),
                //FormatUtils.stripTrailingZeros(item.getCdObdDiff()) + "(" + FormatUtils.stripTrailingZeros(item.getObdStagex()) + "," + FormatUtils.stripTrailingZeros(item.getObdStagey()) + "," + FormatUtils.stripTrailingZeros(item.getObdStagez()) + ")",
                isZero(item.getStagex(), TiltSensorStateUtils.formatX(item.getFirstOldx()))  +" , "+ isZero(item.getStagey(), TiltSensorStateUtils.formatY(item.getFirstOldy())),
                //FormatUtils.stripTrailingZeros(item.getFirstOldx()) + "," + FormatUtils.stripTrailingZeros(item.getFirstOldy()),
                isZeroNum4(item.getCdObdAdd(),TiltSensorStateUtils.formatSettlement(item.getCdObdDiff()))+" _ "+(item.getHightObd() == 0 ? "\\" : item.getHightObdAdd()),
                //FormatUtils.stripTrailingZeros(item.getCdObdAdd()) + "(" + FormatUtils.stripTrailingZeros(item.getObdFirstOldx()) + "," + FormatUtils.stripTrailingZeros(item.getObdFirstOldy()) + "," + FormatUtils.stripTrailingZeros(item.getObdFirstOldz()) + ")",
                isZero(item.getObdLeft(),TiltSensorStateUtils.formatSettlement(item.getObdLeft())) +" , "+isZero(item.getObdRight(),TiltSensorStateUtils.formatSettlement(item.getObdRight())),
                //FormatUtils.stripTrailingZeros(item.getObdLeft()) + "," + FormatUtils.stripTrailingZeros(item.getObdRight()),
                isZero(item.getStageObdLeft(),TiltSensorStateUtils.formatSettlement(item.getStageObdLeft())) +" , "+isZero(item.getStageObdRight(),TiltSensorStateUtils.formatSettlement(item.getStageObdRight())),
                //FormatUtils.stripTrailingZeros(item.getStageObdLeft()) + "," + FormatUtils.stripTrailingZeros(item.getStageObdRight()),
                isZero(item.getFloatObdLeft(),TiltSensorStateUtils.formatSettlement(item.getFloatObdLeft())) +" , "+isZero(item.getFloatObdRight(),TiltSensorStateUtils.formatSettlement(item.getFloatObdRight()))};
                //FormatUtils.stripTrailingZeros(item.getFloatObdLeft()) + "," + FormatUtils.stripTrailingZeros(item.getFloatObdRight())};
        helper.setText(itemId[0], data[0]);
//        String[] name = mContext.getResources().getStringArray(R.array.tiltSensorTitleName);
        for (int i = 1; i < itemId.length; i++) {
            if (mTitleVisibility.get(i - 1) == null || mTitleVisibility.get(i - 1)) {
                helper.getView(itemId[i]).setVisibility(View.VISIBLE);
                helper.setText(itemId[i], data[i]);
//                helper.setText(itemId[i], i + "-" + name[i-1]);
            } else {
                helper.getView(itemId[i]).setVisibility(View.GONE);
            }
        }
    }

    public String isZero(double x, String y) {
        String data = x == 0 ? "\\" : y + Math.abs(x);
        return data;
    }
    public String isZeroNum4(double x, String y) {
        String data = x == 0 ? "\\" : y + (FormatUtils.stripTrailingZeros(Math.abs(x)));
        return data;
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
