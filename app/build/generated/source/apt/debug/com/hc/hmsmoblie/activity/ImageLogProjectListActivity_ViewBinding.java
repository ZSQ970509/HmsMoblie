// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ImageLogProjectListActivity_ViewBinding implements Unbinder {
  private ImageLogProjectListActivity target;

  private View view2131296318;

  @UiThread
  public ImageLogProjectListActivity_ViewBinding(ImageLogProjectListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ImageLogProjectListActivity_ViewBinding(final ImageLogProjectListActivity target,
      View source) {
    this.target = target;

    View view;
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.rvLadderControl, "field 'mRecyclerView'", RecyclerView.class);
    target.mEdtKey = Utils.findRequiredViewAsType(source, R.id.edtLadderControl, "field 'mEdtKey'", EditText.class);
    target.mSwipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.srlLadderControl, "field 'mSwipeRefreshLayout'", SwipeRefreshLayout.class);
    view = Utils.findRequiredView(source, R.id.btnLadderControl, "method 'onClick'");
    view2131296318 = view;
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
    ImageLogProjectListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
    target.mEdtKey = null;
    target.mSwipeRefreshLayout = null;

    view2131296318.setOnClickListener(null);
    view2131296318 = null;
  }
}
