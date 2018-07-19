// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.CheckBox;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131230771;

  private View view2131230921;

  private View view2131230893;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.mUserAccountTv = Utils.findRequiredViewAsType(source, R.id.tvLoginUserAccount, "field 'mUserAccountTv'", TextInputEditText.class);
    target.mUserPasswordTv = Utils.findRequiredViewAsType(source, R.id.tvLoginUserPassword, "field 'mUserPasswordTv'", TextInputEditText.class);
    target.mIsSavePasswordCb = Utils.findRequiredViewAsType(source, R.id.login_save_password_cb, "field 'mIsSavePasswordCb'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.btnLogin, "method 'onClick'");
    view2131230771 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.login_save_password_tv, "method 'onClick'");
    view2131230921 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ivLoginSetting, "method 'onClick'");
    view2131230893 = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mUserAccountTv = null;
    target.mUserPasswordTv = null;
    target.mIsSavePasswordCb = null;

    view2131230771.setOnClickListener(null);
    view2131230771 = null;
    view2131230921.setOnClickListener(null);
    view2131230921 = null;
    view2131230893.setOnClickListener(null);
    view2131230893 = null;
  }
}
