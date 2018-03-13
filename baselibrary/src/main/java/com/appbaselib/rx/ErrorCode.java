package com.appbaselib.rx;

/**
 * Created by Administrator on 2016/11/8.
 */
public class ErrorCode extends RuntimeException {
    public ErrorCode(String detailMessage) {
        super(detailMessage);
    }
}