// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoSelectDriverActivity_ViewBinding implements Unbinder {
  private VideoSelectDriverActivity target;

  @UiThread
  public VideoSelectDriverActivity_ViewBinding(VideoSelectDriverActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VideoSelectDriverActivity_ViewBinding(VideoSelectDriverActivity target, View source) {
    this.target = target;

    target.recyclerViewSelectDriver = Utils.findRequiredViewAsType(source, R.id.rv_SelectDriver, "field 'recyclerViewSelectDriver'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VideoSelectDriverActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerViewSelectDriver = null;
  }
}
