// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131230925;

  private View view2131230924;

  private View view2131230922;

  private View view2131230923;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.mDrawerLayout = Utils.findRequiredViewAsType(source, R.id.main_drawerLayout, "field 'mDrawerLayout'", DrawerLayout.class);
    target.mNavigationView = Utils.findRequiredViewAsType(source, R.id.main_navigationView, "field 'mNavigationView'", NavigationView.class);
    view = Utils.findRequiredView(source, R.id.main_RelativeLayout_UpadtePass, "field 'mainRelativeLayoutUpadtePass' and method 'onClick'");
    target.mainRelativeLayoutUpadtePass = Utils.castView(view, R.id.main_RelativeLayout_UpadtePass, "field 'mainRelativeLayoutUpadtePass'", RelativeLayout.class);
    view2131230925 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.main_RelativeLayout_Setting, "field 'mainRelativeLayoutSetting' and method 'onClick'");
    target.mainRelativeLayoutSetting = Utils.castView(view, R.id.main_RelativeLayout_Setting, "field 'mainRelativeLayoutSetting'", RelativeLayout.class);
    view2131230924 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.main_RelativeLayout_About, "field 'mainRelativeLayoutAbout' and method 'onClick'");
    target.mainRelativeLayoutAbout = Utils.castView(view, R.id.main_RelativeLayout_About, "field 'mainRelativeLayoutAbout'", RelativeLayout.class);
    view2131230922 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.main_RelativeLayout_Exit, "field 'mainRelativeLayoutExit' and method 'onClick'");
    target.mainRelativeLayoutExit = Utils.castView(view, R.id.main_RelativeLayout_Exit, "field 'mainRelativeLayoutExit'", RelativeLayout.class);
    view2131230923 = view;
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
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mDrawerLayout = null;
    target.mNavigationView = null;
    target.mainRelativeLayoutUpadtePass = null;
    target.mainRelativeLayoutSetting = null;
    target.mainRelativeLayoutAbout = null;
    target.mainRelativeLayoutExit = null;

    view2131230925.setOnClickListener(null);
    view2131230925 = null;
    view2131230924.setOnClickListener(null);
    view2131230924 = null;
    view2131230922.setOnClickListener(null);
    view2131230922 = null;
    view2131230923.setOnClickListener(null);
    view2131230923 = null;
  }
}
