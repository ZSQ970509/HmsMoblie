// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

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

public class LadderControlPictureActivity_ViewBinding implements Unbinder {
  private LadderControlPictureActivity target;

  private View view2131296319;

  @UiThread
  public LadderControlPictureActivity_ViewBinding(LadderControlPictureActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LadderControlPictureActivity_ViewBinding(final LadderControlPictureActivity target,
      View source) {
    this.target = target;

    View view;
    target.mTvName = Utils.findRequiredViewAsType(source, R.id.tvLadderControlPictureName, "field 'mTvName'", TextView.class);
    target.mIv1 = Utils.findRequiredViewAsType(source, R.id.ivLadderControlPictureImg1, "field 'mIv1'", ImageView.class);
    target.mIv2 = Utils.findRequiredViewAsType(source, R.id.ivLadderControlPictureImg2, "field 'mIv2'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btnLadderControlPictureClose, "method 'onClick'");
    view2131296319 = view;
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
    LadderControlPictureActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvName = null;
    target.mIv1 = null;
    target.mIv2 = null;

    view2131296319.setOnClickListener(null);
    view2131296319 = null;
  }
}
