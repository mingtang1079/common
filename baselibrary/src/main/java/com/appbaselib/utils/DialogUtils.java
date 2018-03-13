package com.appbaselib.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.pangu.appbaselibrary.R;

public class DialogUtils {

    public static ProgressDialog getDefaultProgressDialog(Context mContext) {

        return new ProgressDialog(mContext);

    }

    public static AlertDialog.Builder getDefaultDialog(Context mContext, String title,String content,
                                                       String positiveText, DialogInterface.OnClickListener mOnClickListener) {

        return new AlertDialog.Builder(mContext).setTitle(title).setMessage(content).setNegativeButton("取消", null).setPositiveButton(positiveText, mOnClickListener);

    }

}
