package com.appbaselib.rx;

import java.net.ConnectException;

/**
 * Created by Administrator on 2016/11/24.
 */

public class ServerException extends RuntimeException {
    //处理某些错误信息（如后台数据不一致导致解析失败得不到 失败信息 ，强行抛出错误）
    public ServerException(String mS) {
        super(mS == null ? "" : mS);  // 防止异常信息返回为null 程序崩溃 （尝试在retrofit中全局设置 string 为 null的时候解析为“”，但失败）
    }
}
