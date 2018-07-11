package com.appbaselib.utils;

import android.support.annotation.Nullable;

import com.appbaselib.constant.Constants;

import java.math.BigDecimal;

/**
 * Created by tangming on 2018/3/16.
 */

public class NullUtils {

    public static <T> T checkNotNull(@Nullable T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        } else {
            return value;
        }
    }

    public static <T extends CharSequence> T fillNull(T source) {
        return (T) (android.text.TextUtils.isEmpty(source) ? "" : source.toString());
    }

}
