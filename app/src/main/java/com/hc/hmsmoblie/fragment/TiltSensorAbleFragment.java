package com.hc.hmsmoblie.fragment;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.classic.adapter.BaseAdapterHelper;
import com.classic.adapter.CommonAdapter;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.adapter.SelectItemAdapter;
import com.hc.hmsmoblie.bean.json.SelecItemJson;
import com.hc.hmsmoblie.bean.json.SensorLogJson;
import com.hc.hmsmoblie.bean.json.TiltSensorParaJson;
import com.hc.hmsmoblie.mvp.contact.TiltSensorAbleFragmentC;
import com.hc.hmsmoblie.mvp.presenter.TiltSensorAbleFragmentP;
import com.hc.hmsmoblie.utils.TimePickerUtils;
import com.hc.hmsmoblie.widget.CommonDialog;
import com.hc.hmsmoblie.widget.CustomLoadMoreView;

import com.hc.hmsmoblie.widget.vh.VHAdapter;
import com.hc.hmsmoblie.widget.vh.VHLayout;
import com.yc.yclibrary.base.YcMvpLazyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 倾角流水数据表格
 */

public class TiltSensorAbleFragment extends YcMvpLazyFragment<TiltSensorAbleFragmentP> implements TiltSensorAbleFragmentC.V {
    @BindView(R.id.VHLayout)
    VHLayout VHLayout;
    @BindView(R.id.tiltSensorTypeSp)
    Spinner tiltSensorTypeSp;
    @BindView(R.id.tiltSensorTimeEndTv)
    TextView tiltSensorTimeEndTv;
    @BindView(R.id.tiltSensorTimeStartTv)
    TextView tiltSensorTimeStartTv;
    @BindView(R.id.btSelectItem)
    TextView btSelectItem;
    @BindView(R.id.tilt_Sensor_Able_Line)
    View tiltSensorAbleLine;
    private String mCamId;
    //流水数据
    private ArrayList<SensorLogJson.ListBean> mDataModels = new ArrayList<SensorLogJson.ListBean>();
    //侦测点
    private List<TiltSensorParaJson.ListBean> mParaIds = new ArrayList<TiltSensorParaJson.ListBean>();
    private int pageIndex = 1;
    private int sumPage;
    private String paraID;
    private PopupWindow mPopWindow;
    private CommonAdapter<String> mSpAdapter;
    private SparseArray<Boolean> mTitleVisibility = new SparseArray<>();
    private ArrayList<SelecItemJson> SelecItemJsonList = new ArrayList<SelecItemJson>();
    private ArrayList<Boolean> isCheckedList = new ArrayList<Boolean>();
    private final SparseArray<Boolean> mTitleVisibility1 = new SparseArray<Boolean>() {
    };

    private SparseArray<Boolean> getTitleVisibility(int type) {
        return null;
    }

    public static TiltSensorAbleFragment newInstance(String camId, List<TiltSensorParaJson.ListBean> paraIds) {
        TiltSensorAbleFragment fragment = new TiltSensorAbleFragment();
        fragment.mCamId = camId;
        fragment.mParaIds = paraIds;
        return fragment;
    }

    @Override
    protected TiltSensorAbleFragmentP loadPresenter() {
        return new TiltSensorAbleFragmentP();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.tilt_sensor_able_fragent;
    }

    VHAdapter mAdapter;

    @Override
    public void initView() {
        initSpinner();
        initData();
    }

    @Override
    public void onGetTiltSensorLogSuccess(SensorLogJson sensorLogJson) {
        if (sensorLogJson.getList().size() == 0) {
            showToast("暂无数据！");
        }
        mDataModels.addAll(sensorLogJson.getList());
        mAdapter.notifyDataSetChanged();
        sumPage = (sensorLogJson.getTotal() + 15 - 1) / 15;
        if (pageIndex <= sumPage) {
            pageIndex++;
        }
        mAdapter.loadMoreComplete();
        VHLayout.finishRefresh();
    }

