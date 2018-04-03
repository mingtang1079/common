package com.appbaselib.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/7.
 */
public class BaseModel<T> implements Serializable {

    public int code;
    public String msg;
    public T data;
    public boolean status;

}
