package com.appbaselib.rx

import android.content.Context
import com.appbaselib.network.MySubscriber
import io.reactivex.Observable

fun <T> Observable<T>.get(context: Context?=null, message: String? =null, title: String? = "", next: (T) -> Unit, err: (mS: String?) -> Unit) {

    this.subscribe(object : MySubscriber<T>(context,message,title) {
        override fun onSucess(t: T) {
            next(t)
        }
        override fun onFail(message: String?) {
            err(message)
        }
    })
}

fun <T> Observable<T>.get(context: Context?=null, message: String? =null, title: String? = null,next: (T) -> Unit) {

    this.subscribe(object : MySubscriber<T>(context,message,title) {
        override fun onSucess(t: T) {
            next(t)
        }

    })

}
