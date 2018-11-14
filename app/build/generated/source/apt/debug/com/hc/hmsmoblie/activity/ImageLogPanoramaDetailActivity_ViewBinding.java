// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.widget.MatrixImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ImageLogPanoramaDetailActivity_ViewBinding implements Unbinder {
  private ImageLogPanoramaDetailActivity target;

  private View view2131296444;

  private View view2131296443;

  private View view2131296442;

  @UiThread
  public ImageLogPanoramaDetailActivity_ViewBinding(ImageLogPanoramaDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ImageLogPanoramaDetailActivity_ViewBinding(final ImageLogPanoramaDetailActivity target,
      View source) {
    this.target = target;

    View view;
    target.mIvPanorama = Utils.findRequiredViewAsType(source, R.id.ivImageLogPanoramaDetails, "field 'mIvPanorama'", MatrixImageView.class);
    view = Utils.findRequiredView(source, R.id.ivImageLogPanoramaDetailsNext, "method 'onClick'");
    view2131296444 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivImageLogPanoramaDetailsBefore, "method 'onClick'");
    view2131296443 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivImageLogPanoramaDetailsBack, "method 'onClick'");
    view2131296442 = view;
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
    ImageLogPanoramaDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvPanorama = null;

    view2131296444.setOnClickListener(null);
    view2131296444 = null;
    view2131296443.setOnClickListener(null);
    view2131296443 = null;
    view2131296442.setOnClickListener(null);
    view2131296442 = null;
  }
}
