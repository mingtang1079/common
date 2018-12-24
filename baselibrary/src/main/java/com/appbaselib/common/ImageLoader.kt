package com.appbaselib.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener

/**
 * Created by CLOUD on 2016/10/14.
 */

object ImageLoader {

    fun load(context: Context, path: String, imageView: ImageView) {
        Glide.with(context).load(path)
                //  .centerCrop()  //
                //                .placeholder(R.drawable.image_loading)
                //                .error(R.drawable.image_failure)
                .transition(withCrossFade())
                .into(imageView)

    }

    fun load(context: Context, path: Uri, imageView: ImageView) {
        Glide.with(context).load(path)
                //  .centerCrop()  //
                .transition(withCrossFade())
                //                .placeholder(R.drawable.image_loading)
                //                .error(R.drawable.image_failure)
                .into(imageView)

    }

    fun load(context: Context, path: String, imageView: ImageView, mRequestListener: RequestListener<Drawable>) {
        Glide.with(context).load(path)
                .transition(withCrossFade())
                .listener(mRequestListener)
                .into(imageView)

    }

    fun load(context: Context, path: Uri, imageView: ImageView, mRequestListener: RequestListener<Drawable>) {
        Glide.with(context).load(path)
                .transition(withCrossFade())
                .listener(mRequestListener)
                .into(imageView)

    }
}
