package com.appbaselib.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.support.annotation.CallSuper
import android.support.v4.app.SupportActivity
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout

/**
 * Created by tangming on 2018/5/3.
 */

open abstract class BaseLifeCycleView : FrameLayout, LifecycleObserver {

    protected abstract val contentViewLayoutID: Int

    constructor(context: Context) : super(context) {
        init(context)

    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)

    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)

    }

    @CallSuper
    fun init(mContext: Context) {
        if (mContext is SupportActivity) {
            val mLifecycleOwner = mContext as LifecycleOwner
            mLifecycleOwner.lifecycle.addObserver(this)
        }
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
   open fun onCreate() {
        val mView = LayoutInflater.from(context).inflate(contentViewLayoutID, this, false)
        addView(mView)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun onStop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun onDestory() {
    }


}
