package com.appbaselib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Description: 应用信息提取工具
 * Created by lbw on 2017/4/18 0018.
 */

public class PackageUtil {

    /**
     * 返回当前程序版本代码,如:1
     *
     * @param context
     * @return 当前程序版本代码
     */
    public static int getAppVersionCode(Context context) {
        int versionCode = -1;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;

        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionCode;
    }

    /**
     * 返回当前程序版本名,如:1.0.1
     *
     * @param context
     * @return 当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;

        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * @param context
     * @return 应用包名
     */
    public static String getAppPackageName(Context context) {
        String packageName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            packageName = pi.packageName;

        } catch (Exception e) {
            Log.e("packageName", "Exception", e);
        }
        return packageName;
    }
}
