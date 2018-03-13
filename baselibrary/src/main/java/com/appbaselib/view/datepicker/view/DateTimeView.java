package com.appbaselib.view.datepicker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.appbaselib.widget.NumberPickerView;
import com.pangu.appbaselibrary.R;

/**
 * Created by tangming on 2017/7/10. 时分秒选择器 自定义
 */

public class DateTimeView extends LinearLayout implements NumberPickerView.OnValueChangeListener {

    private static final int DEFAULT_GREGORIAN_COLOR = 0xff3388ff;
    private static final int DEFAULT_LUNAR_COLOR = 0xffee5544;
    private static final int DEFAULT_NORMAL_TEXT_COLOR = 0xFF555555;
    /**
     * true to use scroll anim when switch picker passively
     */
    private boolean mScrollAnim = true;

    private String[] mDisplayAmPM;
    private String[] mDisplayHours;
    private String[] mDisplayMinutes;

    private NumberPickerView mPickerViewH;
    private NumberPickerView mPickerViewM;
    private NumberPickerView mPickerViewD;

    private int mThemeColorG = DEFAULT_GREGORIAN_COLOR;
    private int mThemeColorL = DEFAULT_LUNAR_COLOR;
    private int mNormalTextColor = DEFAULT_NORMAL_TEXT_COLOR;


    public DateTimeView(Context context) {
        super(context);
        initInternal(context);
    }

    public DateTimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initInternal(context);
        initAttr(context, attrs);
    }

    public DateTimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInternal(context);
        initAttr(context, attrs);
    }

    private void initInternal(Context context) {
        View contentView = inflate(context, R.layout.view_date_time, this);
        mPickerViewD = (NumberPickerView) contentView.findViewById(R.id.picker_half_day);
        mDisplayAmPM = mPickerViewD.getDisplayedValues();
        mPickerViewH = (NumberPickerView) contentView.findViewById(R.id.picker_hour);
        mDisplayHours = mPickerViewH.getDisplayedValues();
        mPickerViewM = (NumberPickerView) contentView.findViewById(R.id.picker_minute);
        mDisplayMinutes = mPickerViewM.getDisplayedValues();
        mPickerViewD.setOnValueChangedListener(this);
        mPickerViewH.setOnValueChangedListener(this);
        mPickerViewM.setOnValueChangedListener(this);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GregorianLunarCalendarView);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.GregorianLunarCalendarView_glcv_ScrollAnimation) {
                mScrollAnim = a.getBoolean(attr, true);
            } else if (attr == R.styleable.GregorianLunarCalendarView_glcv_GregorianThemeColor) {
                mThemeColorG = a.getColor(attr, DEFAULT_GREGORIAN_COLOR);
            }
            if (attr == R.styleable.GregorianLunarCalendarView_glcv_LunarThemeColor) {
                mThemeColorL = a.getColor(attr, DEFAULT_LUNAR_COLOR);
            }
            if (attr == R.styleable.GregorianLunarCalendarView_glcv_NormalTextColor) {
                mNormalTextColor = a.getColor(attr, DEFAULT_NORMAL_TEXT_COLOR);
            }
        }
        setColor(mThemeColorG, mNormalTextColor);
        a.recycle();
    }

    public void setColor(int themeColor, int normalColor) {
        setThemeColor(themeColor);
        setNormalColor(normalColor);
    }

    public void setThemeColor(int themeColor) {
        mPickerViewD.setSelectedTextColor(themeColor);
        mPickerViewD.setHintTextColor(themeColor);
        mPickerViewD.setDividerColor(themeColor);
        mPickerViewH.setSelectedTextColor(themeColor);
        mPickerViewH.setHintTextColor(themeColor);
        mPickerViewH.setDividerColor(themeColor);
        mPickerViewM.setSelectedTextColor(themeColor);
        mPickerViewM.setHintTextColor(themeColor);
        mPickerViewM.setDividerColor(themeColor);
    }

    public void setNormalColor(int normalColor) {
        mPickerViewD.setNormalTextColor(normalColor);
        mPickerViewH.setNormalTextColor(normalColor);
        mPickerViewM.setNormalTextColor(normalColor);
    }

    @Override
    public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {


    }

    public GregorianLunarCalendarView.CalendarData getCalendarData() {

        int hour = Integer.valueOf(mPickerViewH.getContentByCurrValue());
        int minute = Integer.valueOf(mPickerViewM.getContentByCurrValue());

        if (mPickerViewD.getValue() == 1)//下午
            hour += 12;

        GregorianLunarCalendarView.CalendarData mCalendarData = new GregorianLunarCalendarView.CalendarData(hour, minute);
        return mCalendarData;
    }
}
