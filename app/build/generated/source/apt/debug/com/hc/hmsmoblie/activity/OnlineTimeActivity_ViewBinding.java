// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OnlineTimeActivity_ViewBinding implements Unbinder {
  private OnlineTimeActivity target;

  private View view2131296754;

  private View view2131296750;

  private View view2131296322;

  @UiThread
  public OnlineTimeActivity_ViewBinding(OnlineTimeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OnlineTimeActivity_ViewBinding(final OnlineTimeActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.tv_StartDate_OnlineTime, "field 'tvStartDateOnlineTime' and method 'onClick'");
    target.tvStartDateOnlineTime = Utils.castView(view, R.id.tv_StartDate_OnlineTime, "field 'tvStartDateOnlineTime'", TextView.class);
    view2131296754 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_EndDate_OnlineTime, "field 'tvEndDateOnlineTime' and method 'onClick'");
    target.tvEndDateOnlineTime = Utils.castView(view, R.id.tv_EndDate_OnlineTime, "field 'tvEndDateOnlineTime'", TextView.class);
    view2131296750 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_Search_OnlineTime, "field 'btnSearchOnlineTime' and method 'onClick'");
    target.btnSearchOnlineTime = Utils.castView(view, R.id.btn_Search_OnlineTime, "field 'btnSearchOnlineTime'", Button.class);
    view2131296322 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.rvOnlineTime = Utils.findRequiredViewAsType(source, R.id.rv_OnlineTime, "field 'rvOnlineTime'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OnlineTimeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvStartDateOnlineTime = null;
    target.tvEndDateOnlineTime = null;
    target.btnSearchOnlineTime = null;
    target.rvOnlineTime = null;

    view2131296754.setOnClickListener(null);
    view2131296754 = null;
    view2131296750.setOnClickListener(null);
    view2131296750 = null;
    view2131296322.setOnClickListener(null);
    view2131296322 = null;
  }
}
