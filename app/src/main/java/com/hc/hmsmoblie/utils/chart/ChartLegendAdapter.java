package com.hc.hmsmoblie.utils.chart;

import android.content.Context;
import android.support.annotation.NonNull;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonRecyclerAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.domain.TiltSensorBean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class ChartLegendAdapter extends CommonRecyclerAdapter<ChartLegendAdapter.DataBean> {

    public ChartLegendAdapter(@NonNull Context context) {
        super(context, R.layout.widget_chart_legend);
    }

    public void setAllData(List<TiltSensorBean.DataBean> tiltSensorDatas) {
        if (tiltSensorDatas == null || tiltSensorDatas.size() == 0)
            return;
        this.clear();
        for (int i = 0; i < tiltSensorDatas.size(); i++) {
            if (i - 1 < 0 || !tiltSensorDatas.get(i).getmName().equals(tiltSensorDatas.get(i - 1).getmName())) {
                this.add(new DataBean(tiltSensorDatas.get(i).getmColorReId(), tiltSensorDatas.get(i).getmName()));
            }
        }
    }

    @Override
    public void onUpdate(BaseAdapterHelper helper, ChartLegendAdapter.DataBean item, int position) {
        helper.setText(R.id.chartLegendTv, item.getName());
        helper.setBackgroundRes(R.id.chartLegendIv, item.getColorReId());
    }

    class DataBean {
        int colorReId;
        String name;

        public DataBean() {
        }

        public int getColorReId() {
            return colorReId;
        }

        public void setColorReId(int colorReId) {
            this.colorReId = colorReId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public DataBean(int colorReId, String name) {
            this.colorReId = colorReId;
            this.name = name;

        }
    }
}
