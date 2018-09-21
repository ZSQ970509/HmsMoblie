package com.hc.hmsmoblie.utils.chart;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.classic.adapter.CommonRecyclerAdapter;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.hc.hmsmoblie.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;


/**
 *
 */

public class ChartMarkerViewNew extends MarkerView {
    private RecyclerView recyclerVie;
    private CommonRecyclerAdapter<String> commonAdapter;
    private List<ChartMarkerDataBeanNew> mMarkerDataBean = new ArrayList<>();

    public ChartMarkerViewNew(Context context, List<ChartMarkerDataBeanNew> markerDataBean) {
        super(context, R.layout.widget_marker_view_new);
        mMarkerDataBean = markerDataBean;
        recyclerVie = (RecyclerView) findViewById(R.id.rvMarker);
        recyclerVie.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        commonAdapter = new CommonRecyclerAdapter<String>(context, R.layout.item_chart_marker) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {
                helper.setText(R.id.itemChartMarkerTv, item);
            }
        };
        recyclerVie.setAdapter(commonAdapter);
    }

    boolean isLoadeData = false;
    int mIndex = -1;

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        Observable.just((int) (e.getX()) % mMarkerDataBean.size())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((a) -> {
                    refreshAdapter(a);
                });
        super.refreshContent(e, highlight);
    }

    private synchronized void refreshAdapter(int index) {
        if (index == mIndex)
            return;
        mIndex = index;
        if (!isLoadeData) {
            isLoadeData = true;
            commonAdapter.clear();
            for (ChartMarkerDataBeanNew dataBeanNew : mMarkerDataBean) {
                commonAdapter.add(dataBeanNew.getDataName() + "：" + dataBeanNew.getData().get(index) + " (度)");
            }
            isLoadeData = false;
        }
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
