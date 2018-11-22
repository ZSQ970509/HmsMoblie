package com.hc.hmsmoblie.utils.chart;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.classic.adapter.BaseAdapterHelper;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.hc.hmsmoblie.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 图表的工具类
 */

public class ChartUtils {
    private final static String TAG = "ChartUtils";

    public static void initLineChart(LineChart lineChart, Context context) {
        lineChart.setNoDataText(context.getString(R.string.view_empty));
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, Highlight highlight) {
                lineChart.centerViewToAnimated(entry.getX(), entry.getY(), lineChart.getData().getDataSetByIndex(highlight.getDataSetIndex()).getAxisDependency(), 500);
            }

            @Override
            public void onNothingSelected() {
                Log.i("Nothing selected", "Nothing selected.");
            }
        });
        // 不使用描述文本相关信息
        lineChart.getDescription().setEnabled(false);
        lineChart.getDescription().setText("描述");
        // 手势能否触摸图表
        lineChart.setTouchEnabled(true);
        //减速摩擦系数[0,1]之间，0立刻停止，1，自动转换为0.999f
        lineChart.setDragDecelerationFrictionCoef(0.9f);

        // 将其设置为true以启用图表的拖动（用手指移动图表）（这不会影响缩放）。
        lineChart.setDragEnabled(true);
        //将其设置为true以在X轴和Y轴上为图表启用缩放（通过手势放大和缩小）（这不影响拖动）
        lineChart.setScaleEnabled(true);
        //将此设置为true以绘制网格背景，否则为false
        lineChart.setDrawGridBackground(false);
        //将其设置为true以允许在完全缩小时拖动图表曲面时突出显示。 默认值：true
        lineChart.setHighlightPerDragEnabled(true);

        // 如果设置为true，则可以用2个手指同时缩放x和y轴，如果为false，则可以分别缩放x和y轴。 默认值：false
        lineChart.setPinchZoom(true);

        // 设置背景颜色
        lineChart.setBackgroundResource(R.color.colorWhiteGray);
        //使用指定的动画时间在x轴上动画显示图表。
        lineChart.animateX(1500);
        //右侧y轴设置为不使用
        lineChart.getAxisRight().setEnabled(true);
        setRightYAxis(lineChart.getAxisRight());//右侧y轴设置为透明的
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();//刷新
    }

    /**
     * 图表下方 线的标示
     *
     * @param l
     */
    public static void setLegend(Legend l) {
        // 设置图例形式的形状 (线)
        l.setForm(Legend.LegendForm.LINE);
        //字体大小
        l.setTextSize(11f);
        //字体颜色
        l.setTextColor(Color.BLACK);
        //设置图例的垂直对齐
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //设置图例的水平对齐
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        //设置图例的方向
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //设置图例是否绘制在图表内部或外部
        l.setDrawInside(false);
//        l.setYOffset(10f);
    }

    public static void setXAxis(XAxis xAxis, List<String> list) {
        setXAxis(xAxis, list, 0f);
    }

    /**
     * 设置自定义的x轴（底部）
     *
     * @param xAxis
     * @param list
     */
    public static void setXAxis(XAxis xAxis, List<String> list, float angle) {
        if (list == null || list.isEmpty()) {

            Log.e(TAG, "setXAxis的数据为空");
            return;
        }
        CustomAxisX formatter = new CustomAxisX(list);
//        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(11f);
        xAxis.setTextColor(Color.BLACK);
        //将其设置为true以启用绘制该轴的网格线。
        xAxis.setDrawGridLines(false);
        //x轴在下方
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);//设置x轴间距
//        xAxis.setLabelCount(7);//设置
        xAxis.setLabelRotationAngle(angle);//文字倾斜避免不够显示
        xAxis.setValueFormatter(formatter);

    }

    /**
     * 设置y轴（左侧）
     *
     * @param leftAxis
     */
    public static void setLeftYAxis(YAxis leftAxis) {
        leftAxis.setTextColor(Color.BLACK);//y轴字的颜色
        leftAxis.setSpaceTop(5f);//将顶部轴空间设置为整个范围的百分比。默认10f（即10%）
        //通过调用此方法，先前设置的任何自定义最大值将被重置，并且计算会自动完成。
//        leftAxis.setAxisMaximum(maxNum);
        //通过调用此方法，先前设置的任何自定义最小值将被重置，并自动完成计算。
//        leftAxis.setAxisMinimum(minNum);
        leftAxis.setAxisLineColor(Color.BLACK);
        leftAxis.setDrawGridLines(true);    //将此设置为true，以便绘制该轴的网格线
        leftAxis.setGranularityEnabled(false);  //在轴值间隔上启用/禁用粒度控制。
        leftAxis.setXOffset(AXIS_Y_LAYOUT_MARGIN);
        //y轴单位设置
//        leftAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return value + "(m)";
//            }
//        });
    }

    private static final float AXIS_Y_LAYOUT_MARGIN = 13f;

    /**
     * 设置透明右侧y轴
     *
     * @param rightYAxis
     */
    public static void setRightYAxis(YAxis rightYAxis) {
        rightYAxis.setTextColor(Color.TRANSPARENT);//y轴字的颜色
        rightYAxis.setAxisLineColor(Color.TRANSPARENT);
        rightYAxis.setSpaceTop(5f);//将顶部轴空间设置为整个范围的百分比。默认10f（即10%）
        rightYAxis.setDrawGridLines(true);    //将此设置为true，以便绘制该轴的网格线
        rightYAxis.setGranularityEnabled(false);  //在轴值间隔上启用/禁用粒度控制。
        rightYAxis.setXOffset(AXIS_Y_LAYOUT_MARGIN);
    }

    /**
     * 获取线的数据
     *
     * @param lineChart      图表
     * @param dataList       数据
     * @param chartDataIndex 线在图表里对应的index
     * @param color          线和图表下方标示的颜色
     * @param name           图表下方标示的名称
     * @return
     */
    public static LineDataSet getLineDataSet(LineChart lineChart, List<Double> dataList, int chartDataIndex, int color, String name) {
        return getLineDataSet(lineChart, dataList, chartDataIndex, color, name, LineDataSet.Mode.CUBIC_BEZIER);
    }

    public static LineDataSet getLineDataSet(LineChart lineChart, List<Double> dataList, int chartDataIndex, int color, String name, LineDataSet.Mode mode) {
        return getLineDataSet(lineChart, dataList, chartDataIndex, color, name, mode, false);
    }

    public static LineDataSet getLineDataSet(LineChart lineChart, List<Double> dataList, int chartDataIndex, int color, String name, LineDataSet.Mode mode, boolean isDottedLine) {
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < dataList.size(); i++) {
            yVals.add(new Entry(i, Float.parseFloat(dataList.get(i) + "")));
        }
        LineDataSet set;
        if (lineChart.getData() != null && lineChart.getData().getDataSetCount() > 0 && lineChart.getData().getDataSetByIndex(chartDataIndex) != null) {
            set = (LineDataSet) lineChart.getData().getDataSetByIndex(chartDataIndex);
            set.setLabel(name);
            set.setValues(yVals);
        } else {
            set = new LineDataSet(yVals, name);
            if (isDottedLine)
                set.enableDashedLine(10f, 10f, 0f);
            // 设置平滑曲线
            set.setMode(mode);
            set.setColors(color);
            set.setAxisDependency(YAxis.AxisDependency.LEFT);
//            set.setColor(color);//设置线的颜色
            set.setLineWidth(2.5f);//设置线的宽度

            //顶点圆
            set.setCircleRadius(4f);//设置顶点的半径
            set.setCircleColor(color);//设置顶点的颜色

            //顶点上方显示的值
            set.setDrawValues(false);//关闭顶点上方显示y值
            set.setValueTextColor(Color.BLACK);//顶点上方显示的字颜色
//            set.setValueTextSize(10f);//顶点上方显示的字大小

            //指引线
//            set.setHighlightEnabled(false);//关闭指引线宽度
//            set.setHighlightLineWidth(1f); //指引线宽度
            set.setHighLightColor(Color.GRAY); //指引线的颜色。
        }
        return set;
    }

    public static void setData(Activity activity, ChartLineView lineChart, String[] names, int[] colors, List<String> dataMarkerX, List<String> dataX, List<List<Double>> dataY, List<String> unit) {
        LineData lineData = new LineData();
        LineDataSet setData;
        //初始化图表里的LineDataSet(线)数据
        List<ChartMarkerDataBeanNew> markerData = new ArrayList<>();
        for (int i = 0; i < dataY.size(); i++) {
            setData = ChartUtils.getLineDataSet(lineChart, dataY.get(i),
                    i, colors[i], names[i], LineDataSet.Mode.LINEAR, false);
            lineData.addDataSet(setData);
            markerData.add(new ChartMarkerDataBeanNew(names[i], dataY.get(i), unit.get(i)));
        }
        lineChart.setData(lineData);
        //单击图表后的显示的对话框
        ChartMarkerViewNew chartMarkerViewNew = new ChartMarkerViewNew(activity, markerData, dataMarkerX) {
            @Override
            public void onAdapterUpdate(BaseAdapterHelper helper, ChartMarkerDataBeanNew item, int xIndex) {
                helper.setText(R.id.itemChartMarkerTv, item.getDataName() + "：" + item.getData().get(xIndex) + " (" + item.getUnit() + ")");
            }
        };
        chartMarkerViewNew.setChartView(lineChart);
        lineChart.setMarker(chartMarkerViewNew);
        ChartUtils.setLeftYAxis(lineChart.getAxisLeft());
        ChartUtils.setXAxis(lineChart.getXAxis(), dataX, -60f);
        lineChart.getLegend().setDrawInside(true);
        lineChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        lineChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //刷新图表
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
        lineChart.setVisibleXRangeMaximum(45);
    }
}
