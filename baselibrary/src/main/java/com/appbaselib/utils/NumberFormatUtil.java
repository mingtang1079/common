package com.appbaselib.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Description: 数字格式化工具
 * Created by lbw on 2017/7/25 0025.
 */

public class NumberFormatUtil {

    /**
     * @param d
     * @param fractionDigits 小数点个数
     * @return
     */
    public static String formatDouble(double d, int fractionDigits) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(fractionDigits);
        nf.setMinimumFractionDigits(fractionDigits);
        nf.setRoundingMode(RoundingMode.HALF_UP);

        return nf.format(d);
    }

    public static String formatFloat(float d, int fractionDigits) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(fractionDigits);
        nf.setMinimumFractionDigits(fractionDigits);
        nf.setRoundingMode(RoundingMode.HALF_UP);

        return nf.format(d);
    }
}
