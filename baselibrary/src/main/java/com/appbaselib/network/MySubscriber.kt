package com.appbaselib.network


import android.app.ProgressDialog
import android.content.Context
import android.text.TextUtils

import com.appbaselib.app.BaseApplication
import com.appbaselib.utils.NetWorkUtils
import com.appbaselib.utils.ToastUtils
import com.google.gson.JsonSyntaxException

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * 通用订阅者,用于统一处理回调
 */
abstract class MySubscriber<T>(context: Context? = null, mMessage: String? = "请稍后……", title: String? = null) : Observer<T> {

    private var mContext: Context? = null
    private var mDisposable: Disposable? = null
    private var mProgressDialog: ProgressDialog? = null
    private var title: String? = null
    private var message: String? = null

    /**
     * @param context  context
     * @param mMessage dialog message
     */
    init {
        this.mContext = context
        this.message = mMessage
        this.title = title
    }

    override fun onComplete() {}

    override fun onSubscribe(b: Disposable) {
        mDisposable = b

        if (mContext != null) {
            mProgressDialog = ProgressDialog(mContext)

            if (!TextUtils.isEmpty(title))
                mProgressDialog!!.setTitle(title)
            if (!TextUtils.isEmpty(message))
                mProgressDialog!!.setMessage(message)

            mProgressDialog!!.setCancelable(true)
            mProgressDialog!!.setCanceledOnTouchOutside(false)
            //点击取消的时候取消订阅  也就是取消网络请求
            mProgressDialog!!.setOnCancelListener { mDisposable!!.dispose() }
            mProgressDialog!!.show()
        }
    }

    override fun onNext(mBaseModel: T) {
        if (mContext != null)
            mProgressDialog!!.dismiss()

        onSucess(mBaseModel)

    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        if (mContext != null)
            mProgressDialog!!.dismiss()

        if (!NetWorkUtils.isNetworkConnected(BaseApplication.mInstance!!)) { //判断网络
            onFail("网络不可用")
        } else if (e is RuntimeException) {
            onFail(e.message)
        } else if (e is JsonSyntaxException) {  //其余不知名错误
            onFail("数据解析异常")
        } else if (e is NullPointerException) {
            onFail(e.message)
        } else {
            onFail("请求失败，请稍后再试...")
        }

    }

    protected abstract fun onSucess(t: T)

    //默认实现
    protected open fun onFail(message: String?) {
        ToastUtils.showShort(BaseApplication.mInstance, message)
    }


}
/**
 * @param context context 加入dialog
 */
