package com.appbaselib.rx

import android.arch.lifecycle.LifecycleOwner
import android.content.Context
import com.appbaselib.app.AppManager
import com.appbaselib.base.BaseModel
import com.appbaselib.network.MySubscriber
import io.reactivex.Observable
import org.jetbrains.annotations.NotNull

//不带绑定生命周期 需要手动调用rxcompose.handleResult 方法
fun <T> Observable<T>.get2(context: Context? = null, message: String? = "请稍候", title: String? = "", next: (T) -> Unit, err: (mS: String?) -> Unit) {

    this.subscribe(object : MySubscriber<T>(context, message, title) {
        override fun onSucess(t: T) {
            next(t)
        }

        override fun onFail(message: String?) {
            err(message)
        }
    })
}

fun <T> Observable<T>.get2(context: Context? = null, message: String? = "请稍候", title: String? = null, next: (T) -> Unit) {

    this.subscribe(object : MySubscriber<T>(context, message, title) {
        override fun onSucess(t: T) {
            next(t)
        }

    })

}
//绑定生命周期 不覆写onFail 默认弹出toast

fun <T> Observable<BaseModel<T>>.get(context: Context=AppManager.getInstance().currentActivity, isShowDialog: Boolean = false, message: String? = "请稍候", title: String? = null, next: (T) -> Unit) {

    this.compose(RxCompose.handleResult<T>(context as LifecycleOwner)).subscribe(object : MySubscriber<T>(if (isShowDialog) context else null, message, title) {
        override fun onSucess(t: T) {
            next(t)
        }

    })

}

//绑定生命周期 复写onFail
fun <T> Observable<BaseModel<T>>.get(context: Context=AppManager.getInstance().currentActivity, isShowDialog: Boolean = false, message: String? = "请稍候", title: String? = "", next: (T) -> Unit, err: (mS: String?) -> Unit) {

    this.compose(RxCompose.handleResult<T>(context  as LifecycleOwner)).subscribe(object : MySubscriber<T>(if (isShowDialog) context else null, message, title) {
        override fun onSucess(t: T) {
            next(t)
        }

        override fun onFail(message: String?) {
            err(message)
        }
    })
}
