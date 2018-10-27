package com.appbaselib.common

import android.content.Context
import android.net.Uri
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener

/**
 * Created by CLOUD on 2016/10/14.
 */

object ImageLoader {

    fun load(context: Context, path: String, imageView: ImageView) {
        Glide.with(context).load(path)
                //  .centerCrop()  //
                .crossFade()
                //                .placeholder(R.drawable.image_loading)
                //                .error(R.drawable.image_failure)
                .into(imageView)

    }

    fun load(context: Context, path: Uri, imageView: ImageView) {
        Glide.with(context).load(path)
                //  .centerCrop()  //
                .crossFade()
                //                .placeholder(R.drawable.image_loading)
                //                .error(R.drawable.image_failure)
                .into(imageView)

    }

    fun load(context: Context, path: String, imageView: ImageView, mRequestListener: RequestListener<String, GlideDrawable>) {
        Glide.with(context).load(path)
                .crossFade()
                .listener(mRequestListener)
                .into(imageView)

    }

    fun load(context: Context, path: Uri, imageView: ImageView, mRequestListener: RequestListener<Uri, GlideDrawable>) {
        Glide.with(context).load(path)
                .crossFade()
                .listener(mRequestListener)
                .into(imageView)

    }
}
