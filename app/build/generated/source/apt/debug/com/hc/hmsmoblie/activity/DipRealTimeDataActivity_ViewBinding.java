// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DipRealTimeDataActivity_ViewBinding implements Unbinder {
  private DipRealTimeDataActivity target;

  private View view2131296729;

  private View view2131296682;

  private View view2131296742;

  private View view2131296743;

  private View view2131296655;

  private View view2131296686;

  @UiThread
  public DipRealTimeDataActivity_ViewBinding(DipRealTimeDataActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DipRealTimeDataActivity_ViewBinding(final DipRealTimeDataActivity target, View source) {
    this.target = target;

    View view;
    target.ivActionbarLeft = Utils.findRequiredViewAsType(source, R.id.ivActionbarLeft, "field 'ivActionbarLeft'", ImageView.class);
    target.tvActionbarLeft = Utils.findRequiredViewAsType(source, R.id.tvActionbarLeft, "field 'tvActionbarLeft'", TextView.class);
    target.tvActionbarMid = Utils.findRequiredViewAsType(source, R.id.tvActionbarMid, "field 'tvActionbarMid'", TextView.class);
    target.ivActionbarRight = Utils.findRequiredViewAsType(source, R.id.ivActionbarRight, "field 'ivActionbarRight'", ImageView.class);
    target.tvActionbarRight = Utils.findRequiredViewAsType(source, R.id.tvActionbarRight, "field 'tvActionbarRight'", TextView.class);
    target.LlActionbarRight = Utils.findRequiredViewAsType(source, R.id.LlActionbarRight, "field 'LlActionbarRight'", LinearLayout.class);
    target.tiltSensorTypeSp = Utils.findRequiredViewAsType(source, R.id.tiltSensorTypeSp, "field 'tiltSensorTypeSp'", Spinner.class);
    target.tvCreateTime = Utils.findRequiredViewAsType(source, R.id.tvCreateTime, "field 'tvCreateTime'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tvRefreshTime, "field 'tvRefreshTime' and method 'onClick'");
    target.tvRefreshTime = Utils.castView(view, R.id.tvRefreshTime, "field 'tvRefreshTime'", TextView.class);
    view2131296729 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvAlarm, "field 'tvAlarm' and method 'onClick'");
    target.tvAlarm = Utils.castView(view, R.id.tvAlarm, "field 'tvAlarm'", TextView.class);
    view2131296682 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvTitleSensorCheck, "field 'tvTitleSensorCheck' and method 'onClick'");
    target.tvTitleSensorCheck = Utils.castView(view, R.id.tvTitleSensorCheck, "field 'tvTitleSensorCheck'", TextView.class);
    view2131296742 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvTitleSensorSetting, "field 'tvTitleSensorSetting' and method 'onClick'");
    target.tvTitleSensorSetting = Utils.castView(view, R.id.tvTitleSensorSetting, "field 'tvTitleSensorSetting'", TextView.class);
    view2131296743 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rvData = Utils.findRequiredViewAsType(source, R.id.rvData, "field 'rvData'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tiltSensorStateTv, "field 'tiltSensorStateTv' and method 'onClick'");
    target.tiltSensorStateTv = Utils.castView(view, R.id.tiltSensorStateTv, "field 'tiltSensorStateTv'", TextView.class);
    view2131296655 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvCharDetails, "method 'onClick'");
    view2131296686 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DipRealTimeDataActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivActionbarLeft = null;
    target.tvActionbarLeft = null;
    target.tvActionbarMid = null;
    target.ivActionbarRight = null;
    target.tvActionbarRight = null;
    target.LlActionbarRight = null;
    target.tiltSensorTypeSp = null;
    target.tvCreateTime = null;
    target.tvRefreshTime = null;
    target.tvAlarm = null;
    target.tvTitleSensorCheck = null;
    target.tvTitleSensorSetting = null;
    target.rvData = null;
    target.tiltSensorStateTv = null;

    view2131296729.setOnClickListener(null);
    view2131296729 = null;
    view2131296682.setOnClickListener(null);
    view2131296682 = null;
    view2131296742.setOnClickListener(null);
    view2131296742 = null;
    view2131296743.setOnClickListener(null);
    view2131296743 = null;
    view2131296655.setOnClickListener(null);
    view2131296655 = null;
    view2131296686.setOnClickListener(null);
    view2131296686 = null;
  }
}
