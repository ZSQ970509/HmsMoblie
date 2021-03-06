package com.hc.hmsmoblie.bean.domain;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.json.TiltSensorChartJson;
import com.hc.hmsmoblie.bean.type.TiltSensorDataProcessingType;
import com.hc.hmsmoblie.bean.type.TiltSensorType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */

public class TiltSensorBean {
    private TiltSensorChartJson.DataBean mAllData;

    public TiltSensorBean(TiltSensorChartJson.DataBean allData) {
        mAllData = allData;
    }

    public TiltSensorChartJson.DataBean getmAllData() {
        return mAllData;
    }

    public void setmAllData(TiltSensorChartJson.DataBean mAllData) {
        this.mAllData = mAllData;
    }

    public List<TiltSensorBean.DataBean> getData(@TiltSensorType int type) {
        List<TiltSensorBean.DataBean> dataBeans = new ArrayList<>();
        List<Integer> colors;
        List<String> names;
        List<Boolean> isDottedLines;
        List<List<Double>> datas;
        List<String> unit;//单位
        List<Integer> processingType;//数据处理方式
        switch (type) {
            default:
            case TiltSensorType.A_ANGLE_VALUE:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen);
                names = Arrays.asList("X轴角度", "Y轴角度"
                        , "X轴阈值", "Y轴阈值"
                        , "X轴阈值", "Y轴阈值");
                isDottedLines = Arrays.asList(false, false, true, true, true, true);
                datas = Arrays.asList(mAllData.getNewOx(), mAllData.getNewOy()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF());
                unit = Arrays.asList("度", "度", "度", "度", "度", "度");
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.B_SINGLE_ANGLE_DIFFERENCE:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen);
                names = Arrays.asList("X单次角度差", "Y单次角度差"
                        , "X轴阈值", "Y轴阈值"
                        , "X轴阈值", "Y轴阈值");
                isDottedLines = Arrays.asList(false, false, true, true, true, true);
                datas = Arrays.asList(mAllData.getOldx(), mAllData.getOldy()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF());
                unit = Arrays.asList("度", "度", "度", "度", "度", "度");
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.C_STAGE_ANGLE_DIFFERENCE:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen);
                names = Arrays.asList("X阶段角度差", "Y阶段角度差"
                        , "X轴阈值", "Y轴阈值"
                        , "X轴阈值", "Y轴阈值");
                isDottedLines = Arrays.asList(false, false, true, true, true, true);
                datas = Arrays.asList(mAllData.getObdStagex(), mAllData.getObdStagey()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF());
                unit = Arrays.asList("度", "度", "度", "度", "度", "度");
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.D_CUMULATIVE_ANGLE_DIFFERENCE:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen);
                names = Arrays.asList("X累计角度差", "Y累计角度差"
                        , "X轴阈值", "Y轴阈值"
                        , "X轴阈值", "Y轴阈值");
                isDottedLines = Arrays.asList(false, false, true, true, true, true);
                datas = Arrays.asList(mAllData.getFirstOldx(), mAllData.getFirstOldy()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF());
                unit = Arrays.asList("度", "度", "度", "度", "度", "度");
                 processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.E_SINGLE_SETTLEMENT:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue2, R.color.tiltSensorColorLineBlue2);
                names = Arrays.asList("单次沉降", "X单次位移"
                        , "Y单次位移", "Z单次位移"
                        , "位移阈值", "位移阈值");
                isDottedLines = Arrays.asList(false, false, false, false, true, true);
                datas = Arrays.asList(mAllData.getCdObd(), mAllData.getObdOldx()
                        , mAllData.getObdOldy(), mAllData.getObdOldz()
                        , mAllData.getYuOxF(), mAllData.getYuOyF());
                unit = Arrays.asList("mm", "mm", "mm", "mm", "mm", "mm");
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.F_STAGE_SETTLEMENT:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue2, R.color.tiltSensorColorLineBlue2);
                names = Arrays.asList("阶段沉降", "X阶段位移"
                        , "Y阶段位移", "Z阶段位移"
                        , "位移阈值", "位移阈值");
                isDottedLines = Arrays.asList(false, false, false, false, true, true);
                datas = Arrays.asList(mAllData.getCdObdDiff(), mAllData.getObdStagex()
                        , mAllData.getObdStagey(), mAllData.getObdStagez()
                        , mAllData.getYuOxF(), mAllData.getYuOyF());
                unit = Arrays.asList("mm", "mm", "mm", "mm", "mm", "mm");
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.G_ACCUMULATIVE_SETTLEMENT:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue2, R.color.tiltSensorColorLineBlue2);
                names = Arrays.asList("累计沉降", "X累计位移"
                        , "Y累计位移", "Z累计位移"
                        , "位移阈值", "位移阈值");
                isDottedLines = Arrays.asList(false, false, false, false, true, true);
                datas = Arrays.asList(mAllData.getCdObdAdd(), mAllData.getObdFirstOldx()
                        , mAllData.getObdFirstOldy(), mAllData.getObdFirstOldz()
                        , mAllData.getYuOxF(), mAllData.getYuOyF());
                unit = Arrays.asList("mm", "mm", "mm", "mm", "mm", "mm");
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.H_PARALLELISM:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineBlue
                        , R.color.tiltSensorColorLineGreen, R.color.tiltSensorColorLineGreen);
                names = Arrays.asList("左端浮动", "右端浮动"
                        , "位移阈值", "位移阈值"
                        , "位移阈值", "位移阈值");
                isDottedLines = Arrays.asList(false, false, true, true, true, true);
                datas = Arrays.asList(mAllData.getFloatObdLeft(), mAllData.getFloatObdRight()
                        , mAllData.getYuOxZ(), mAllData.getYuOyZ()
                        , mAllData.getYuOxF(), mAllData.getYuOyF());
                unit = Arrays.asList("mm", "mm", "mm", "mm", "mm", "mm");
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
        }
        for (int i = 0; i < datas.size(); i++) {
            TiltSensorBean.DataBean dataBean = new DataBean();
            dataBean.setmColorReId(colors.get(i));
            dataBean.setmName(names.get(i));
            dataBean.setmIsDottedLine(isDottedLines.get(i));
            dataBean.setmData(datas.get(i));
            dataBean.setUnit(unit.get(i));
            dataBean.setProcessingType(processingType.get(i));
            dataBeans.add(dataBean);
        }
        return dataBeans;
    }

    public static class DataBean {
        private String mName;//名称
        private Integer mColorReId;//颜色
        private List<Double> mData = new ArrayList<>();//数据
        private Boolean mIsDottedLine;//是否为虚线
        private String mUnit = "";//单位
        private @TiltSensorDataProcessingType
        int mProcessingType;

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public Integer getmColorReId() {
            return mColorReId;
        }

        public void setmColorReId(Integer mColorReId) {
            this.mColorReId = mColorReId;
        }

        public List<Double> getmData() {
            return mData;
        }

        public void setmData(List<Double> mData) {
            this.mData = mData;
        }

        public Boolean getmIsDottedLine() {
            return mIsDottedLine;
        }

        public void setmIsDottedLine(Boolean mIsDottedLine) {
            this.mIsDottedLine = mIsDottedLine;
        }

        public String getUnit() {
            return mUnit;
        }

        public void setUnit(String unit) {
            this.mUnit = unit;
        }

        public int getProcessingType() {
            return mProcessingType;
        }

        public void setProcessingType(int processingType) {
            this.mProcessingType = processingType;
        }
    }
}
