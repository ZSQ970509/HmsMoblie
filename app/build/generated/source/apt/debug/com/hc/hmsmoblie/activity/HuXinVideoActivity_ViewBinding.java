// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.opengl.GLSurfaceView;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HuXinVideoActivity_ViewBinding implements Unbinder {
  private HuXinVideoActivity target;

  private View view2131296430;

  private View view2131296345;

  private View view2131296348;

  private View view2131296347;

  private View view2131296342;

  private View view2131296349;

  private View view2131296344;

  private View view2131296343;

  private View view2131296346;

  @UiThread
  public HuXinVideoActivity_ViewBinding(HuXinVideoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HuXinVideoActivity_ViewBinding(final HuXinVideoActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.control_Speed, "field 'controlSpeed' and method 'onClick'");
    target.controlSpeed = Utils.castView(view, R.id.control_Speed, "field 'controlSpeed'", ImageView.class);
    view2131296348 = view;
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
    target.glvHuXinVideo = Utils.findRequiredViewAsType(source, R.id.glv_HuXin_Video, "field 'glvHuXinVideo'", GLSurfaceView.class);
    target.layoutPross = Utils.findRequiredView(source, R.id.layout_pross, "field 'layoutPross'");
    target.prossTV = Utils.findRequiredViewAsType(source, R.id.txt_pross, "field 'prossTV'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HuXinVideoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivHuXinBack = null;
    target.imageView_main = null;
    target.controlSpeed = null;
    target.controlSetting = null;
    target.controlDirection = null;
    target.controlUp = null;
    target.controlLeft = null;
    target.controlDown = null;
    target.controlRight = null;
    target.glvHuXinVideo = null;
    target.layoutPross = null;
    target.prossTV = null;

    view2131296430.setOnClickListener(null);
    view2131296430 = null;
    view2131296345.setOnClickListener(null);
    view2131296345 = null;
    view2131296348.setOnClickListener(null);
    view2131296348 = null;
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
