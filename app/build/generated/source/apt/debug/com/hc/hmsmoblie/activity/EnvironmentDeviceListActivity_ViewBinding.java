// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EnvironmentDeviceListActivity_ViewBinding implements Unbinder {
  private EnvironmentDeviceListActivity target;

  @UiThread
  public EnvironmentDeviceListActivity_ViewBinding(EnvironmentDeviceListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EnvironmentDeviceListActivity_ViewBinding(EnvironmentDeviceListActivity target,
      View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.rvEnvironmentDeviceList, "field 'mRecyclerView'", RecyclerView.class);
    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.srlEnvironmentDeviceList, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EnvironmentDeviceListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
    target.mSwipeRefreshLayout = null;
  }
}
