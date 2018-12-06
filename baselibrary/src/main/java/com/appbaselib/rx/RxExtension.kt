package com.appbaselib.rx

import com.appbaselib.network.MySubscriber
import io.reactivex.Observable

fun <T> Observable<T>.get(next: (T) -> Unit, err: (mS: String) -> Unit) {

    this.subscribe(object : MySubscriber<T>() {
        override fun onSucess(t: T) {
            next(t)
        }

        override fun onFail(message: String) {
            err(message)
        }

    })

}

fun <T> Observable<T>.get(next: (T) -> Unit) {

    this.subscribe(object : MySubscriber<T>() {
        override fun onSucess(t: T) {
            next(t)
        }

    })

}