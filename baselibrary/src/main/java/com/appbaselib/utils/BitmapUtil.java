package com.appbaselib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by lbw on 2017/08/03
 * Bitmap工具类
 */
public class BitmapUtil {

    /**
     * Drawable转化为Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;

    }

    public static Bitmap idToBitmap(Context context, int res) {
        return BitmapFactory.decodeResource(context.getResources(), res);
    }

    /**
     * Bitmap to Drawable
     *
     * @param bitmap
     * @param mcontext
     * @return
     */
    public static Drawable bitmapToDrawble(Bitmap bitmap, Context mcontext) {
        return new BitmapDrawable(mcontext.getResources(), bitmap);
    }

    /**
     * saveBitmap2file bitmap  转为文件
     *
     * @param bmp 参数描述
     * @return Object 返回对象描述
     * @Exception 异常描述
     */
    public static String saveBitmap2file(String destName, Bitmap bmp) {
        String path = null;
        try {
            path = destName + ".jpg";

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            return path;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * bitmap 裁剪为圆形
     *
     * @param bitmap
     * @param radius
     * @return
     */
    public static Bitmap getRoundBitmap(Bitmap bitmap, int radius) {
        Bitmap scaledSrcBmp;
        int diameter = radius * 2;
        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
        int bmpWidth = bitmap.getWidth();
        int bmpHeight = bitmap.getHeight();
        int squareWidth = 0, squareHeight = 0;
        int x = 0, y = 0;
        Bitmap squareBitmap;
        if (bmpHeight > bmpWidth) {// 高大于宽
            squareWidth = squareHeight = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            // 截取正方形图片
            squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth, squareHeight);
        } else if (bmpHeight < bmpWidth) {// 宽大于高
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bitmap, x, y, squareWidth, squareHeight);
        } else {
            squareBitmap = bitmap;
        }
        if (squareBitmap.getWidth() != diameter || squareBitmap.getHeight() != diameter) {
            scaledSrcBmp = Bitmap.createScaledBitmap(squareBitmap, diameter, diameter, true);
        } else {
            scaledSrcBmp = squareBitmap;
        }
        Bitmap output = Bitmap.createBitmap(scaledSrcBmp.getWidth(),
                scaledSrcBmp.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, scaledSrcBmp.getWidth(), scaledSrcBmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(scaledSrcBmp.getWidth() / 2,
                scaledSrcBmp.getHeight() / 2,
                scaledSrcBmp.getWidth() / 2,
                paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(scaledSrcBmp, rect, rect, paint);
        bitmap = null;
        squareBitmap = null;
        scaledSrcBmp = null;
        return output;
    }

    public static Bitmap compoundBitmap(Bitmap dBitmap, Bitmap uBitmap) {
        if (dBitmap == null || uBitmap == null) {
            return null;
        }
        Bitmap rBitmap = Bitmap.createBitmap(dBitmap.getWidth(), dBitmap.getHeight(), dBitmap.getConfig());
        Canvas canvas = new Canvas(rBitmap);
        canvas.drawBitmap(rBitmap, 0, 0, null);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        int left = dBitmap.getWidth() / 2 - uBitmap.getWidth() / 2;
        int top = dBitmap.getHeight() / 2 - uBitmap.getHeight() / 2;
        canvas.drawBitmap(uBitmap, left, top, paint);

        if (!dBitmap.isRecycled()) {
            dBitmap.recycle();
        }
        if (!uBitmap.isRecycled()) {
            uBitmap.recycle();
        }

        return rBitmap;
    }


    /**
     * 合成出图、文字
     *
     * @param src
     * @param color
     * @param text
     * @return
     */
    public static Bitmap compoundBitmap(Context context, Bitmap src, int color, String text) {
        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        Bitmap rBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(rBitmap);
        // cv.drawBitmap(src, 0, 0, null);
        //加入图片
        if (color != 0) {
            cv.drawColor(color, PorterDuff.Mode.SRC_IN);
        }
        //加入文字
        if (text != null) {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setAntiAlias(true);
            paint.setTextSize(ScreenUtils.sp2px(context, 14));
            float xPos = w / 2 - paint.measureText(text) / 2;
            float yPos = h / 2 - paint.measureText(text) / 2;
            cv.drawText(text, xPos, yPos, paint);
        }
        cv.save(Canvas.ALL_SAVE_FLAG);
        cv.restore();
        return rBitmap;
    }

    /**
     * @param src
     * @param degree
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap src, float degree) {
        Matrix matrix = new Matrix();
        matrix.setRotate(degree);

        Bitmap dstBitmap = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);

        return dstBitmap;
    }

    public static byte[] bitmapToByte(Bitmap mBitmap) {

        int bytes = mBitmap.getByteCount();
        ByteBuffer buf = ByteBuffer.allocate(bytes);
        mBitmap.copyPixelsToBuffer(buf);
        return buf.array();
    }


}
