package com.hc.hmsmoblie.bean.domain;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.json.TiltSensorChartJson;
import com.hc.hmsmoblie.bean.json.TiltSensorChartJsonNew;
import com.hc.hmsmoblie.bean.type.TiltSensorDataProcessingType;
import com.hc.hmsmoblie.bean.type.TiltSensorType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */

public class TiltSensorBeanNew {
    private TiltSensorChartJsonNew.DataBeanX mAllData;

    public TiltSensorBeanNew(TiltSensorChartJsonNew.DataBeanX allData) {
        mAllData = allData;
    }

    public TiltSensorChartJsonNew.DataBeanX getmAllData() {
        return mAllData;
    }

    public void setmAllData(TiltSensorChartJsonNew.DataBeanX mAllData) {
        this.mAllData = mAllData;
    }

    public List<TiltSensorBeanNew.DataBean> getData(@TiltSensorType int type) {
        List<TiltSensorBeanNew.DataBean> dataBeans = new ArrayList<>();
        List<Integer> colors;
        List<String> names = new ArrayList<>();
        List<Boolean> isDottedLines;
        List<List<Double>> datas = new ArrayList<>();
        List<String> unit = new ArrayList<>();//单位
        List<Integer> processingType;//数据处理方式
        switch (type) {
            default:
            case TiltSensorType.A_ANGLE_VALUE:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen);
                isDottedLines = Arrays.asList(false, false, true, true, true, true);
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.B_SINGLE_ANGLE_DIFFERENCE:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen);
                isDottedLines = Arrays.asList(false, false, true, true, true, true);
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.C_STAGE_ANGLE_DIFFERENCE:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen);
                isDottedLines = Arrays.asList(false, false, true, true, true, true);
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.D_CUMULATIVE_ANGLE_DIFFERENCE:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen);
                isDottedLines = Arrays.asList(false, false, true, true, true, true);
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.E_SINGLE_SETTLEMENT:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue2, R.color.tiltSensorColorLineBlue2);
                isDottedLines = Arrays.asList(false, false, false, false, true, true);
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.F_STAGE_SETTLEMENT:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue2, R.color.tiltSensorColorLineBlue2);
                isDottedLines = Arrays.asList(false, false, false, false, true, true);
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.G_ACCUMULATIVE_SETTLEMENT:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineGreen
                        , R.color.tiltSensorColorLineBlue2, R.color.tiltSensorColorLineBlue2);
                isDottedLines = Arrays.asList(false, false, false, false, true, true);
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
            case TiltSensorType.H_PARALLELISM:
                colors = Arrays.asList(R.color.tiltSensorColorLineRed, R.color.tiltSensorColorLineYellow
                        , R.color.tiltSensorColorLineBlue, R.color.tiltSensorColorLineBlue
                        , R.color.tiltSensorColorLineGreen, R.color.tiltSensorColorLineGreen);
                isDottedLines = Arrays.asList(false, false, true, true, true, true);
                processingType = Arrays.asList(TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4
                        , TiltSensorDataProcessingType.KEEP_DECIMAL_4, TiltSensorDataProcessingType.KEEP_DECIMAL_4);
                break;
        }
        TiltSensorChartJsonNew.DataBeanX.DataBean tempItem;
        for (int i = 0; i < mAllData.getData().size(); i++) {
            tempItem = mAllData.getData().get(i);
            datas.add(tempItem.getTiltSensorData());
            unit.add(tempItem.getUnit());
            names.add(tempItem.getName());
        }
        for (int i = 0; i < datas.size(); i++) {
            TiltSensorBeanNew.DataBean dataBean = new DataBean();
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
