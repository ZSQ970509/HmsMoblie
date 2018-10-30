package com.hc.hmsmoblie.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.TintableBackgroundView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.domain.TiltSensorAlarmBean;
import com.hc.hmsmoblie.utils.FormatUtils;

import butterknife.BindView;

/**
 *
 */

public class AlarmDialog extends Dialog {
    //    @BindView()
    EditText alarmAxisXEdt;
    //    @BindView(R.id.alarmAxisYEdt)
    EditText alarmAxisYEdt;
    //    @BindView(R.id.alarmSettlementEdt)
    EditText alarmSettlementEdt;
    //    @BindView(R.id.alarmSpaceEdt)
    EditText alarmSpaceEdt;
    //    @BindView(R.id.alarmHFLeftEdt)
    EditText alarmHFLeftEdt;
    //    @BindView(R.id.alarmHFRightEdt)
    EditText alarmHFRightEdt;
    //    @BindView(R.id.alarmIsOpenCb)
    CheckBox alarmIsOpenCb;
    //    @BindView(R.id.alarmLeftTv)
    TextView alarmLeftTv;
    //    @BindView(R.id.alarmRightTv)
    TextView alarmRightTv;
    private RightClick mRightClick;
    private LeftClick mLeftClick;
    private TiltSensorAlarmBean mTiltSensorAlarmBean;

//    public static AlarmDialog newInstance(Context context, TiltSensorAlarmBean tiltSensorAlarmBean) {
//        return new AlarmDialog(context, tiltSensorAlarmBean);
//    }

    public AlarmDialog(Context context) {
        this(context, R.style.CommonDialogStyle);
    }

    private AlarmDialog(Context context, int theme) {
        super(context, theme);
        initViews(context);
    }

    private void initViews(Context context) {
        setContentView(R.layout.dip_real_time_data_pop);
        alarmAxisXEdt = findViewById(R.id.alarmAxisXEdt);

        alarmAxisYEdt = findViewById(R.id.alarmAxisYEdt);

        alarmSettlementEdt = findViewById(R.id.alarmSettlementEdt);
        alarmSpaceEdt = findViewById(R.id.alarmSpaceEdt);
        alarmHFLeftEdt = findViewById(R.id.alarmHFLeftEdt);

        alarmHFRightEdt = findViewById(R.id.alarmHFRightEdt);

        alarmIsOpenCb = findViewById(R.id.alarmIsOpenCb);


        alarmLeftTv = findViewById(R.id.alarmLeftTv);
        alarmRightTv = findViewById(R.id.alarmRightTv);
        //设置对话框位置大小
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        dialogWindow.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setAttributes(lp);//此处暂未设置偏移量
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        alarmLeftTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mLeftClick != null) {
                    mTiltSensorAlarmBean.setOpen(alarmIsOpenCb.isChecked());
                    mTiltSensorAlarmBean.setAxisX(Double.parseDouble(alarmAxisXEdt.getText().toString().trim()));
                    mTiltSensorAlarmBean.setAxisY(Double.parseDouble(alarmAxisYEdt.getText().toString().trim()));
                    mTiltSensorAlarmBean.setSettlement(Double.parseDouble(alarmSettlementEdt.getText().toString().trim()));
                    mTiltSensorAlarmBean.setSpace(Double.parseDouble(alarmSpaceEdt.getText().toString().trim()));
                    mTiltSensorAlarmBean.setHorizontalFloatingLeft(Double.parseDouble(alarmHFLeftEdt.getText().toString().trim()));
                    mTiltSensorAlarmBean.setHorizontalFloatingRight(Double.parseDouble(alarmHFRightEdt.getText().toString().trim()));
                    mTiltSensorAlarmBean.setAlreadySet(true);
                    mLeftClick.onClick(mTiltSensorAlarmBean);
                }
            }
        });
        alarmRightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mRightClick != null) {
                    mRightClick.onClick(v);
                }
            }
        });
        alarmIsOpenCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTiltSensorAlarmBean.setOpen(isChecked);
            }
        });
    }

    public AlarmDialog setAlarmData(TiltSensorAlarmBean alarmData) {
        mTiltSensorAlarmBean = new TiltSensorAlarmBean(alarmData);
        alarmAxisXEdt.setText(FormatUtils.stripTrailingZeros(mTiltSensorAlarmBean.getAxisX()) + "");
        alarmAxisYEdt.setText(FormatUtils.stripTrailingZeros(mTiltSensorAlarmBean.getAxisY()) + "");
        alarmSettlementEdt.setText(FormatUtils.stripTrailingZeros(mTiltSensorAlarmBean.getSettlement()) + "");
        alarmSpaceEdt.setText(FormatUtils.stripTrailingZeros(mTiltSensorAlarmBean.getSpace()) + "");
        alarmHFLeftEdt.setText(FormatUtils.stripTrailingZeros(mTiltSensorAlarmBean.getHorizontalFloatingLeft()) + "");
        alarmHFRightEdt.setText(FormatUtils.stripTrailingZeros(mTiltSensorAlarmBean.getHorizontalFloatingRight()) + "");
        alarmIsOpenCb.setChecked(mTiltSensorAlarmBean.isOpen());
        return this;
    }

    /**
     * 设置左侧按钮字符串
     */
    public AlarmDialog setLeftBtnText(String text) {
        if (!TextUtils.isEmpty(text))
            alarmLeftTv.setText(text);
        return this;
    }


    /**
     * 设置右侧按钮字符串
     *
     * @param text
     */
    public AlarmDialog setRightBtnText(String text) {
        if (!TextUtils.isEmpty(text))
            alarmRightTv.setText(text);
        return this;
    }

    /**
     * 设置左侧按钮点击事件
     *
     * @param leftClick
     * @return
     */
    public AlarmDialog setLeftClick(LeftClick leftClick) {
        mLeftClick = leftClick;
        return this;
    }

    /**
     * 设置右侧按钮点击事件
     *
     * @param rightClick
     * @return
     */
    public AlarmDialog setRightClick(RightClick rightClick) {
        mRightClick = rightClick;
        return this;
    }

    public interface RightClick {
        void onClick(View v);
    }

    public interface LeftClick {
        void onClick(TiltSensorAlarmBean tiltSensorAlarmBean);
    }

}
