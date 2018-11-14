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

public class ImageLogWideAngleActivity_ViewBinding implements Unbinder {
  private ImageLogWideAngleActivity target;

  private View view2131296447;

  @UiThread
  public ImageLogWideAngleActivity_ViewBinding(ImageLogWideAngleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ImageLogWideAngleActivity_ViewBinding(final ImageLogWideAngleActivity target,
      View source) {
    this.target = target;

    View view;
    target.mIvWideAngle = Utils.findRequiredViewAsType(source, R.id.ivImageLogWideAngle, "field 'mIvWideAngle'", MatrixImageView.class);
    view = Utils.findRequiredView(source, R.id.ivImageLogWideAngleBack, "method 'onClick'");
    view2131296447 = view;
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
    ImageLogWideAngleActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvWideAngle = null;

    view2131296447.setOnClickListener(null);
    view2131296447 = null;
  }
}
