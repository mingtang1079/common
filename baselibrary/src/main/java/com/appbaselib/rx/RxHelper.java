package com.appbaselib.rx;

import com.appbaselib.app.BaseApplication;
import com.appbaselib.base.BaseModel;
import com.appbaselib.base.BaseModelWrapper;
import com.appbaselib.netstatus.NetUtils;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxHelper {

    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseModel<T>, BaseModel<T>> handleResult() {
        return new ObservableTransformer<BaseModel<T>, BaseModel<T>>() {
            @Override
            public ObservableSource<BaseModel<T>> apply(Observable<BaseModel<T>> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (!NetUtils.isNetworkAvailable(BaseApplication.mInstance)) {
                                    // TODO: 2018/3/14
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                try {
                    subscriber.onNext(data);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }

    /**
     * 线程切换的封装
     *
     * @param <T>
     * @return
     */

    public static <T> ObservableTransformer<BaseModel<T>, BaseModel<T>> rxSchedulerHelper() {
        return new ObservableTransformer<BaseModel<T>, BaseModel<T>>() {
            @Override
            public ObservableSource<BaseModel<T>> apply(Observable<BaseModel<T>> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
