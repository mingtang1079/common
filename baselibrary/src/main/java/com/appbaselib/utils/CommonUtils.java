package com.appbaselib.utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by tangming on 2018/5/15.
 */

public class CommonUtils {

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
