package com.appbaselib.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;

import com.appbaselib.utils.CommonUtils;
import com.appbaselib.utils.SystemUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public abstract class BaseApplication extends MultiDexApplication {

    public static BaseApplication mInstance = null;
    protected ActivityLifecycle mActivityLifecycle;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppManager.getInstance().setApplication(this);//给App管理器设置上下文
        /**检测当前进程名称是否为应用包名，否则return （像百度地图等sdk需要在单独的进程中执行，会多次执行Application的onCreate()方法，所以为了只初始化一次应用配置，作此判断）*/
        if (!CommonUtils.getCurProcessName(this).equals(getPackageName())) {
            return;
        }

        mActivityLifecycle = new ActivityLifecycle(this);
        registerActivityLifecycleCallbacks(mActivityLifecycle);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