    @Override
    public void onGetTiltSensorLogFail(String msg) {
        CommonDialog.newInstanceSingle(getActivity())
                .setTitle("提示")
                .setMsg("此设备暂无倾角数据！")
                .setSingleBtnText("确定")
                .setSingleClick(v -> {
//                    getActivity().finish();
                })
                .show();
    }

    public void initData() {
        iniChecks();
        VHLayout.setHeaderListData(getResources().getStringArray(R.array.tiltSensorTitleName));
        mAdapter = new VHAdapter(R.layout.tilt_sensor_item, mDataModels);
        VHLayout.setAdapter(mAdapter);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(() -> VHLayout.getRecyclerView().postDelayed(() -> {
            if (pageIndex > sumPage) {
                showToast("已经是最后一页了");
                mAdapter.loadMoreEnd();
            } else {
                mPresenter.getTiltSensorLog(mCamId, paraID, pageIndex, 15, tiltSensorTimeStartTv.getText().toString(), tiltSensorTimeEndTv.getText().toString());
            }

        }, 1000), VHLayout.getRecyclerView());
        VHLayout.setRefresh(() -> {
            pageIndex = 1;
            mDataModels.clear();
            mPresenter.getTiltSensorLog(mCamId, paraID, pageIndex, 15, tiltSensorTimeStartTv.getText().toString(), tiltSensorTimeEndTv.getText().toString());
        });
        mPresenter.getTiltSensorLog(mCamId, paraID, pageIndex, 15, tiltSensorTimeStartTv.getText().toString(), tiltSensorTimeEndTv.getText().toString());
        List<String> dataList = new ArrayList<String>();
        dataList.add("全部");
        for (TiltSensorParaJson.ListBean jsonBean : mParaIds) {
            dataList.add(jsonBean.getParaName());
        }
        mSpAdapter = new CommonAdapter<String>(getActivity(), R.layout.item_common) {
            @Override
            public void onUpdate(BaseAdapterHelper helper, String item, int position) {
                helper.setText(R.id.itemCommonTv, item);
            }
        };
        mSpAdapter.addAll(dataList);
        tiltSensorTypeSp.setAdapter(mSpAdapter);
        tiltSensorTypeSp.setSelection(0);
    }

