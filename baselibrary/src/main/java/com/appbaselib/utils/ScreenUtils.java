package com.appbaselib.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Description: 屏幕尺寸工具类
 * Created by lbw on 2017/4/19 0019.
 */

public class ScreenUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static float dp2pxFloat(Context context, float dpValue) {
        return dpValue * context.getResources().getDisplayMetrics().density;
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px(像素)
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     */
    public static float px2sp(Context context, float size) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取目标View在屏幕中的坐标
     *
     * @param targetView
     * @return targetRect 封装了坐标位置的rect
     */
    public static Rect getGlobalVisibleRect(View targetView) {
        Rect targetRect = new Rect();
        if (targetView != null) {
            targetView.getGlobalVisibleRect(targetRect);//获取targetView在屏幕中的位置
        }
        return targetRect;
    }

    /**
     * 计算指定的 View 在屏幕中的坐标。
     */
    public static RectF calcViewScreenLocation(View view) {
        int[] location = new int[2];
        // 获取控件在屏幕中的位置，返回的数组分别为控件左顶点的 x、y 的值
        view.getLocationOnScreen(location);
        return new RectF(location[0], location[1], location[0] + view.getWidth(),
                location[1] + view.getHeight());
    }



    /**
     * 获取目标View在屏幕中的坐标
     * <p/>
     * 该坐标为已经修正过状态栏的坐标
     *
     * @param activity
     * @param targetView
     * @return
     */
    public static Rect getGlobalVisibleRect(Activity activity, View targetView) {
        Rect targetRect = new Rect();

        if (targetView != null) {
            targetView.getGlobalVisibleRect(targetRect);//获取targetView在屏幕中的位置

            Rect outRect = new Rect();//修正状态栏的影响
            activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);

            targetRect.top = targetRect.top - outRect.top;//修正
            targetRect.bottom = targetRect.bottom - outRect.top;//修正
        }
        return targetRect;
    }


    /**
     * 获取屏幕尺寸
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenSize(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
            return new Point(display.getWidth(), display.getHeight());
        }else{
            Point point = new Point();
            display.getSize(point);
            return point;
        }
    }

    /**
     * 标题栏高度
     * @return
     */
    public static int getTitleHeight(Activity activity){
        return  activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }
    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context)
    {

        int statusHeight = -1;
        try
        {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return statusHeight;
    }


}
