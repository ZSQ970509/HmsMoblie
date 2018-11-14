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
import java.lang.IllegalStateException;
import java.lang.Override;

public class ImageLogNodeActivity_ViewBinding implements Unbinder {
  private ImageLogNodeActivity target;

  private View view2131296440;

  @UiThread
  public ImageLogNodeActivity_ViewBinding(ImageLogNodeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ImageLogNodeActivity_ViewBinding(final ImageLogNodeActivity target, View source) {
    this.target = target;

    View view;
    target.mIvNode11 = Utils.findRequiredViewAsType(source, R.id.ivImageLogNode11, "field 'mIvNode11'", ImageView.class);
    target.mIvNode12 = Utils.findRequiredViewAsType(source, R.id.ivImageLogNode12, "field 'mIvNode12'", ImageView.class);
    target.mIvNode13 = Utils.findRequiredViewAsType(source, R.id.ivImageLogNode13, "field 'mIvNode13'", ImageView.class);
    target.mIvNode21 = Utils.findRequiredViewAsType(source, R.id.ivImageLogNode21, "field 'mIvNode21'", ImageView.class);
    target.mIvNode22 = Utils.findRequiredViewAsType(source, R.id.ivImageLogNode22, "field 'mIvNode22'", ImageView.class);
    target.mIvNode23 = Utils.findRequiredViewAsType(source, R.id.ivImageLogNode23, "field 'mIvNode23'", ImageView.class);
    target.mIvNode31 = Utils.findRequiredViewAsType(source, R.id.ivImageLogNode31, "field 'mIvNode31'", ImageView.class);
    target.mIvNode32 = Utils.findRequiredViewAsType(source, R.id.ivImageLogNode32, "field 'mIvNode32'", ImageView.class);
    target.mIvNode33 = Utils.findRequiredViewAsType(source, R.id.ivImageLogNode33, "field 'mIvNode33'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ivImageLogNodeBack, "method 'onClick'");
    view2131296440 = view;
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
    ImageLogNodeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvNode11 = null;
    target.mIvNode12 = null;
    target.mIvNode13 = null;
    target.mIvNode21 = null;
    target.mIvNode22 = null;
    target.mIvNode23 = null;
    target.mIvNode31 = null;
    target.mIvNode32 = null;
    target.mIvNode33 = null;

    view2131296440.setOnClickListener(null);
    view2131296440 = null;
  }
}
