package com.safframework.ext

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

/**
 * Created by Tony Shen on 2017/6/30.
 */

val View.isVisible: Boolean
    get() = visibility == View.VISIBLE
val View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE
val View.isGone: Boolean
    get() = visibility == View.GONE

fun View.hideKeyboard():Boolean {
    clearFocus()
    return (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard():Boolean {
    requestFocus()
    return (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

/***
 * 设置延迟时间的View扩展
 * @param delay Long 延迟时间，默认600毫秒
 * @return T
 */
fun <T : View> T.withTrigger(delay: Long = 600): T {
    triggerDelay = delay
    return this
}

/***
 * 点击事件的View扩展
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.click(block: (T) -> Unit) = setOnClickListener {

    if (clickEnable()) {
        block(it as T)
    }
}

/***
 * 带延迟过滤的点击事件View扩展
 * @param delay Long 延迟时间，默认600毫秒
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.clickWithTrigger(time: Long = 600, block: (T) -> Unit){
    triggerDelay = time
    setOnClickListener {
        if (clickEnable()) {
            block(it as T)
        }
    }
}

private var <T : View> T.triggerLastTime: Long
    get() = if (getTag(1123460103) != null) getTag(1123460103) as Long else 0
    set(value) {
        setTag(1123460103, value)
    }

private var <T : View> T.triggerDelay: Long
    get() = if (getTag(1123461123) != null) getTag(1123461123) as Long else -1
    set(value) {
        setTag(1123461123, value)
    }

private fun <T : View> T.clickEnable(): Boolean {
    var flag = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        flag = true
    }
    triggerLastTime = currentClickTime
    return flag
}

fun <T : View> T.longClick(block: (T) -> Boolean) = setOnLongClickListener { block(it as T) }

fun View.toBitmap(): Bitmap?{
    clearFocus()
    isPressed = false
    val willNotCache = willNotCacheDrawing()
    setWillNotCacheDrawing(false)
    // Reset the drawing cache background color to fully transparent
    // for the duration of this operation
    val color = drawingCacheBackgroundColor
    drawingCacheBackgroundColor = 0
    if (color != 0) destroyDrawingCache()
    buildDrawingCache()
    val cacheBitmap = drawingCache
    if (cacheBitmap == null) {
        Log.e("Views", "failed to get bitmap from $this", RuntimeException())
        return null
    }
    val bitmap = Bitmap.createBitmap(cacheBitmap)
    // Restore the view
    destroyDrawingCache()
    setWillNotCacheDrawing(willNotCache)
    drawingCacheBackgroundColor = color
    return bitmap
}

/**
 * 获取View的截图, 支持获取整个RecyclerView列表的长截图
 * 注意：调用该方法时，请确保View已经测量完毕，如果宽高为0，则将抛出异常
 */
//fun View.toBitmap2(): Bitmap {
//    if(measuredWidth==0 || measuredHeight==0){
//        throw java.lang.RuntimeException("调用该方法时，请确保View已经测量完毕，如果宽高为0，则抛出异常以提醒！")
//    }
//    return when (this) {
//        is RecyclerView -> {
//            this.scrollToPosition(0)
//            this.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
//                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
//
//            val bmp = Bitmap.createBitmap(width, measuredHeight, Bitmap.Config.ARGB_8888)
//            val canvas = Canvas(bmp)
//
//            //draw default bg, otherwise will be black
//            if (background != null) {
//                background.setBounds(0, 0, width, measuredHeight)
//                background.draw(canvas)
//            } else {
//                canvas.drawColor(Color.WHITE)
//            }
//            this.draw(canvas)
//            //恢复高度
//            this.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
//                    View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST))
//            bmp //return
//        }
//        else -> {
//            val screenshot = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_4444)
//            val canvas = Canvas(screenshot)
//            if (background != null) {
//                background.setBounds(0, 0, width, measuredHeight)
//                background.draw(canvas)
//            } else {
//                canvas.drawColor(Color.WHITE)
//            }
//            draw(canvas)// 将 view 画到画布上
//            screenshot //return
//        }
//    }
//}



/**
 * 设置View的高度
 */
fun View.height(height: Int): View {
    val params = layoutParams
    params.height = height
    layoutParams = params
    return this
}
/**
 * 设置View的宽度
 */
fun View.width(width: Int): View {
    val params = layoutParams
    params.width = width
    layoutParams = params
    return this
}

/**
 * 设置View的宽度和高度
 * @param width 要设置的宽度
 * @param height 要设置的高度
 */
fun View.widthAndHeight(width: Int, height: Int): View {
    val params = layoutParams
    params.width = width
    params.height = height
    layoutParams = params
    return this
}


/**
 * 设置View的margin
 * @param leftMargin 默认是0
 * @param topMargin 默认是0
 * @param rightMargin 默认是0
 * @param bottomMargin 默认是0
 */
fun View.margin(leftMargin: Int = 0, topMargin: Int = 0, rightMargin: Int = 0, bottomMargin:Int = 0): View{
    val params = layoutParams as ViewGroup.MarginLayoutParams
    params.leftMargin = leftMargin
    params.topMargin = topMargin
    params.rightMargin = rightMargin
    params.bottomMargin = bottomMargin
    layoutParams = params
    return this
}