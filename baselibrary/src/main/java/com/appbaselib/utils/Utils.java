package com.appbaselib.utils;

import android.support.annotation.Nullable;

/**
 * Created by tangming on 2018/3/16.
 */

public class Utils {

    public static <T> T checkNotNull(@Nullable T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        } else {
            return value;
        }
    }
}
