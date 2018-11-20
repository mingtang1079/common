package com.appbaselib.network;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.appbaselib.app.BaseApplication;
import com.appbaselib.utils.NetWorkUtils;
import com.appbaselib.utils.ToastUtils;
import com.google.gson.JsonSyntaxException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 通用订阅者,用于统一处理回调
 */
public abstract class MySubscriber<T> implements Observer<T> {

    private Context mContext;
    private Disposable mDisposable;
    private ProgressDialog mProgressDialog;
    private String title;
    private String message;

    protected boolean showDialog() {
        return true;
    }

    /**
     * @param context  context
     * @param mMessage dialog message
     */
    public MySubscriber(Context context, String ttile, String mMessage) {
        this.message = mMessage;
        this.mContext = context;
        this.title = ttile;
    }


    public MySubscriber(Context context, String mMessage) {
        this(context, null, mMessage);

    }

    /**
     * @param context context 加入dialog
     */
    public MySubscriber(Context context) {
        this(context, "请稍后……");
    }

    public MySubscriber() {

    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onSubscribe(Disposable b) {
        mDisposable = b;

        if (mContext != null) {
            mProgressDialog = new ProgressDialog(mContext);

            if (!TextUtils.isEmpty(title))
                mProgressDialog.setTitle(title);
            if (!TextUtils.isEmpty(message))
                mProgressDialog.setMessage(message);

            mProgressDialog.setCancelable(true);
            mProgressDialog.setCanceledOnTouchOutside(false);
            //点击取消的时候取消订阅  也就是取消网络请求
            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mDisposable.dispose();
                }
            });
            mProgressDialog.show();
        }
    }

    @Override
    public void onNext(T mBaseModel) {
        if (mContext != null)
            mProgressDialog.dismiss();

            onSucess(mBaseModel);

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (mContext != null)
            mProgressDialog.dismiss();

        if (!NetWorkUtils.isNetworkConnected(BaseApplication.Companion.getMInstance())) { //判断网络
            onFail("网络不可用");
        } else if (e instanceof RuntimeException) {
            onFail(e.getMessage());
        } else if (e instanceof JsonSyntaxException) {  //其余不知名错误
            onFail("数据解析异常");
        } else if (e instanceof NullPointerException) {
            onFail(e.getMessage());
        } else {
            onFail("请求失败，请稍后再试...");
        }

    }

    protected abstract void onSucess(T t);

    //默认实现
    protected void onFail(String message) {
        ToastUtils.showShort(BaseApplication.Companion.getMInstance(), message);
    }


}
