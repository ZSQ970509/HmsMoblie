// Generated code from Butter Knife. Do not modify!
package com.hc.hmsmoblie.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.hc.hmsmoblie.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoProjectDetailsActivity_ViewBinding implements Unbinder {
  private VideoProjectDetailsActivity target;

  private View view2131296772;

  private View view2131296766;

  @UiThread
  public VideoProjectDetailsActivity_ViewBinding(VideoProjectDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public VideoProjectDetailsActivity_ViewBinding(final VideoProjectDetailsActivity target,
      View source) {
    this.target = target;

    View view;
    target.videoProjectDetailName = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Name, "field 'videoProjectDetailName'", TextView.class);
    target.videoProjectDetailType = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Type, "field 'videoProjectDetailType'", TextView.class);
    target.videoProjectDetailAddress = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Address, "field 'videoProjectDetailAddress'", TextView.class);
    target.videoProjectDetailQualitySupervision = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Quality_Supervision, "field 'videoProjectDetailQualitySupervision'", TextView.class);
    target.videoProjectDetailSafetySupervision = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Safety_Supervision, "field 'videoProjectDetailSafetySupervision'", TextView.class);
    target.videoProjectDetailMoney = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Money, "field 'videoProjectDetailMoney'", TextView.class);
    target.videoProjectDetailArea = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Area, "field 'videoProjectDetailArea'", TextView.class);
    target.videoProjectDetailStartTime = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Start_Time, "field 'videoProjectDetailStartTime'", TextView.class);
    target.videoProjectDetailEndTime = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_End_Time, "field 'videoProjectDetailEndTime'", TextView.class);
    target.videoProjectDetailHumen = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Humen, "field 'videoProjectDetailHumen'", TextView.class);
    target.videoProjectDetailPhone = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Phone, "field 'videoProjectDetailPhone'", TextView.class);
    target.videoProjectDetailAffiliatedArea = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Affiliated_Area, "field 'videoProjectDetailAffiliatedArea'", TextView.class);
    target.videoProjectDetailImage = Utils.findRequiredViewAsType(source, R.id.video_Project_Detail_Image, "field 'videoProjectDetailImage'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.video_Project_Detail_Online_Time, "field 'videoProjectDetailOnlineTime' and method 'onClick'");
    target.videoProjectDetailOnlineTime = Utils.castView(view, R.id.video_Project_Detail_Online_Time, "field 'videoProjectDetailOnlineTime'", RelativeLayout.class);
    view2131296772 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.video_Project_Detail_Driver_List, "field 'videoProjectDetailDriverList' and method 'onClick'");
    target.videoProjectDetailDriverList = Utils.castView(view, R.id.video_Project_Detail_Driver_List, "field 'videoProjectDetailDriverList'", RelativeLayout.class);
    view2131296766 = view;
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
    VideoProjectDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.videoProjectDetailName = null;
    target.videoProjectDetailType = null;
    target.videoProjectDetailAddress = null;
    target.videoProjectDetailQualitySupervision = null;
    target.videoProjectDetailSafetySupervision = null;
    target.videoProjectDetailMoney = null;
    target.videoProjectDetailArea = null;
    target.videoProjectDetailStartTime = null;
    target.videoProjectDetailEndTime = null;
    target.videoProjectDetailHumen = null;
    target.videoProjectDetailPhone = null;
    target.videoProjectDetailAffiliatedArea = null;
    target.videoProjectDetailImage = null;
    target.videoProjectDetailOnlineTime = null;
    target.videoProjectDetailDriverList = null;

    view2131296772.setOnClickListener(null);
    view2131296772 = null;
    view2131296766.setOnClickListener(null);
    view2131296766 = null;
  }
}
