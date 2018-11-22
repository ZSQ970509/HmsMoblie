package com.hc.hmsmoblie.utils;

import android.support.annotation.DrawableRes;

import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.bean.type.LevelType;

import io.reactivex.Single;

/**
 * Created by Administrator on 2018/11/9.
 */

public class BatAndSignalUtil {
    public static String changeBatPercentage(double bat) {
        if (bat > 11.7) {
            return "100%";
        } else if (bat > 11.2) {
            return "75%";
        } else if (bat > 10.8) {
            return "50%";
        } else if (bat > 10.5) {
            return "25%";
        } else if (bat > 9.7) {
            return "10%";
        }
        return "5%";
    }


    public static String changeSignalPercentage(int Signal) {
        if (Signal >= -51) {
            return "96%";
        } else if (Signal >= -53) {
            return "93%";
        } else if (Signal >= -55) {
            return "90%";
        } else if (Signal >= -57) {
            return "87%";
        } else if (Signal >= -59) {
            return "84%";
        } else if (Signal >= -61) {
            return "81%";
        } else if (Signal >= -63) {
            return "78%";
        } else if (Signal >= -65) {
            return "75%";
        } else if (Signal >= -67) {
            return "72%";
        } else if (Signal >= -69) {
            return "69%";
        } else if (Signal >= -71) {
            return "66%";
        } else if (Signal >= -73) {
            return "63%";
        } else if (Signal >= -75) {
            return "60%";
        } else if (Signal >= -77) {
            return "57%";
        } else if (Signal >= -79) {
            return "54%";
        } else if (Signal >= -81) {
            return "51%";
        } else if (Signal >= -83) {
            return "48%";
        } else if (Signal >= -85) {
            return "45%";
        } else if (Signal >= -87) {
            return "42%";
        } else if (Signal >= -89) {
            return "39%";
        } else if (Signal >= -91) {
            return "36%";
        } else if (Signal >= -93) {
            return "33%";
        } else if (Signal >= -95) {
            return "30%";
        } else if (Signal >= -97) {
            return "27%";
        } else if (Signal >= -99) {
            return "24%";
        } else if (Signal >= -101) {
            return "21%";
        } else if (Signal >= -103) {
            return "18%";
        } else if (Signal >= -105) {
            return "15%";
        } else if (Signal >= -107) {
            return "12%";
        } else if (Signal >= -109) {
            return "9%";
        } else if (Signal >= -111) {
            return "6%";
        } else if (Signal >= -113) {
            return "3%";
        } else if (Signal == 0) {
            return "0%";
        }
        return "0%";
    }

    /**
     * 根据bat返回显示图片的Rid
     *
     * @param bat
     * @return
     */
    @DrawableRes
    public static int batLevel(double bat) {
//        return R.drawable.logo2;
        if (bat > 117) {
            return R.drawable.ic_battery4;
        } else if (bat > 112) {
            return R.drawable.ic_battery3;
        } else if (bat > 108) {
            return R.drawable.ic_battery2;
        } else if (bat > 105) {
            return R.drawable.ic_battery1;
        } else {
            return R.drawable.ic_battery0;
        }
    }

    /**
     * 根据信号强度signal返回显示图片的Rid
     *
     * @param signal
     * @return
     */
    @DrawableRes
    public static int signalLevel(double signal) {
//        return R.drawable.logo2;
        if (signal >= -61) {
            return R.drawable.ic_signl4;
        } else if (signal >= -75) {
            return R.drawable.ic_signl3;
        } else if (signal >= -87) {
            return R.drawable.ic_signl2;
        } else if (signal >= -101) {
            return R.drawable.ic_signl1;
        } else {
            return R.drawable.ic_signl0;
        }
    }
}
