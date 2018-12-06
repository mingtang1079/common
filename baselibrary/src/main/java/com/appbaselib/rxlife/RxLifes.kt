package com.appbaselib.rxlife;
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

/**
 * 自动绑定生命周期,请查看：[LifecycleTransformer.convertLifecycle]生命周期转化函数。
 */
fun <T> Observable<T>.bindToLifecycle(owner: LifecycleOwner) = compose(RxLife.with(owner).bindToLifecycle())

/**
 * 指定在[event]生命周期事件注销
 */
fun <T> Observable<T>.bindUntilEvent(owner: LifecycleOwner, event: Lifecycle.Event) = compose(RxLife.with(owner).bindUntilEvent(event))

/**
 * 在[Lifecycle.Event.ON_DESTROY],即界面销毁的时候注销
 */
fun <T> Observable<T>.bindonDestroy(owner: LifecycleOwner) = compose(RxLife.with(owner).bindOnDestroy())


//------------------------------------

/**
 * 自动绑定生命周期,请查看：[LifecycleTransformer.convertLifecycle]生命周期转化函数。
 */
fun <T> Flowable<T>.bindToLifecycle(owner: LifecycleOwner) = compose(RxLife.with(owner).bindToLifecycle())

/**
 * 指定在[event]生命周期事件注销
 */
fun <T> Flowable<T>.bindUntilEvent(owner: LifecycleOwner, event: Lifecycle.Event) = compose(RxLife.with(owner).bindUntilEvent(event))

/**
 * 在[Lifecycle.Event.ON_DESTROY],即界面销毁的时候注销
 */
fun <T> Flowable<T>.bindonDestroy(owner: LifecycleOwner) = compose(RxLife.with(owner).bindOnDestroy())


//------------------------------------

/**
 * 自动绑定生命周期,请查看：[LifecycleTransformer.convertLifecycle]生命周期转化函数。
 */
fun <T> Maybe<T>.bindToLifecycle(owner: LifecycleOwner) = compose(RxLife.with(owner).bindToLifecycle())

/**
 * 指定在[event]生命周期事件注销
 */
fun <T> Maybe<T>.bindUntilEvent(owner: LifecycleOwner, event: Lifecycle.Event) = compose(RxLife.with(owner).bindUntilEvent(event))

/**
 * 在[Lifecycle.Event.ON_DESTROY],即界面销毁的时候注销
 */
fun <T> Maybe<T>.bindonDestroy(owner: LifecycleOwner) = compose(RxLife.with(owner).bindOnDestroy())


//------------------------------------

/**
 * 自动绑定生命周期,请查看：[LifecycleTransformer.convertLifecycle]生命周期转化函数。
 */
fun <T> Single<T>.bindToLifecycle(owner: LifecycleOwner) = compose(RxLife.with(owner).bindToLifecycle())

/**
 * 指定在[event]生命周期事件注销
 */
fun <T> Single<T>.bindUntilEvent(owner: LifecycleOwner, event: Lifecycle.Event) = compose(RxLife.with(owner).bindUntilEvent(event))

/**
 * 在[Lifecycle.Event.ON_DESTROY],即界面销毁的时候注销
 */
fun <T> Single<T>.bindonDestroy(owner: LifecycleOwner) = compose(RxLife.with(owner).bindOnDestroy())

