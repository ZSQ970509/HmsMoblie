// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ImageLogPanoramaListActivity_ViewBinding implements Unbinder {
  private ImageLogPanoramaListActivity target;

  private View view2131296715;

  private View view2131296712;

  private View view2131296714;

  @UiThread
  public ImageLogPanoramaListActivity_ViewBinding(ImageLogPanoramaListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ImageLogPanoramaListActivity_ViewBinding(final ImageLogPanoramaListActivity target,
      View source) {
    this.target = target;

    View view;
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.rcImageLogPanorama, "field 'mRecyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tvImageLogPanoramaStartTime, "field 'mTvStartTime' and method 'onClick'");
    target.mTvStartTime = Utils.castView(view, R.id.tvImageLogPanoramaStartTime, "field 'mTvStartTime'", TextView.class);
    view2131296715 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tvImageLogPanoramaEndTime, "field 'mTvEndTime' and method 'onClick'");
    target.mTvEndTime = Utils.castView(view, R.id.tvImageLogPanoramaEndTime, "field 'mTvEndTime'", TextView.class);
    view2131296712 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.srlImageLogPanorama, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.tvImageLogPanoramaSearch, "method 'onClick'");
    view2131296714 = view;
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
    ImageLogPanoramaListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
    target.mTvStartTime = null;
    target.mTvEndTime = null;
    target.mSwipeRefreshLayout = null;

    view2131296715.setOnClickListener(null);
    view2131296715 = null;
    view2131296712.setOnClickListener(null);
    view2131296712 = null;
    view2131296714.setOnClickListener(null);
    view2131296714 = null;
  }
}
