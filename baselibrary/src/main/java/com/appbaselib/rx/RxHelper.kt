package com.appbaselib.rx

import android.arch.lifecycle.LifecycleOwner
import com.appbaselib.base.BaseModel
import com.appbaselib.rxlife.RxLife
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
object RxHelper {

    /**
     * 不对结果进行预处理 (不带生命周期) 返回全部实体
     *
     * @param <T>
     * @return
    </T> */
    fun <T> handleResult2(context: LifecycleOwner): ObservableTransformer<BaseModel<T>, BaseModel<T>> {
        return ObservableTransformer { upstream ->
            upstream
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .compose(RxLife.with(context).bindToLifecycle())

        }
    }

    /**
     * 对结果进行预处理 (不带生命周期)
     *
     * @param <T>
     * @return
    </T> */
    fun <T> handleResult(context: LifecycleOwner): ObservableTransformer<BaseModel<T>, T> {

        return  ObservableTransformer { upstream ->

            upstream.flatMap {

                if (it.code==200)
                {
                    createData(it.data)
                }
                else{
                    Observable.error(ServerException(it.msg))
                }
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                //    .compose(RxLife.with(context).bindToLifecycle())

        }

    }

    /**
     * 线程切换的封装
     *
     * @param <T>
     * @return
    </T> */

    fun <T> rxSchedulerHelper(): ObservableTransformer<BaseModel<T>, BaseModel<T>> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
    </T> */
    private fun <T> createData(data: T): Observable<T> {

        return Observable.create { e ->
            try {
                e.onNext(data)
                e.onComplete()
            } catch (ex: Exception) {
                e.onError(ex)
            }
        }

    }


}
