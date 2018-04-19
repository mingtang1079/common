package com.appbaselib.common;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;

/**
 * Created by CLOUD on 2016/10/14.
 */

public class ImageLoader {

    public static void load(Context context, String path, ImageView imageView) {
        Glide.with(context).load(path)
                //  .centerCrop()  //
                .crossFade()
//                .placeholder(R.drawable.image_loading)
//                .error(R.drawable.image_failure)
                .into(imageView);

    }
    public static void load(Context context, Uri path, ImageView imageView) {
        Glide.with(context).load(path)
                //  .centerCrop()  //
                .crossFade()
//                .placeholder(R.drawable.image_loading)
//                .error(R.drawable.image_failure)
                .into(imageView);

    }

    public  static  void  load(Context context, String path, ImageView imageView, RequestListener<String,GlideDrawable> mRequestListener)
    {
        Glide.with(context).load(path)
                .crossFade()
                .listener(mRequestListener)
                .into(imageView);

    }

    public  static  void  load(Context context, Uri path, ImageView imageView, RequestListener<Uri,GlideDrawable> mRequestListener)
    {
        Glide.with(context).load(path)
                .crossFade()
                .listener(mRequestListener)
                .into(imageView);

    }
}
