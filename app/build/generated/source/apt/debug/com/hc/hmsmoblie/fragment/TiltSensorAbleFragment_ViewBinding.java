// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.widget.vh.VHLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TiltSensorAbleFragment_ViewBinding implements Unbinder {
  private TiltSensorAbleFragment target;

  private View view2131296657;

  private View view2131296658;

  private View view2131296310;

  private View view2131296309;

  @UiThread
  public TiltSensorAbleFragment_ViewBinding(final TiltSensorAbleFragment target, View source) {
    this.target = target;

    View view;
    target.VHLayout = Utils.findRequiredViewAsType(source, R.id.VHLayout, "field 'VHLayout'", VHLayout.class);
    target.tiltSensorTypeSp = Utils.findRequiredViewAsType(source, R.id.tiltSensorTypeSp, "field 'tiltSensorTypeSp'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.tiltSensorTimeEndTv, "field 'tiltSensorTimeEndTv' and method 'onClick'");
    target.tiltSensorTimeEndTv = Utils.castView(view, R.id.tiltSensorTimeEndTv, "field 'tiltSensorTimeEndTv'", TextView.class);
    view2131296657 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tiltSensorTimeStartTv, "field 'tiltSensorTimeStartTv' and method 'onClick'");
    target.tiltSensorTimeStartTv = Utils.castView(view, R.id.tiltSensorTimeStartTv, "field 'tiltSensorTimeStartTv'", TextView.class);
    view2131296658 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btSelectItem, "field 'btSelectItem' and method 'onClick'");
    target.btSelectItem = Utils.castView(view, R.id.btSelectItem, "field 'btSelectItem'", TextView.class);
    view2131296310 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.tiltSensorAbleLine = Utils.findRequiredView(source, R.id.tilt_Sensor_Able_Line, "field 'tiltSensorAbleLine'");
    view = Utils.findRequiredView(source, R.id.btCruiseDataSearch, "method 'onClick'");
    view2131296309 = view;
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
    TiltSensorAbleFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.VHLayout = null;
    target.tiltSensorTypeSp = null;
    target.tiltSensorTimeEndTv = null;
    target.tiltSensorTimeStartTv = null;
    target.btSelectItem = null;
    target.tiltSensorAbleLine = null;

    view2131296657.setOnClickListener(null);
    view2131296657 = null;
    view2131296658.setOnClickListener(null);
    view2131296658 = null;
    view2131296310.setOnClickListener(null);
    view2131296310 = null;
    view2131296309.setOnClickListener(null);
    view2131296309 = null;
  }
}
