package com.hc.hmsmoblie.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.domain.TiltSensorAlarmBean;
import com.hc.hmsmoblie.bean.domain.TiltSensorSettingBean;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class TitleSenorSettingDialog extends Dialog {
    //    @BindView()
    EditText alarmAxisXEdt;
    //    @BindView(R.id.alarmAxisYEdt)
    EditText alarmAxisYEdt;
    //    @BindView(R.id.alarmSettlementEdt)
    EditText alarmSettlementEdt;
    //    @BindView(R.id.alarmSpaceEdt)
    EditText alarmSpaceEdt;

    EditText alarmCacheTimeEdt;

    Spinner alarmSpinner;
    //    @BindView(R.id.alarmLeftTv)
    TextView alarmLeftTv;
    //    @BindView(R.id.alarmRightTv)
    TextView alarmRightTv;
    private RightClick mRightClick;
    private LeftClick mLeftClick;
    private TiltSensorSettingBean mTiltSensorSettingBean;
    private CommonAdapter<String> mSpAdapter;
//    public static AlarmDialog newInstance(Context context, TiltSensorAlarmBean tiltSensorAlarmBean) {
//        return new AlarmDialog(context, tiltSensorAlarmBean);
//    }

    public TitleSenorSettingDialog(Context context) {
        this(context, R.style.CommonDialogStyle);
    }

    private TitleSenorSettingDialog(Context context, int theme) {
        super(context, theme);
        initViews(context);
    }

    private void initViews(Context context) {
        setContentView(R.layout.dip_title_sensor_setting);
        alarmAxisXEdt = findViewById(R.id.alarmAxisXEdt);

        alarmAxisYEdt = findViewById(R.id.alarmAxisYEdt);

        alarmSettlementEdt = findViewById(R.id.alarmSettlementEdt);
        alarmSpaceEdt = findViewById(R.id.alarmSpaceEdt);
        alarmSpinner = findViewById(R.id.alarmSpinner);
        alarmCacheTimeEdt = findViewById(R.id.alarmCacheTime);
        alarmLeftTv = findViewById(R.id.alarmLeftTv);
        alarmRightTv = findViewById(R.id.alarmRightTv);
        getSpinnerData(context);
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

                if (mLeftClick != null) {
                    /*mTiltSensorSettingBean.setAxisX(Double.parseDouble(alarmAxisXEdt.getText().toString().trim()));
                    mTiltSensorSettingBean.setAxisY(Double.parseDouble(alarmAxisYEdt.getText().toString().trim()));
                    mTiltSensorSettingBean.setSettlement(Double.parseDouble(alarmSettlementEdt.getText().toString().trim()));
                    mTiltSensorSettingBean.setSpace(Double.parseDouble(alarmSpaceEdt.getText().toString().trim()));
                    mTiltSensorSettingBean.setAlreadySet(true);
                   */
                    if(alarmAxisXEdt.getText().toString().equals("") || alarmAxisYEdt.getText().toString().equals("")
                            || alarmSettlementEdt.getText().toString().equals("") || alarmSpaceEdt.getText().toString().equals("")
                            || alarmCacheTimeEdt.getText().toString().equals("")){
                        mTiltSensorSettingBean = null;
                    }else{
                        dismiss();
                        mTiltSensorSettingBean = new TiltSensorSettingBean();
                        mTiltSensorSettingBean.setAxisX(Double.parseDouble(alarmAxisXEdt.getText().toString())*10000);
                        mTiltSensorSettingBean.setAxisY(Double.parseDouble(alarmAxisYEdt.getText().toString())*10000);
                        mTiltSensorSettingBean.setRptPer(Integer.parseInt(alarmSettlementEdt.getText().toString()));
                        mTiltSensorSettingBean.setRptPer_warn(Integer.parseInt(alarmSpaceEdt.getText().toString()));
                        mTiltSensorSettingBean.setDevState(alarmSpinner.getSelectedItemPosition());
                        mTiltSensorSettingBean.setCacheTime(Integer.parseInt(alarmCacheTimeEdt.getText().toString()));

                    }
                    mLeftClick.onClick(mTiltSensorSettingBean);
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
    }
    public void getSpinnerData(Context context){
        List<String> dataList = new ArrayList<String>();
        dataList.add("开启");
        dataList.add("关闭");
        dataList.add("重启");
        mSpAdapter = new CommonAdapter<String>(context, R.layout.item_common) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {
                helper.setText(R.id.itemCommonTv, item);
            }
        };
        mSpAdapter.addAll(dataList);
        alarmSpinner.setAdapter(mSpAdapter);
        alarmSpinner.setSelection(0);
    }
    public TitleSenorSettingDialog setAlarmData(TiltSensorSettingBean alarmData) {
        mTiltSensorSettingBean = new TiltSensorSettingBean(alarmData);
        Log.e("getSlope_Thres_2",mTiltSensorSettingBean.getAxisX()+"");
        Log.e("getSlope_Thres_2",mTiltSensorSettingBean.getAxisY()+"");
        alarmAxisXEdt.setText(mTiltSensorSettingBean.getAxisX() + "");
        alarmAxisYEdt.setText(mTiltSensorSettingBean.getAxisY() + "");
        alarmSettlementEdt.setText(FormatUtils.stripTrailingZeros(mTiltSensorSettingBean.getRptPer()) + "");
        alarmSpaceEdt.setText(FormatUtils.stripTrailingZeros(mTiltSensorSettingBean.getRptPer_warn()) + "");
        alarmSpinner.setSelection(mTiltSensorSettingBean.getDevState());
        alarmCacheTimeEdt.setText(FormatUtils.stripTrailingZeros(mTiltSensorSettingBean.getCacheTime()) + "");
        return this;
    }

    /**
     * 设置左侧按钮字符串
     */
    public TitleSenorSettingDialog setLeftBtnText(String text) {
        if (!TextUtils.isEmpty(text))
            alarmLeftTv.setText(text);
        return this;
    }


    /**
     * 设置右侧按钮字符串
     *
     * @param text
     */
    public TitleSenorSettingDialog setRightBtnText(String text) {
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
    public TitleSenorSettingDialog setLeftClick(LeftClick leftClick) {
        mLeftClick = leftClick;
        return this;
    }

    /**
     * 设置右侧按钮点击事件
     *
     * @param rightClick
     * @return
     */
    public TitleSenorSettingDialog setRightClick(RightClick rightClick) {
        mRightClick = rightClick;
        return this;
    }

    public interface RightClick {
        void onClick(View v);
    }

    public interface LeftClick {
        void onClick(TiltSensorSettingBean mTiltSensorSettingBean);
    }


}
