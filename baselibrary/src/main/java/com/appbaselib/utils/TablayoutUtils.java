package com.appbaselib.utils;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

public class TablayoutUtils {


    // 加入设置tab线的长度改变
    public static  void setTabLine(TabLayout tabLayout, int left, int right, Context mContext) {
        try {
            //拿到tabLayout的mTabStrip属性
            Field mTabStripField = tabLayout.getClass().getDeclaredField("mTabStrip");
            mTabStripField.setAccessible(true);

            LinearLayout mTabStrip = (LinearLayout) mTabStripField.get(tabLayout);

            for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                View tabView = mTabStrip.getChildAt(i);

                //拿到tabView的mTextView属性
                Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                mTextViewField.setAccessible(true);

                TextView mTextView = (TextView) mTextViewField.get(tabView);

                tabView.setPadding(0, 0, 0, 0);

                //字多宽线就多宽，所以测量mTextView的宽度
                int width = 0;
                width = mTextView.getWidth();
                if (width == 0) {
                    mTextView.measure(0, 0);
                    width = mTextView.getMeasuredWidth();
                }

                //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                params.width = width;
                params.setMargins(ScreenUtils.dp2px(mContext, left), 0, ScreenUtils.dp2px(mContext, right), 0);
                tabView.setLayoutParams(params);

                tabView.invalidate();
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
