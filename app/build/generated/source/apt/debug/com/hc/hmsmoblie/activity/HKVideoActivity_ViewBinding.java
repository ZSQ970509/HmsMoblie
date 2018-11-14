// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.widget.CustomSurfaceView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HKVideoActivity_ViewBinding implements Unbinder {
  private HKVideoActivity target;

  private View view2131296430;

  private View view2131296345;

  private View view2131296347;

  private View view2131296342;

  private View view2131296349;

  private View view2131296344;

  private View view2131296343;

  private View view2131296346;

  @UiThread
  public HKVideoActivity_ViewBinding(HKVideoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HKVideoActivity_ViewBinding(final HKVideoActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ivHuXinBack, "field 'ivHuXinBack' and method 'onClick'");
    target.ivHuXinBack = Utils.castView(view, R.id.ivHuXinBack, "field 'ivHuXinBack'", ImageView.class);
    view2131296430 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.control_Main, "field 'imageView_main' and method 'onClick'");
    target.imageView_main = Utils.castView(view, R.id.control_Main, "field 'imageView_main'", ImageView.class);
    view2131296345 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.control_Setting, "field 'controlSetting' and method 'onClick'");
    target.controlSetting = Utils.castView(view, R.id.control_Setting, "field 'controlSetting'", ImageView.class);
    view2131296347 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.control_Direction, "field 'controlDirection' and method 'onClick'");
    target.controlDirection = Utils.castView(view, R.id.control_Direction, "field 'controlDirection'", ImageView.class);
    view2131296342 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.control_Up, "field 'controlUp' and method 'onClick'");
    target.controlUp = Utils.castView(view, R.id.control_Up, "field 'controlUp'", ImageView.class);
    view2131296349 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.control_Left, "field 'controlLeft' and method 'onClick'");
    target.controlLeft = Utils.castView(view, R.id.control_Left, "field 'controlLeft'", ImageView.class);
    view2131296344 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.control_Down, "field 'controlDown' and method 'onClick'");
    target.controlDown = Utils.castView(view, R.id.control_Down, "field 'controlDown'", ImageView.class);
    view2131296343 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.control_Right, "field 'controlRight' and method 'onClick'");
    target.controlRight = Utils.castView(view, R.id.control_Right, "field 'controlRight'", ImageView.class);
    view2131296346 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.csvHkVideo = Utils.findRequiredViewAsType(source, R.id.csv_Hk_Video, "field 'csvHkVideo'", CustomSurfaceView.class);
    target.layoutPross = Utils.findRequiredView(source, R.id.layout_pross, "field 'layoutPross'");
  }

  @Override
  @CallSuper
  public void unbind() {
    HKVideoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivHuXinBack = null;
    target.imageView_main = null;
    target.controlSetting = null;
    target.controlDirection = null;
    target.controlUp = null;
    target.controlLeft = null;
    target.controlDown = null;
    target.controlRight = null;
    target.csvHkVideo = null;
    target.layoutPross = null;

    view2131296430.setOnClickListener(null);
    view2131296430 = null;
    view2131296345.setOnClickListener(null);
    view2131296345 = null;
    view2131296347.setOnClickListener(null);
    view2131296347 = null;
    view2131296342.setOnClickListener(null);
    view2131296342 = null;
    view2131296349.setOnClickListener(null);
    view2131296349 = null;
    view2131296344.setOnClickListener(null);
    view2131296344 = null;
    view2131296343.setOnClickListener(null);
    view2131296343 = null;
    view2131296346.setOnClickListener(null);
    view2131296346 = null;
  }
}
