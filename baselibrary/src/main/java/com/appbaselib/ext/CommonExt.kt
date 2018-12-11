package com.appbaselib.ext


import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.view.WindowManager
import android.widget.Toast
import com.appbaselib.app.BaseApplication
import com.google.gson.Gson
import java.io.Serializable


/** toast相关 **/
fun Any.toast(msg: CharSequence) {
    Toast.makeText(BaseApplication.mInstance, msg, Toast.LENGTH_SHORT).show()
}
fun Any.longToast(msg: CharSequence) {
    Toast.makeText(BaseApplication.mInstance, msg, Toast.LENGTH_LONG).show()
}
/** json相关 **/
fun Any.toJson() = Gson().toJson(this)
