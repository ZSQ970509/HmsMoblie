// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TiltSensorActivity_ViewBinding implements Unbinder {
  private TiltSensorActivity target;

  private View view2131296656;

  private View view2131296629;

  @UiThread
  public TiltSensorActivity_ViewBinding(TiltSensorActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TiltSensorActivity_ViewBinding(final TiltSensorActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tiltSensorTableCb, "field 'tiltSensorTableCb' and method 'onViewClicked'");
    target.tiltSensorTableCb = Utils.castView(view, R.id.tiltSensorTableCb, "field 'tiltSensorTableCb'", CheckBox.class);
    view2131296656 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tiltSensorChartCb, "field 'tiltSensorChartCb' and method 'onViewClicked'");
    target.tiltSensorChartCb = Utils.castView(view, R.id.tiltSensorChartCb, "field 'tiltSensorChartCb'", CheckBox.class);
    view2131296629 = view;
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
    TiltSensorActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tiltSensorTableCb = null;
    target.tiltSensorChartCb = null;

    view2131296656.setOnClickListener(null);
    view2131296656 = null;
    view2131296629.setOnClickListener(null);
    view2131296629 = null;
  }
}
