package com.appbaselib.base.mvp;


import android.content.Context;

import org.greenrobot.eventbus.EventBus;


/*
 * Description: Presenter基类，主要只做了注册EventBus，注册rx订阅监听，还有销毁时把对象置null
 * 注意如要要注册rx监听，子类必须重写addSubscribe方法，并且把rx的逻辑放在该方法体内
 * Created by Sum41forever on 2017/8/28
 * 修改备注:
 */
public class BasePresenter<V extends MvpView> implements IPresenter {
    protected final String TAG = this.getClass().getSimpleName();


    protected V mRootView;

    public BasePresenter(V rootView) {
        this.mRootView = rootView;
        onStart();
    }


    public BasePresenter() {
        onStart();
    }


    @Override
    public void onStart() {
        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().register(this);//注册eventbus
    }

    @Override
    public void onDestroy() {
        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().unregister(this);//解除注册eventbus
        unSubscribe();//解除订阅

        this.mRootView = null;
    }

    /**
     * 是否使用eventBus,默认为使用(false)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }

    //将所有subscription放入,集中处理
    protected void addSubscribe( ) {

    }

    //保证activity结束时取消所有正在执行的订阅
    protected void unSubscribe() {

    }


}

