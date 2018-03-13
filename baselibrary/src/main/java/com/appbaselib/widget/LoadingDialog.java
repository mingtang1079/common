package com.appbaselib.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.pangu.appbaselibrary.R;

/**
 * 加载数据时的dialog
 * Created by Administrator on 2016/11/29 0029.
 */
public class LoadingDialog {

    private Dialog loadingDialog;
    private ImageView iv_dialog;
    private Animation animation;

    public LoadingDialog(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);
        iv_dialog = (ImageView) v.findViewById(R.id.iv_loading);
        animation = AnimationUtils.loadAnimation(
                context, R.anim.loading_animation);
        loadingDialog = new Dialog(context, R.style.loading_dialog);
        loadingDialog.setCancelable(true);// 不可以用“返回键”取消
        loadingDialog.setContentView(v);
    }

    public void show() {
        try {
            iv_dialog.startAnimation(animation);
            loadingDialog.show();
        } catch (Exception ignored) {
        }
    }

    public void dismiss() {
        try {
            iv_dialog.clearAnimation();
            loadingDialog.dismiss();
        } catch (Exception ignored) {
        }
    }
}
