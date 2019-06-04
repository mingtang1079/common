package com.appbaselib.common

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.pangu.appbaselibrary.R

/**
 * Created by CLOUD on 2016/10/14.
 */

object ImageLoader {
    fun load(context: Context, path: String, imageView: ImageView) {
        Glide.with(context).load(path)
                //  .centerCrop()  //
                //                .error(R.drawable.image_failure)
                .transition(withCrossFade())
                .into(imageView)

    }

    fun load(context: Context, path: String, imageView: ImageView,corner:Float) {
        Glide.with(context).load(path)
                //  .centerCrop()  //
                .apply(RequestOptions().transforms(CenterCrop(), RoundCorner(context, 12f)))
             //   .transition(withCrossFade())
                .into(imageView)

    }


    fun load(context: Context, path: Uri, imageView: ImageView) {
        Glide.with(context).load(path)
                //  .centerCrop()  //
                .transition(withCrossFade())
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
