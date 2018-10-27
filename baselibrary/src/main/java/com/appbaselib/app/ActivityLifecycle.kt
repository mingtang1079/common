package com.appbaselib.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentManager

/*
 */
class ActivityLifecycle(private val mApplication: Application) : Application.ActivityLifecycleCallbacks {

    private val mAppManager: AppManager

    private val mFragmentLifecycles: List<FragmentManager.FragmentLifecycleCallbacks>? = null

    init {
        mAppManager = AppManager.getInstance()
        mAppManager.setApplication(mApplication)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        //如果intent包含了此字段,并且为true说明不加入到list
        // 默认为false,如果不需要管理(比如不需要在退出所有activity(killAll)时，退出此activity就在intent加此字段为true)
        var isNotAdd = false
        if (activity.intent != null) {
            isNotAdd = activity.intent.getBooleanExtra(AppManager.IS_NOT_ADD_ACTIVITY_LIST, false)
        }
        if (!isNotAdd) {
            mAppManager.addActivity(activity)
        }

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityResumed(activity: Activity) {
        mAppManager.currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {
        if (mAppManager.currentActivity === activity) {
            mAppManager.currentActivity = null
        }
    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        mAppManager.removeActivity(activity)

    }

}
