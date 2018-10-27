package com.appbaselib.base

import java.io.Serializable

/**
 * Created by Administrator on 2016/11/7.
 */
data class BaseModel<T>(var code: Int, var msg: String? = null, var data: T?, var status: Boolean) : Serializable {


}
