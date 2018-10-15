package com.hc.hmsmoblie.bean.domain;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.json.TiltSensorChartJson;
import com.hc.hmsmoblie.bean.type.TiltSensorType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */

public class TiltSensorBean {
    private TiltSensorChartJson.DataBean mAllData;

    TiltSensorBean(TiltSensorChartJson.DataBean allData) {
        mAllData = allData;
    }

    public TiltSensorChartJson.DataBean getmAllData() {
        return mAllData;
    }

    public void setmAllData(TiltSensorChartJson.DataBean mAllData) {
        this.mAllData = mAllData;
    }

    public TiltSensorBean.DataBean getData(@TiltSensorType int type) {
        TiltSensorBean.DataBean dataBean = new TiltSensorBean.DataBean();
        switch (type) {
            default:
            case TiltSensorType.A_ANGLE_VALUE:
                dataBean.setmColorReId(Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineBlue
                        , R.color.tiltSensorColorLineGreen, R.color.tiltSensorColorLineGreen));
                dataBean.setmName(Arrays.asList("X轴角度", "Y轴角度"
                        , "X轴阈值", "X轴阈值"
                        , "Y轴阈值", "Y轴阈值"));
                dataBean.setmIsDottedLine(Arrays.asList(false, false, true, true, true, true));
                dataBean.setmData(Arrays.asList(mAllData.getNewOx(), mAllData.getNewOy()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF()));
                break;
            case TiltSensorType.B_SINGLE_ANGLE_DIFFERENCE:
                dataBean.setmColorReId(Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineBlue
                        , R.color.tiltSensorColorLineGreen, R.color.tiltSensorColorLineGreen));
                dataBean.setmName(Arrays.asList("X单次角度差", "Y单次角度差"
                        , "X轴阈值", "X轴阈值"
                        , "Y轴阈值", "Y轴阈值"));
                dataBean.setmIsDottedLine(Arrays.asList(false, false, true, true, true, true));
                dataBean.setmData(Arrays.asList(mAllData.getNewOx(), mAllData.getNewOy()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF()));
                break;
            case TiltSensorType.C_STAGE_ANGLE_DIFFERENCE:
                dataBean.setmColorReId(Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineBlue
                        , R.color.tiltSensorColorLineGreen, R.color.tiltSensorColorLineGreen));
                dataBean.setmName(Arrays.asList("X阶段角度差", "Y阶段角度差"
                        , "X轴阈值", "X轴阈值"
                        , "Y轴阈值", "Y轴阈值"));
                dataBean.setmIsDottedLine(Arrays.asList(false, false, true, true, true, true));
                dataBean.setmData(Arrays.asList(mAllData.getNewOx(), mAllData.getNewOy()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF()));
                break;
            case TiltSensorType.D_CUMULATIVE_ANGLE_DIFFERENCE:
                dataBean.setmColorReId(Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineBlue
                        , R.color.tiltSensorColorLineGreen, R.color.tiltSensorColorLineGreen));
                dataBean.setmName(Arrays.asList("X累计角度差", "Y累计角度差"
                        , "X轴阈值", "X轴阈值"
                        , "Y轴阈值", "Y轴阈值"));
                dataBean.setmIsDottedLine(Arrays.asList(false, false, true, true, true, true));
                dataBean.setmData(Arrays.asList(mAllData.getNewOx(), mAllData.getNewOy()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF()));
                break;
            case TiltSensorType.E_SINGLE_SETTLEMENT:
                dataBean.setmColorReId(Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue2, R.color.tiltSensorColorLineBlue2));
                dataBean.setmName(Arrays.asList("单次沉降", "X单次位移"
                        , "Y单次位移", "Z单次位移"
                        , "位移阈值", "位移阈值"));
                dataBean.setmIsDottedLine(Arrays.asList(false, false, false, false, true, true));
                dataBean.setmData(Arrays.asList(mAllData.getNewOx(), mAllData.getNewOy()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF()));
                break;
            case TiltSensorType.F_STAGE_SETTLEMENT:
                dataBean.setmColorReId(Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue2, R.color.tiltSensorColorLineBlue2));
                dataBean.setmName(Arrays.asList("阶段沉降", "X阶段位移"
                        , "Y阶段位移", "Z阶段位移"
                        , "位移阈值", "位移阈值"));
                dataBean.setmIsDottedLine(Arrays.asList(false, false, false, false, true, true));
                dataBean.setmData(Arrays.asList(mAllData.getNewOx(), mAllData.getNewOy()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF()));
                break;
            case TiltSensorType.G_ACCUMULATIVE_SETTLEMENT:
                dataBean.setmColorReId(Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue2, R.color.tiltSensorColorLineBlue2));
                dataBean.setmName(Arrays.asList("累计沉降", "X累计位移"
                        , "Y累计位移", "Z累计位移"
                        , "Y轴阈值", "Y轴阈值"));
                dataBean.setmIsDottedLine(Arrays.asList(false, false, false, false, true, true));
                dataBean.setmData(Arrays.asList(mAllData.getNewOx(), mAllData.getNewOy()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF()));
                break;
            case TiltSensorType.H_PARALLELISM:
                dataBean.setmColorReId(Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineBlue
                        , R.color.tiltSensorColorLineGreen, R.color.tiltSensorColorLineGreen));
                dataBean.setmName(Arrays.asList("左端浮动", "右端浮动"
                        , "位移阈值", "位移阈值"
                        , "位移阈值", "位移阈值"));
                dataBean.setmIsDottedLine(Arrays.asList(false, false, true, true, true, true));
                dataBean.setmData(Arrays.asList(mAllData.getNewOx(), mAllData.getNewOy()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF()));
                break;
        }
        return dataBean;
    }

    private static class DataBean {
        private List<String> mName;//名称
        private List<Integer> mColorReId;//颜色
        private List<List<Double>> mData = new ArrayList<>();//数据
        private List<Boolean> mIsDottedLine;//是否为虚线
        private List<String> mUnit;//单位

        public List<String> getmName() {
            return mName;
        }

        public void setmName(List<String> mName) {
            this.mName = mName;
        }

        public List<Integer> getmColorReId() {
            return mColorReId;
        }

        public void setmColorReId(List<Integer> mColorReId) {
            this.mColorReId = mColorReId;
        }

        public List<List<Double>> getmData() {
            return mData;
        }

        public void setmData(List<List<Double>> mData) {
            this.mData = mData;
        }

        public List<Boolean> getmIsDottedLine() {
            return mIsDottedLine;
        }

        public void setmIsDottedLine(List<Boolean> mIsDottedLine) {
            this.mIsDottedLine = mIsDottedLine;
        }
    }
}
