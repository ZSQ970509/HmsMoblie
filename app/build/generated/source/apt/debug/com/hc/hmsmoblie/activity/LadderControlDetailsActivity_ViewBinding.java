// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LadderControlDetailsActivity_ViewBinding implements Unbinder {
  private LadderControlDetailsActivity target;

  @UiThread
  public LadderControlDetailsActivity_ViewBinding(LadderControlDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LadderControlDetailsActivity_ViewBinding(LadderControlDetailsActivity target,
      View source) {
    this.target = target;

    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.vpDataReport, "field 'mViewPager'", ViewPager.class);
    target.mTabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'mTabLayout'", TabLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LadderControlDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mViewPager = null;
    target.mTabLayout = null;
  }
}
