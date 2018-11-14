// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EnvironmentSelectProjectActivity_ViewBinding implements Unbinder {
  private EnvironmentSelectProjectActivity target;

  @UiThread
  public EnvironmentSelectProjectActivity_ViewBinding(EnvironmentSelectProjectActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EnvironmentSelectProjectActivity_ViewBinding(EnvironmentSelectProjectActivity target,
      View source) {
    this.target = target;

    target.btnSearchSelectProject = Utils.findRequiredViewAsType(source, R.id.btn_Search_SelectProject, "field 'btnSearchSelectProject'", Button.class);
    target.editSearchSelectProject = Utils.findRequiredViewAsType(source, R.id.edit_Search_SelectProject, "field 'editSearchSelectProject'", EditText.class);
    target.recyclerViewSelectProject = Utils.findRequiredViewAsType(source, R.id.rv_SelectProject, "field 'recyclerViewSelectProject'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EnvironmentSelectProjectActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnSearchSelectProject = null;
    target.editSearchSelectProject = null;
    target.recyclerViewSelectProject = null;
  }
}
