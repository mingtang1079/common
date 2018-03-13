package com.appbaselib.utils;

import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * Created by tamgming on 2017/10/30.
 */

public class UriUtils {

    public static Uri getImageStreamFromExternal(String imageName) {
        File externalPubPath = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES
        );

        File picPath = new File(externalPubPath, imageName);
        Uri uri = null;
        if(picPath.exists()) {
            uri = Uri.fromFile(picPath);
        }

        return uri;
    }



}
