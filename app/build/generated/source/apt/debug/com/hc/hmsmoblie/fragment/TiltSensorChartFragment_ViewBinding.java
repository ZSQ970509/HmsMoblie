// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.mikephil.charting.charts.LineChart;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TiltSensorChartFragment_ViewBinding implements Unbinder {
  private TiltSensorChartFragment target;

  private View view2131296633;

  private View view2131296634;

  private View view2131296652;

  private View view2131296661;

  private View view2131296653;

  @UiThread
  public TiltSensorChartFragment_ViewBinding(final TiltSensorChartFragment target, View source) {
    this.target = target;

    View view;
    target.lineChart = Utils.findRequiredViewAsType(source, R.id.lineChart, "field 'lineChart'", LineChart.class);
    view = Utils.findRequiredView(source, R.id.tiltSensorChartTimeTv, "field 'mTimeTv' and method 'onViewClicked'");
    target.mTimeTv = Utils.castView(view, R.id.tiltSensorChartTimeTv, "field 'mTimeTv'", TextView.class);
    view2131296633 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tiltSensorDayTv, "field 'tiltSensorDayTv' and method 'onViewClicked'");
    target.tiltSensorDayTv = Utils.castView(view, R.id.tiltSensorDayTv, "field 'tiltSensorDayTv'", TextView.class);
    view2131296634 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tiltSensorMonthTv, "field 'tiltSensorMonthTv' and method 'onViewClicked'");
    target.tiltSensorMonthTv = Utils.castView(view, R.id.tiltSensorMonthTv, "field 'tiltSensorMonthTv'", TextView.class);
    view2131296652 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tiltSensorYearTv, "field 'tiltSensorYearTv' and method 'onViewClicked'");
    target.tiltSensorYearTv = Utils.castView(view, R.id.tiltSensorYearTv, "field 'tiltSensorYearTv'", TextView.class);
    view2131296661 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mSp = Utils.findRequiredViewAsType(source, R.id.tiltSensorSp, "field 'mSp'", Spinner.class);
    target.selectItemSp = Utils.findRequiredViewAsType(source, R.id.selectItemSp, "field 'selectItemSp'", Spinner.class);
    target.mChartLegendRv = Utils.findRequiredViewAsType(source, R.id.tiltSensorChartLegendRv, "field 'mChartLegendRv'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tiltSensorSearchTv, "method 'onViewClicked'");
    view2131296653 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    TiltSensorChartFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lineChart = null;
    target.mTimeTv = null;
    target.tiltSensorDayTv = null;
    target.tiltSensorMonthTv = null;
    target.tiltSensorYearTv = null;
    target.mSp = null;
    target.selectItemSp = null;
    target.mChartLegendRv = null;

    view2131296633.setOnClickListener(null);
    view2131296633 = null;
    view2131296634.setOnClickListener(null);
    view2131296634 = null;
    view2131296652.setOnClickListener(null);
    view2131296652 = null;
    view2131296661.setOnClickListener(null);
    view2131296661 = null;
    view2131296653.setOnClickListener(null);
    view2131296653 = null;
  }
}
