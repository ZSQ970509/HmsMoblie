// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.mikephil.charting.charts.LineChart;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EnvironmentDetailsActivity_ViewBinding implements Unbinder {
  private EnvironmentDetailsActivity target;

  private View view2131296312;

  private View view2131296313;

  private View view2131296314;

  private View view2131296315;

  private View view2131296316;

  private View view2131296317;

  @UiThread
  public EnvironmentDetailsActivity_ViewBinding(EnvironmentDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EnvironmentDetailsActivity_ViewBinding(final EnvironmentDetailsActivity target,
      View source) {
    this.target = target;

    View view;
    target.mTvName = Utils.findRequiredViewAsType(source, R.id.tvDetailsName, "field 'mTvName'", TextView.class);
    target.mTvName2 = Utils.findRequiredViewAsType(source, R.id.tvDetailsName2, "field 'mTvName2'", TextView.class);
    target.mTvComplianceRate = Utils.findRequiredViewAsType(source, R.id.tvComplianceRate, "field 'mTvComplianceRate'", TextView.class);
    target.mTvComplianceRate2 = Utils.findRequiredViewAsType(source, R.id.tvComplianceRate2, "field 'mTvComplianceRate2'", TextView.class);
    target.mTvTime = Utils.findRequiredViewAsType(source, R.id.tvDetailsTime, "field 'mTvTime'", TextView.class);
    target.mTvTime2 = Utils.findRequiredViewAsType(source, R.id.tvDetailsTime2, "field 'mTvTime2'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnDetailsTimeForDay, "field 'mTvTimeForDay' and method 'onClick'");
    target.mTvTimeForDay = Utils.castView(view, R.id.btnDetailsTimeForDay, "field 'mTvTimeForDay'", TextView.class);
    view2131296312 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnDetailsTimeForDay2, "field 'mTvTimeForDay2' and method 'onClick'");
    target.mTvTimeForDay2 = Utils.castView(view, R.id.btnDetailsTimeForDay2, "field 'mTvTimeForDay2'", TextView.class);
    view2131296313 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnDetailsTimeForMonth, "field 'mTvTimeForMonth' and method 'onClick'");
    target.mTvTimeForMonth = Utils.castView(view, R.id.btnDetailsTimeForMonth, "field 'mTvTimeForMonth'", TextView.class);
    view2131296314 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnDetailsTimeForMonth2, "field 'mTvTimeForMonth2' and method 'onClick'");
    target.mTvTimeForMonth2 = Utils.castView(view, R.id.btnDetailsTimeForMonth2, "field 'mTvTimeForMonth2'", TextView.class);
    view2131296315 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnDetailsTimeForYear, "field 'mTvTimeForYear' and method 'onClick'");
    target.mTvTimeForYear = Utils.castView(view, R.id.btnDetailsTimeForYear, "field 'mTvTimeForYear'", TextView.class);
    view2131296316 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btnDetailsTimeForYear2, "field 'mTvTimeForYear2' and method 'onClick'");
    target.mTvTimeForYear2 = Utils.castView(view, R.id.btnDetailsTimeForYear2, "field 'mTvTimeForYear2'", TextView.class);
    view2131296317 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mLineChart = Utils.findRequiredViewAsType(source, R.id.lcDetails, "field 'mLineChart'", LineChart.class);
    target.mLineChart2 = Utils.findRequiredViewAsType(source, R.id.lcDetails2, "field 'mLineChart2'", LineChart.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EnvironmentDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvName = null;
    target.mTvName2 = null;
    target.mTvComplianceRate = null;
    target.mTvComplianceRate2 = null;
    target.mTvTime = null;
    target.mTvTime2 = null;
    target.mTvTimeForDay = null;
    target.mTvTimeForDay2 = null;
    target.mTvTimeForMonth = null;
    target.mTvTimeForMonth2 = null;
    target.mTvTimeForYear = null;
    target.mTvTimeForYear2 = null;
    target.mLineChart = null;
    target.mLineChart2 = null;

    view2131296312.setOnClickListener(null);
    view2131296312 = null;
    view2131296313.setOnClickListener(null);
    view2131296313 = null;
    view2131296314.setOnClickListener(null);
    view2131296314 = null;
    view2131296315.setOnClickListener(null);
    view2131296315 = null;
    view2131296316.setOnClickListener(null);
    view2131296316 = null;
    view2131296317.setOnClickListener(null);
    view2131296317 = null;
  }
}
