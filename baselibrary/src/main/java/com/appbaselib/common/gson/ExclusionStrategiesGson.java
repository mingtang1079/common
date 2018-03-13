package com.appbaselib.common.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Description: 获取忽略注解的字段
 * Created by lbw on 2017/7/19 0019.   alter bt  tangming 加入null的问题
 */

public class ExclusionStrategiesGson {

    public static Gson getExclusionStrategiesGson() {
        Gson gson = new GsonBuilder()
                .serializeNulls()//序列化为null对象
                .setDateFormat("yyyy-MM-dd HH:mm:ss") //设置日期的格式
                .disableHtmlEscaping()//防止对网址乱码 忽略对特殊字符的转换
                .setExclusionStrategies(new FooAnnotationExclusionStrategy())
                .create();
        return gson;
    }
}
