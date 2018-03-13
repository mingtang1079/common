package com.appbaselib.network;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.Toast;

import com.appbaselib.app.BaseApplication;
import com.appbaselib.rx.ServerException;
import com.appbaselib.utils.NetWorkUtils;
import com.appbaselib.utils.ToastUtils;
import com.google.gson.JsonSyntaxException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 通用订阅者,用于统一处理回调
 */
public abstract class DefaultSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    //  private SweetAlertDialog dialog;
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
    public DefaultSubscriber(Context context, String ttile, String mMessage) {
        this.message = mMessage;
        this.mContext = context;
        this.title = ttile;
    }


    public DefaultSubscriber(Context context, String mMessage) {
        this(context, null, mMessage);

    }


    /**
     * @param context context 加入dialog
     */
    public DefaultSubscriber(Context context) {
        this(context, "请稍后……");
    }

    public DefaultSubscriber() {

    }


    @Override
    public void onCompleted() {
        //  if (showIsLuruDialog())
        //    dialog.dismiss();
    }

    @Override
    public void onStart() {
        super.onStart();
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
                    if (!isUnsubscribed()) {
                        unsubscribe();
                    }
                }
            });
            mProgressDialog.show();
        }


    }

    @Override
    public void onNext(T t) {
        if (mContext != null)
            mProgressDialog.dismiss();


        _onNext(t);


    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (!NetWorkUtils.isNetworkConnected(BaseApplication.mInstance)) { //判断网络
            _onError("网络不可用");
        } else if (e instanceof ServerException) {
            _onError(e.getMessage());

            //   处理用户验证信息 不对就重新登录
            if (e.getMessage().contains("认证失败")) {
                //LoginActivity.launch(App.getInstance());
                ToastUtils.showToast(BaseApplication.mInstance, "请重新登录", Toast.LENGTH_SHORT);
//                Context mContext = PDAApplication.MyActivityManager.getInstance().getCurrentActivity();
//                mContext.startActivity(new Intent(mContext, LoginActivity.class));
            }

        } else if (e instanceof JsonSyntaxException) {  //其余不知名错误
            _onError("数据解析异常……");
        } else if (e instanceof HttpException) {
            _onError(((HttpException) e).message() + ":" + ((HttpException) e).code());
        } else if (e instanceof NullPointerException) {
            _onError(e.getMessage());
        } else {
            _onError("请求失败，请稍后再试...");
        }

        if (mContext != null)
            mProgressDialog.dismiss();
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
