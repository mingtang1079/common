package com.appbaselib.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.appbaselib.view.datepicker.data.ChineseCalendar;
import com.appbaselib.view.datepicker.view.DateTimeView;
import com.appbaselib.view.datepicker.view.GregorianLunarCalendarView;
import com.appbaselib.view.datepicker.view.OnDateSelectedListener;
import com.pangu.appbaselibrary.R;

import java.util.Calendar;

/**
 * Created by tangming on 2017/7/7.
 */

@SuppressWarnings("WrongConstant")
public class DatePickerDialogUtils {
    /**
     * 获取时间选择的View （不带时分秒）
     * @param mContext 上下文
     * @param mOnDateSetListener 值监听
     * @return
     */
    public static View getDefaultDatePicker(final Context mContext, GregorianLunarCalendarView.OnDateChangedListener mOnDateSetListener) {

        View mView = LayoutInflater.from(mContext).inflate(R.layout.view_data_picler, null);
        GregorianLunarCalendarView mGLCView = (GregorianLunarCalendarView) mView.findViewById(R.id.calendar_view);
        //3种方式进行初始化：
        //1.默认日期今天，默认模式公历
        mGLCView.init();
        //3.指定日期，指定公历/农历模式。这里的公历模式/农历模式是指显示时采用的显示方式，
        //  和前面的参数Calendar无关，无论使用Calendar还是ChineseCalendar，GregorianLunarCalendarView需要的只是某一指定的年月日。
        // mGLCView.init(Calendar c, boolean isGregorian);
        //获取数据：
        //采用GregorianLunarCalendarView.CalendarData内部类来存储当前日期，使用getCalendarData()函数获取选中date数据
        GregorianLunarCalendarView.CalendarData calendarData = mGLCView.getCalendarData();
        //进一步获取日期
        Calendar calendar = calendarData.getCalendar();//返回的是ChineseCalendar对象
        //若当前时间是： 公历2016年06月20日 农历二零一六年五月十六，那么获取的返回值如下：
        int yearG = calendar.get(Calendar.YEAR);//获取公历年 2016
        int monthG = calendar.get(Calendar.MONTH) + 1;//获取公历月 6
        int dayG = calendar.get(Calendar.DAY_OF_MONTH);//获取公历日 20
        int yearL = calendar.get(ChineseCalendar.CHINESE_YEAR);//获取农历年 2016
        int monthL = calendar.get(ChineseCalendar.CHINESE_MONTH);//获取农历月 5//注意，如果是闰五月则返回-5
        int dayL = calendar.get(ChineseCalendar.CHINESE_DATE);//获取农历日 20

        //添加日期改变的回调
//        mGLCView.setOnDateChangedListener(new GregorianLunarCalendarView.OnDateChangedListener() {
//                                              @Override
//                                              public void onDateChanged(GregorianLunarCalendarView.CalendarData calendarData) {
//                                                  Calendar calendar = calendarData.getCalendar();
//                                                  String showToast = "Gregorian : " + calendar.get(Calendar.YEAR) + "-"
//                                                          + (calendar.get(Calendar.MONTH) + 1) + "-"
//                                                          + calendar.get(Calendar.DAY_OF_MONTH) + "\n"
//                                                          + "Lunar     : " + calendar.get(ChineseCalendar.CHINESE_YEAR) + "-"
//                                                          + (calendar.get(ChineseCalendar.CHINESE_MONTH)) + "-"
//                                                          + calendar.get(ChineseCalendar.CHINESE_DATE);
//                                              }
//                                          }
//        );
        mGLCView.setOnDateChangedListener(mOnDateSetListener);
        return mGLCView;
    }

    /**
     * 获取时间选择dialog （年月日）
     * @param mContext
     * @param mOnDateSelectedListener
     * @return
     */
    public static AlertDialog.Builder getDefaultDatePickerDialog(final Context mContext, final OnDateSelectedListener mOnDateSelectedListener) {

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.view_data_picler, null);
        final GregorianLunarCalendarView mGLCView = (GregorianLunarCalendarView) mView.findViewById(R.id.calendar_view);
        mGLCView.init();
        mBuilder.setView(mView);
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface mDialogInterface, int mI) {
                mOnDateSelectedListener.onDateSelected(mGLCView.getCalendarData());
            }
        });
        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface mDialogInterface, int mI) {
                mDialogInterface.dismiss();
            }
        });

        return mBuilder;

    }

    /**
     * 获取时间选择dialog （年月）
     * @param mContext
     * @param mOnDateSelectedListener
     * @return
     */
    public static AlertDialog.Builder getDefaultDatePickerDialog3(final Context mContext, final OnDateSelectedListener mOnDateSelectedListener) {

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.view_data_picler, null);
        final GregorianLunarCalendarView mGLCView = (GregorianLunarCalendarView) mView.findViewById(R.id.calendar_view);
        mGLCView.init();
        mGLCView.setNumberPickerDayVisibility(View.GONE);
        mBuilder.setView(mView);
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface mDialogInterface, int mI) {
                mOnDateSelectedListener.onDateSelected(mGLCView.getCalendarData());
            }
        });
        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface mDialogInterface, int mI) {
                mDialogInterface.dismiss();
            }
        });

        return mBuilder;

    }

    /**
     * 获取时间选择dialog （时分秒）
     * @param mContext
     * @param mOnDateSelectedListener
     * @return
     */
    public static AlertDialog.Builder getDefaultDatePickerDialog2(final Context mContext, final OnDateSelectedListener mOnDateSelectedListener) {

        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.view_time, null);

        final DateTimeView mDateTimeView = (DateTimeView) mView.findViewById(R.id.calendar_time_view);
        mBuilder.setView(mView);
        mBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface mDialogInterface, int mI) {
                mOnDateSelectedListener.onDateSelected(mDateTimeView.getCalendarData());
            }
        });
        mBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface mDialogInterface, int mI) {
                mDialogInterface.dismiss();
            }
        });
        return mBuilder;

    }


}
