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

public class AboutActivity_ViewBinding implements Unbinder {
  private AboutActivity target;

  private View view2131296416;

  @UiThread
  public AboutActivity_ViewBinding(AboutActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AboutActivity_ViewBinding(final AboutActivity target, View source) {
    this.target = target;

    View view;
    target.textviewNowVersion = Utils.findRequiredViewAsType(source, R.id.textview_NowVersion, "field 'textviewNowVersion'", TextView.class);
    view = Utils.findRequiredView(source, R.id.imageView_Update_Btn, "field 'imageViewUpdateBtn' and method 'onClick'");
    target.imageViewUpdateBtn = Utils.castView(view, R.id.imageView_Update_Btn, "field 'imageViewUpdateBtn'", ImageView.class);
    view2131296416 = view;
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
    AboutActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textviewNowVersion = null;
    target.imageViewUpdateBtn = null;

    view2131296416.setOnClickListener(null);
    view2131296416 = null;
  }
}
