package com.appbaselib.common.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Description: 忽略被ExcAnnotation注解的字段
 * Created by lbw on 2017/6/23 0023.
 */

public class FooAnnotationExclusionStrategy implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.getAnnotation(GsonExclusionAnnotation.class) != null;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(GsonExclusionAnnotation.class) != null;
    }
}
