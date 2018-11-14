// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoSelectProjectFragment_ViewBinding implements Unbinder {
  private VideoSelectProjectFragment target;

  private View view2131296323;

  private View view2131296454;

  @UiThread
  public VideoSelectProjectFragment_ViewBinding(final VideoSelectProjectFragment target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_Search_SelectProject, "field 'btnSearchSelectProject' and method 'onClick'");
    target.btnSearchSelectProject = Utils.castView(view, R.id.btn_Search_SelectProject, "field 'btnSearchSelectProject'", Button.class);
    view2131296323 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.editSearchSelectProject = Utils.findRequiredViewAsType(source, R.id.edit_Search_SelectProject, "field 'editSearchSelectProject'", EditText.class);
    target.recyclerViewSelectProject = Utils.findRequiredViewAsType(source, R.id.rv_SelectProject, "field 'recyclerViewSelectProject'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.iv_List_Main, "method 'onClick'");
    view2131296454 = view;
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
    VideoSelectProjectFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnSearchSelectProject = null;
    target.editSearchSelectProject = null;
    target.recyclerViewSelectProject = null;

    view2131296323.setOnClickListener(null);
    view2131296323 = null;
    view2131296454.setOnClickListener(null);
    view2131296454 = null;
  }
}
