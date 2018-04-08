package com.appbaselib.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;

import com.appbaselib.utils.SystemUtils;
import com.pangu.appbaselibrary.BuildConfig;
import com.tencent.bugly.crashreport.CrashReport;

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
        AppManager.getInstance().setApplication(mInstance);//给App管理器设置上下文
        mActivityLifecycle = new ActivityLifecycle(mInstance);
        registerActivityLifecycleCallbacks(mActivityLifecycle);

    }


}
