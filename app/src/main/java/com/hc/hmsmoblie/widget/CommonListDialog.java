package com.hc.hmsmoblie.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hc.hmsmoblie.R;

/**
 * 通用的对话框
 */
public class CommonListDialog extends Dialog {
    private LinearLayout linearLayoutVideo;
    private LinearLayout linearLayoutImageLog;
    private LinearLayout linearLayoutDipAngle;
    private TextView textViewVideo;
    private TextView textViewImageLog;
    private TextView textViewDipAngle;
    private TextViewVideoClick textViewVideoClick;
    private TextViewImageLogClick textViewImageLogClick;
    private TextViewDipAngleClick textViewDipAngleClick;
    public static CommonListDialog newInstance(Context context) {
        return new CommonListDialog(context);
    }

    public static CommonListDialog newInstanceType(Context context,int type) {
        CommonListDialog dialog = new CommonListDialog(context);
        switch (type){
            case 1:
                dialog.linearLayoutImageLog.setVisibility(View.GONE);
                dialog.linearLayoutDipAngle.setVisibility(View.GONE);
                break;
            case 2:
                dialog.linearLayoutDipAngle.setVisibility(View.GONE);
                break;
            case 3:
                dialog.linearLayoutVideo.setVisibility(View.GONE);
        }
        return dialog;
    }

    public CommonListDialog(Context context) {
        this(context, R.style.CommonDialogStyle);
    }

    private CommonListDialog(Context context, int theme) {
        super(context, theme);
        initViews(context);
    }

    private void initViews(Context context) {
        setContentView(R.layout.widget_dialog_list);
        linearLayoutVideo = (LinearLayout) findViewById(R.id.linearLayout_Video);
        linearLayoutImageLog = (LinearLayout) findViewById(R.id.linearLayout_Image_Log);
        linearLayoutDipAngle = (LinearLayout) findViewById(R.id.linearLayout_Dip_Angle);
        textViewVideo = (TextView) findViewById(R.id.textView_Video);
        textViewImageLog = (TextView) findViewById(R.id.textView_Image_Log);
        textViewDipAngle = (TextView) findViewById(R.id.textView_Dip_Angle);
        //设置对话框位置大小
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        dialogWindow.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setAttributes(lp);//此处暂未设置偏移量
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        textViewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (textViewVideo != null) {
                    textViewVideoClick.onClick(v);
                }
            }
        });
        textViewImageLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (textViewImageLog != null) {
                    textViewImageLogClick.onClick(v);
                }
            }
        });
        textViewDipAngle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (textViewDipAngle != null) {
                    textViewDipAngleClick.OnClick(v);
                }
            }
        });
    }

    /**
     * 设置视频查看按钮点击事件
     *
     * @param textViewVideoClick
     * @return
     */
    public CommonListDialog setTextViewVideoClick(TextViewVideoClick textViewVideoClick) {
        this.textViewVideoClick = textViewVideoClick;
        return this;
    }

    /**
     * 设置全景图按钮点击事件
     *
     * @param textViewImageLogClick
     * @return
     */
    public CommonListDialog setTextViewImageLogClick(TextViewImageLogClick textViewImageLogClick) {
        this.textViewImageLogClick = textViewImageLogClick;
        return this;
    }

    /**
     * 设置倾角数据按钮点击事件
     *
     * @param textViewDipAngleClick
     * @return
     */
    public CommonListDialog setTextViewDipAngleClick(TextViewDipAngleClick textViewDipAngleClick) {
        this.textViewDipAngleClick = textViewDipAngleClick;
        return this;
    }

    public interface TextViewVideoClick {
        void onClick(View v);
    }

    public interface TextViewImageLogClick {
        void onClick(View v);
    }

    public interface TextViewDipAngleClick {
        void OnClick(View v);
    }
}
