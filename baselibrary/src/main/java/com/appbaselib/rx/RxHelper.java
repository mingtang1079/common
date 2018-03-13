package com.appbaselib.rx;

import com.appbaselib.base.BaseModel;
import com.appbaselib.base.BaseModelWrapper;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxHelper {

    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<BaseModel<T>, T> handleResult() {
        return new Observable.Transformer<BaseModel<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseModel<T>> tObservable) {
                return tObservable.flatMap(new Func1<BaseModel<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseModel<T> result) {

                        if (result.getCode() == 200) {
                            return createData(result.getData());
                        } else {
                            return Observable.error(new ServerException(result.getMsg()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /**
     * 对结果进行二次处理 ，带分页数据
     *
     * @param <T>
     * @return
     */

    public static <T> Observable.Transformer<BaseModel<T>, BaseModelWrapper<T>> handleResult2() {
        return new Observable.Transformer<BaseModel<T>, BaseModelWrapper<T>>() {
            @Override
            public Observable<BaseModelWrapper<T>> call(Observable<BaseModel<T>> tObservable) {
                Observable<BaseModelWrapper<T>> observable = tObservable.flatMap(new Func1<BaseModel<T>, Observable<BaseModelWrapper<T>>>() {
                    @Override
                    public Observable<BaseModelWrapper<T>> call(BaseModel<T> result) {

                        if (result.isSuccess()) {

                            BaseModelWrapper<T> mBaseModelWrapper = new BaseModelWrapper<T>();
                            mBaseModelWrapper.data = result.getData();
                            mBaseModelWrapper.totalCount = result.getTotalCount();
                            mBaseModelWrapper.pageNo = result.getPageNo();
                            mBaseModelWrapper.pageSize = result.getPageSize();
                            mBaseModelWrapper.timestamp = result.getTimestamp();
                            return createData(mBaseModelWrapper);

                        } else {
                            return Observable.error(new ServerException(result.getMsg()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                return observable;
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
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
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

    public static <T> Observable.Transformer<BaseModel<T>, BaseModel<T>> rxSchedulerHelper() {
        return new Observable.Transformer<BaseModel<T>, BaseModel<T>>() {
            @Override
            public Observable<BaseModel<T>> call(Observable<BaseModel<T>> source) {
                return source.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
