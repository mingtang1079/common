package com.appbaselib.rx;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;

import com.appbaselib.app.BaseApplication;
import com.appbaselib.base.BaseModel;
import com.appbaselib.base.BaseModelWrapper;
import com.appbaselib.netstatus.NetUtils;
import com.appbaselib.utils.Utils;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.CompletableScoper;
import com.uber.autodispose.CompletableSubscribeProxy;
import com.uber.autodispose.FlowableScoper;
import com.uber.autodispose.FlowableSubscribeProxy;
import com.uber.autodispose.MaybeScoper;
import com.uber.autodispose.MaybeSubscribeProxy;
import com.uber.autodispose.ObservableScoper;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.uber.autodispose.ParallelFlowableSubscribeProxy;
import com.uber.autodispose.SingleScoper;
import com.uber.autodispose.SingleSubscribeProxy;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import org.reactivestreams.Subscriber;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableConverter;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.schedulers.Schedulers;
import static com.uber.autodispose.ScopeUtil.deferredResolvedLifecycle;

public class RxHelper {

    /**
     * 对结果进行预处理 (不带生命周期) 返回全部实体
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
     * 对结果进行预处理 (带生命周期)
     *
     * @param <T>
     * @return
     */
    public static <T> AutoDisposeConverter<BaseModel<T>> handleResult(Context mContext) {

        Maybe scope = deferredResolvedLifecycle(Utils.checkNotNull(AndroidLifecycleScopeProvider.from((LifecycleOwner) mContext), "provider == null"));
        Utils.checkNotNull(scope, "scope == null");

        return new AutoDisposeConverter<BaseModel<T>>() {
            @Override
            public ParallelFlowableSubscribeProxy<BaseModel<T>> apply(ParallelFlowable<BaseModel<T>> upstream) {
                return null;
            }

            @Override
            public CompletableSubscribeProxy apply(Completable upstream) {
                return upstream.to(new CompletableScoper(scope));
            }

            @Override
            public FlowableSubscribeProxy<BaseModel<T>> apply(Flowable<BaseModel<T>> upstream) {
                return upstream.to(new FlowableScoper<BaseModel<T>>(scope));
            }

            @Override
            public MaybeSubscribeProxy<BaseModel<T>> apply(Maybe<BaseModel<T>> upstream) {
                return upstream.to(new MaybeScoper<BaseModel<T>>(scope));
            }

            @Override
            public ObservableSubscribeProxy<BaseModel<T>> apply(Observable<BaseModel<T>> upstream) {
                return upstream
                        .compose(handleResult())//加入线程切换
                        .to(new ObservableScoper<BaseModel<T>>(scope));
            }

            @Override
            public SingleSubscribeProxy<BaseModel<T>> apply(Single<BaseModel<T>> upstream) {
                return upstream.to(new SingleScoper<BaseModel<T>>(scope));
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
