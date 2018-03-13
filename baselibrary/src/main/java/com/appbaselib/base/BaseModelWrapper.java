package com.appbaselib.base;

import java.io.Serializable;

/**
 * Created by tangming on 2017/6/2. 对分页数据的二次包裹
 */

public class BaseModelWrapper<T> implements Serializable {

    public T data;
    public int pageNo;
    public int pageSize;
    public int totalCount;
    public Long timestamp;

}
