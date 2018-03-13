package com.appbaselib.utils;

import android.util.Log;

import com.pangu.appbaselibrary.BuildConfig;

/**
 * @author hht  alter  by  tanmgin
 * @Description: TODO
 * @date 2016/12/26 0026
 */
public class LogUtils {


    private LogUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isDebug = BuildConfig.DEBUG;

    private static final String TAG = "App---->";

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void i(int msg) {
        if (isDebug)
            Log.i(TAG, String.valueOf(msg));
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数

    public static void e(String e, String msg) {
        if (isDebug)
            Log.e(e, msg);
    }

    public static void d(String e, String msg) {
        if (isDebug)
            Log.d(e, msg);
    }

    public static void i(String e, String msg) {
        if (isDebug)
            Log.i(e, msg);
    }

    public static void v(String e, String msg) {
        if (isDebug)
            Log.v(e, msg);
    }

    public static void w(String e, String msg) {
        if (isDebug)
            Log.w(e, msg);
    }
}
