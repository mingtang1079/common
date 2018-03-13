package com.appbaselib.rx;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 当返回失败信息的时候，很有可能 信息T 的数据结构和成功状态不一样 导致 直接走了rxjava  的onerror回调，得不到失败的信息(就有些傻吊后台会这么玩)
 * Created by tamgming on 2017/10/23.
 */

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {


    private final Gson gson;
    private final Type type;


    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {

        String response = value.string();
        //先将返回的json数据解析到Response中，如果code==200，则解析到我们的实体基类中，否则抛异常
        ErrorResponse httpResult = gson.fromJson(response, ErrorResponse.class);
        if (httpResult.code == 200) {
            //200的时候就直接解析，不可能出现解析异常。因为我们实体基类中传入的泛型，就是数据成功时候的格式
            return gson.fromJson(response, type);
        } else {
            ErrorResponse errorResponse = gson.fromJson(response, ErrorResponse.class);
            //抛一个自定义ResultException 传入失败时候信息
            throw new ServerException(errorResponse.msg);

        }
    }

}


