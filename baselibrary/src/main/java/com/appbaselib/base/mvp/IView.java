package com.appbaselib.base.mvp;

import android.content.Intent;

/*
 * Description: 通用的View页面接口，独有的接口请继承该接口扩展
 * Created by Sum41forever on 2017/8/28
 * 修改备注:
 */
public interface IView extends MvpView{

    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String message);

    /**
     * 跳转activity
     */
    void launchActivity(Intent intent);

    /**
     * 杀死自己
     */
    void killMyself();
}
