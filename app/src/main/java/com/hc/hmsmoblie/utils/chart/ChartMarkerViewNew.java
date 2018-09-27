package com.hc.hmsmoblie.utils.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.IMarker;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.utils.YcDecoration;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 *
 */

public abstract class ChartMarkerViewNew extends RelativeLayout implements IMarker {
    private RecyclerView recyclerVie;
    private CommonRecyclerAdapter<String> mCommonAdapter;
    private List<ChartMarkerDataBeanNew> mMarkerDataBean = new ArrayList<>();
    private MPPointF mOffset = new MPPointF();
    private MPPointF mOffset2 = new MPPointF();
    private WeakReference<Chart> mWeakChart;
    private int mIndex = -1;
    private Context mContext;

    public ChartMarkerViewNew(Context context, List<ChartMarkerDataBeanNew> markerDataBean) {
        super(context);
        initView(context, markerDataBean);
    }

    private void initView(Context context, List<ChartMarkerDataBeanNew> markerDataBean) {
        mContext = context;
        mMarkerDataBean = markerDataBean;
        mCommonAdapter = new CommonRecyclerAdapter<String>(context, R.layout.item_chart_marker) {
            @Override
            public String getItem(int position) {
                return "";
            }

            @Override
            public int getItemCount() {
                return mMarkerDataBean.size();
            }

            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {
                onAdapterUpdate(helper, mMarkerDataBean.get(position), mIndex);
            }
        };
        View inflated = LayoutInflater.from(getContext()).inflate(R.layout.widget_marker_view_new, this);
        inflated.setLayoutParams(new LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        inflated.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        inflated.layout(0, 0, inflated.getMeasuredWidth(), inflated.getMeasuredHeight());
        recyclerVie = (RecyclerView) findViewById(R.id.rvMarker);
        recyclerVie.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        recyclerVie.addItemDecoration(new YcDecoration((int) (getResources().getDisplayMetrics().density * 4 + 0.5f), 1));
        recyclerVie.setAdapter(mCommonAdapter);
    }


    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        refreshAdapter(e.getX());
        measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        layout(0, 0, getMeasuredWidth(), getMeasuredHeight());

    }
    private void refreshAdapter(float entryX) {
        int index = (int) (entryX % mMarkerDataBean.get(0).getData().size());
        if (index == mIndex)//避免重复刷新RecyclerView里的数据
            return;
        mIndex = index;
//        recyclerVie.removeAllViews();//避免RecyclerView里item重叠问题
        mCommonAdapter.notifyDataSetChanged();

//        mCommonAdapter.clear();
//        for(ChartMarkerDataBeanNew dataBeanNew :mMarkerDataBean){
//            mCommonAdapter.add();
//        }
//        .addAll(.);
    }
    @Override
    public void draw(Canvas canvas, float posX, float posY) {
        MPPointF offset = getOffsetForDrawingAtPoint(posX, posY);
        int saveId = canvas.save();
        // translate to the correct position and draw
        canvas.translate(posX + offset.x, posY + offset.y);
        draw(canvas);
        canvas.restoreToCount(saveId);
    }



    @Override
    public MPPointF getOffset() {
        return mOffset;
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {
        Chart chart = getChartView();
        MPPointF offset = new MPPointF(0, -getHeight());
        float width = getWidth();
        float height = getHeight();

        mOffset2.x = offset.x;

        if (chart != null && posX + width + mOffset2.x > chart.getWidth()) {
            offset = new MPPointF(-getWidth(), -getHeight());
            mOffset2.x = offset.x;
        }

        mOffset2.y = offset.y;

        if (posX + mOffset2.x < 0) {
            mOffset2.x = -posX;
        } /*else if (chart != null && posX + width + mOffset2.x > chart.getWidth()) {
            mOffset2.x = chart.getWidth() - posX - width;
        }*/

        if (posY + mOffset2.y < 0) {
            mOffset2.y = -posY;
        } else if (chart != null && posY + height + mOffset2.y > chart.getHeight()) {
            mOffset2.y = chart.getHeight() - posY - height;
        }
        return mOffset2;
    }

    public void setChartView(Chart chart) {
        mWeakChart = new WeakReference<>(chart);
    }

    public Chart getChartView() {
        return mWeakChart == null ? null : mWeakChart.get();
    }

    public abstract void onAdapterUpdate(BaseAdapterHelper helper, ChartMarkerDataBeanNew item, int xIndex);
}
