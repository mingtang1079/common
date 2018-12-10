package com.appbaselib.rx

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.appbaselib.base.BaseModel
import com.appbaselib.network.MySubscriber
import io.reactivex.Observable

fun <T> Observable<T>.get(context: Context? = null, message: String? = "请稍候", title: String? = "", next: (T) -> Unit, err: (mS: String?) -> Unit) {

    this.subscribe(object : MySubscriber<T>(context, message, title) {
        override fun onSucess(t: T) {
            next(t)
        }

        override fun onFail(message: String?) {
            err(message)
        }
    })
}

fun <T> Observable<T>.get(context: Context? = null, message: String? = "请稍候", title: String? = null, next: (T) -> Unit) {

    this.subscribe(object : MySubscriber<T>(context, message, title) {
        override fun onSucess(t: T) {
            next(t)
        }

    })

}

fun <T> Observable<BaseModel<T>>.get2(context: Context, isShowDialog: Boolean = false, message: String? = "请稍候", title: String? = null, next: (T) -> Unit) {

    this.compose(RxCompose.handleResult<T>(context as LifecycleOwner)).subscribe(object : MySubscriber<T>(if (isShowDialog) context else null, message, title) {
        override fun onSucess(t: T) {
            next(t)
        }

    })

}

fun <T> Observable<BaseModel<T>>.get2(context: Context, isShowDialog: Boolean = false, message: String? = "请稍候", title: String? = "", next: (T) -> Unit, err: (mS: String?) -> Unit) {

    this.compose(RxCompose.handleResult<T>(context as LifecycleOwner)).subscribe(object : MySubscriber<T>(if (isShowDialog) context else null, message, title) {
        override fun onSucess(t: T) {
            next(t)
        }

        override fun onFail(message: String?) {
            err(message)
        }
    })
}
