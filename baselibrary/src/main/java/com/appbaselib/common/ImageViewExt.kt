package com.appbaselib.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder

/**
 * imageview 加载网络图片扩展函数
 */
fun ImageView.load(url: String?) {
    get(url).into(this)
}

fun ImageView.get(url: String?): RequestBuilder<Drawable> = Glide.with(context).load(url)
fun ImageView.get(url: Drawable?): RequestBuilder<Drawable>? = Glide.with(context).load(url)