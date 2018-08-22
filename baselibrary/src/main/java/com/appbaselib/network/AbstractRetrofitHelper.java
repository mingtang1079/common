package com.appbaselib.network;

import android.util.Log;

import com.appbaselib.common.gson.ExclusionStrategiesGson;
import com.appbaselib.rx.GsonConverterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by CLOUD on 2016/10/14.
 */

public abstract class AbstractRetrofitHelper<T> {

    protected static AbstractRetrofitHelper mInstance;
    private T mApi;
    private OkHttpClient okHttpClient = null;
    private Retrofit retrofit;

    private void init() {
        initOkHttp();
        initRetrofit();
    }

    protected AbstractRetrofitHelper() {
        mInstance = this;
        init();
    }

    protected T getApi() {
        return mApi;
    }

    protected abstract String getHost();

    protected abstract Class<T> getAPI();

    public void initRetrofit() {
        retrofit = newRetrofitInstance(getHost());
        mApi = retrofit.create(getAPI());
    }

    /**
     * @param specifyHost
     * @return 指定host的实例
     */
    public Retrofit newRetrofitInstance(String specifyHost) {
        return new Retrofit.Builder()
                .baseUrl(specifyHost)
                .client(okHttpClient)
                //   .addConverterFactory(GsonConverterFactory.create(ExclusionStrategiesGson.getExclusionStrategiesGson()))
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private void initOkHttp() {
        OkHttpClient.Builder clientBuilder;
        clientBuilder = new OkHttpClient.Builder();
        List<Interceptor> interceptors = getCustomInterceptors();
        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                clientBuilder.addInterceptor(interceptor);
            }
        }
        List<Interceptor> interceptors1 = getCustomNetworkInterceptors();
        if (interceptors1 != null) {
            for (Interceptor interceptor : interceptors1) {
                clientBuilder.addInterceptor(interceptor);
            }
        }
        clientBuilder.connectTimeout(10 * 10000L, TimeUnit.MILLISECONDS);
        clientBuilder.readTimeout(10 * 10000L, TimeUnit.MILLISECONDS);
        clientBuilder.retryOnConnectionFailure(true);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("HttpReponse:", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);
        clientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        okHttpClient = clientBuilder.build();
    }

    protected abstract List<Interceptor> getCustomNetworkInterceptors();

    protected abstract List<Interceptor> getCustomInterceptors();

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }


}
