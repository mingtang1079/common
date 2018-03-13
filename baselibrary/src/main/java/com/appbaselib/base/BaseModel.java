package com.appbaselib.base;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/7.
 */
public class BaseModel<T> implements Serializable {


    private int code;
    private String msg;
    private T data;
    private String version;
    private int totalCount;
    private int pageSize;
    private int pageNo;
    private long timestamp;
    private boolean success;


    public int getCode() {
        return code;
    }

    public void setCode(int mCode) {
        code = mCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String mMsg) {
        msg = mMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T mData) {
        data = mData;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String mVersion) {
        version = mVersion;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int mTotalCount) {
        totalCount = mTotalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int mPageSize) {
        pageSize = mPageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int mPageNo) {
        pageNo = mPageNo;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long mTimestamp) {
        timestamp = mTimestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean mSuccess) {
        success = mSuccess;
    }
}
