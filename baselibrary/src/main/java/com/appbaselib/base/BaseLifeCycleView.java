package com.appbaselib.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.SupportActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

/**
 * Created by tangming on 2018/5/3.
 */

public abstract class BaseLifeCycleView extends FrameLayout implements LifecycleObserver {

    public BaseLifeCycleView(Context context) {
        super(context);
        init(context);

    }

    public BaseLifeCycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public BaseLifeCycleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    @CallSuper
    public void init(Context mContext) {
        if (mContext instanceof SupportActivity) {
            LifecycleOwner mLifecycleOwnerm = (LifecycleOwner) mContext;
            mLifecycleOwnerm.getLifecycle().addObserver(this);
        }
    }

    protected abstract int getContentViewLayoutID();

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        View mView = LayoutInflater.from(getContext()).inflate(getContentViewLayoutID(), this, false);
        ButterKnife.bind(this, mView);
        addView(mView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
    }


}
