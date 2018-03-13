package com.appbaselib.common;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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
}
