package com.safframework.ext

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 */

/**
 * screen width in pixels
 */
inline val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

/**
 * screen height in pixels
 */
inline val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

inline val Context.isNetworkAvailable: Boolean
    @SuppressLint("MissingPermission")
    get() {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo?.isConnectedOrConnecting ?: false
    }

/**
 * returns dip(dp) dimension value in pixels
 * @param value dp
 */
fun Context.dp2px(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun Context.dp2px(value: Float): Int = (value * resources.displayMetrics.density).toInt()

/**
 * return sp dimension value in pixels
 * @param value sp
 */
fun Context.sp2px(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()

fun Context.sp2px(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

/**
 * converts [px] value into dip or sp
 * @param px
 */
fun Context.px2dp(px: Int): Float = px.toFloat() / resources.displayMetrics.density

fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity

/**
 * return dimen resource value in pixels
 * @param resource dimen resource
 */
fun Context.dimen2px(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)


fun Context.string(@StringRes id: Int): String = getString(id)

fun Context.color(@ColorRes id: Int): Int = resources.getColor(id)

fun Context.inflateLayout(@LayoutRes layoutId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View
        = LayoutInflater.from(this).inflate(layoutId, parent, attachToRoot)

/**
 * 获取当前app的版本号
 */
fun Context.getAppVersion(): String {

    val appContext = applicationContext
    val manager = appContext.getPackageManager()
    try {
        val info = manager.getPackageInfo(appContext.getPackageName(), 0)

        if (info != null)
            return info.versionName

    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }

    return ""
}

fun Context.getAppVersionCode(): Int {

    val appContext = applicationContext
    val manager = appContext.getPackageManager()
    try {
        val info = manager.getPackageInfo(appContext.getPackageName(), 0)

        if (info != null)
            return info.versionCode

    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }

    return 0
}

/**
 * 获取应用的包名
 *
 * @param context context
 * @return package name
 */
fun Context.getPackageName(): String = packageName