    public void initSpinner() {
        tiltSensorTypeSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    paraID = "";
                } else {
                    paraID = mParaIds.get(position - 1).getParaID() + "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    @OnClick({R.id.tiltSensorTimeStartTv, R.id.tiltSensorTimeEndTv, R.id.btCruiseDataSearch, R.id.btSelectItem})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiltSensorTimeStartTv:
                TimePickerUtils.showPickerView(getActivity(), "", tiltSensorTimeStartTv, tiltSensorTimeStartTv.getText().toString(), "1234-10-11", tiltSensorTimeEndTv.getText().toString());
                break;
            case R.id.tiltSensorTimeEndTv:
                if (TextUtils.isEmpty(tiltSensorTimeStartTv.getText().toString()))
                    TimePickerUtils.showPickerView(getActivity(), "", tiltSensorTimeEndTv, tiltSensorTimeEndTv.getText().toString(), "1234-10-11", "");
                else
                   TimePickerUtils.showPickerView(getActivity(), "", tiltSensorTimeEndTv, tiltSensorTimeEndTv.getText().toString(), tiltSensorTimeStartTv.getText().toString(), "");
                break;
            case R.id.btCruiseDataSearch:
                pageIndex = 1;
                mDataModels.clear();
                getHideCol();
                mPresenter.getTiltSensorLog(mCamId, paraID, pageIndex, 15, tiltSensorTimeStartTv.getText().toString(), tiltSensorTimeEndTv.getText().toString());
                break;
            case R.id.btSelectItem:
                showPopupWindow();
                break;
        }
    }

    private void showPopupWindow() {
        //backgroundAlpha(0.5f);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.select_item_pop, null);
        mPopWindow = new PopupWindow(contentView);
        mPopWindow.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.FILL_PARENT);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.select_Item_RecyclerView);
        LinearLayout linearLayout = (LinearLayout) contentView.findViewById(R.id.select_Item_Pop_Linearlayout);
        Button select_Item_Btn = (Button) contentView.findViewById(R.id.select_Item_Btn);

        linearLayout.setAlpha(0.5f);
        SelectItemAdapter mAdapter = new SelectItemAdapter(R.layout.select_item_samll_pop, getSelectItemData());
        select_Item_Btn.setOnClickListener(v -> {
            Log.e("111", mAdapter.getChecked().toString() + "");
            isCheckedList.clear();
            isCheckedList.addAll(mAdapter.getChecked());
            mPopWindow.dismiss();
            getHideCol();
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(mAdapter);

        if (Build.VERSION.SDK_INT < 24) {
            mPopWindow.showAsDropDown(tiltSensorAbleLine);
        } else {
            int[] location = new int[2];
            btSelectItem.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (Build.VERSION.SDK_INT == 25) {
                WindowManager wm = (WindowManager) mPopWindow.getContentView().getContext().getSystemService(Context.WINDOW_SERVICE);
                int screenHeight = wm.getDefaultDisplay().getHeight();
                mPopWindow.setHeight(screenHeight - location[1] - tiltSensorAbleLine.getHeight());
            }
            mPopWindow.showAtLocation(tiltSensorAbleLine, Gravity.NO_GRAVITY, 0, y + tiltSensorAbleLine.getHeight());
        }

        mPopWindow.setOnDismissListener(() -> backgroundAlpha(1f));

    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    public void iniChecks() {
        for (int i = 0; i < 4; i++) {
            isCheckedList.add(true);
        }
    }

    public void getHideCol() {
        for (int i = 0; i < isCheckedList.size(); i++) {
            switch (i) {
                case 0:
                    //X角、Y角、激光距离,显示123
                    mTitleVisibility.put(2, isCheckedList.get(i));
                    mTitleVisibility.put(3, isCheckedList.get(i));
                    mTitleVisibility.put(4, isCheckedList.get(i));
                    break;
                case 1:
                    //当次角度差、阶段角度差、累计角度差,显示468
                    mTitleVisibility.put(5, isCheckedList.get(i));
                    mTitleVisibility.put(7, isCheckedList.get(i));
                    mTitleVisibility.put(9, isCheckedList.get(i));
                    break;
                case 2:
                    //当次沉降+坐标位移、阶段沉降+坐标位移、累计沉降+坐标位移,显示579
                    mTitleVisibility.put(6, isCheckedList.get(i));
                    mTitleVisibility.put(8, isCheckedList.get(i));
                    mTitleVisibility.put(10, isCheckedList.get(i));
                    break;
                case 3:
                    //平行度浮动,显示10,11
                    mTitleVisibility.put(11, isCheckedList.get(i));
                    mTitleVisibility.put(12, isCheckedList.get(i));
                    break;
            }
            VHLayout.setTitleVisibility(mTitleVisibility);
        }

    }

    public ArrayList<SelecItemJson> getSelectItemData() {
        Log.e("111", isCheckedList.toString());
        ArrayList<SelecItemJson> selecItemJsons = new ArrayList<SelecItemJson>();
        selecItemJsons.add(new SelecItemJson("设备原始数据", isCheckedList.get(0)));
        selecItemJsons.add(new SelecItemJson("角度差数据", isCheckedList.get(1)));
        selecItemJsons.add(new SelecItemJson("沉降位移数据", isCheckedList.get(2)));
        selecItemJsons.add(new SelecItemJson("平行度浮动", isCheckedList.get(3)));
        return selecItemJsons;
    }
}
