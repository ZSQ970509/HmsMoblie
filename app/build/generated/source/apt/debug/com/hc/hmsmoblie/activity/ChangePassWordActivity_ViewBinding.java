// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePassWordActivity_ViewBinding implements Unbinder {
  private ChangePassWordActivity target;

  private View view2131296415;

  @UiThread
  public ChangePassWordActivity_ViewBinding(ChangePassWordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePassWordActivity_ViewBinding(final ChangePassWordActivity target, View source) {
    this.target = target;

    View view;
    target.editTextChangeOldPassWord = Utils.findRequiredViewAsType(source, R.id.editText_Change_OldPassWord, "field 'editTextChangeOldPassWord'", EditText.class);
    target.editTextChangeNewPassWord = Utils.findRequiredViewAsType(source, R.id.editText_Change_NewPassWord, "field 'editTextChangeNewPassWord'", EditText.class);
    target.editTextChangeNewPassWordAgain = Utils.findRequiredViewAsType(source, R.id.editText_Change_NewPassWord_Again, "field 'editTextChangeNewPassWordAgain'", EditText.class);
    view = Utils.findRequiredView(source, R.id.imageView_Change_Submit, "method 'onViewClicked'");
    view2131296415 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangePassWordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editTextChangeOldPassWord = null;
    target.editTextChangeNewPassWord = null;
    target.editTextChangeNewPassWordAgain = null;

    view2131296415.setOnClickListener(null);
    view2131296415 = null;
  }
}
