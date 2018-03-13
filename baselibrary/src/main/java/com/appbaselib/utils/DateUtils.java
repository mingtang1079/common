package com.appbaselib.utils;

import android.text.TextUtils;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * DateUtils
 *
 * @author hqw 2015-8-5
 */
public class DateUtils {

    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_MONTH = new SimpleDateFormat("MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_SecondToMinute = new SimpleDateFormat("mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_HOUR_Minute = new SimpleDateFormat("HH:mm");
    public static final SimpleDateFormat DATE_FORMAT_HOUR_Minute_Sec = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat DATE_FORMAT_DATE_D = new SimpleDateFormat("yyyy.MM.dd");
    public static final String DATE_FORMAT_MM_DD_HH_MI_SS = "MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_MM_DD_HH_MI = "MM-dd HH:mm";
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH = "yyyy-MM-dd HH:mm";//罗肖增加
    public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD_HH_LONG = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private DateUtils() {
        throw new AssertionError();
    }


    //获取时间 年月日
    public static String getCurrentTimeYmd() {


        return DATE_FORMAT_DATE.format(new java.util.Date());

    }


    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        if (timeInMillis == 0)
            return "";
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(String timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }


    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {

        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTimeYmd(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_DATE);
    }


    /**
     * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTimeDate(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_DATE);
    }

    /**
     * 获取 MM-DD
     *
     * @param timeInMillis
     * @return
     */
    public static String getTimeMonth(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_MONTH);
    }

    public static String getTimeYear(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_DATE);
    }

    public static String getTimeHourMinute(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_HOUR_Minute);
    }

    /**
     * long time to string, format is {@link #DATE_FORMAT_SecondToMinute}
     *
     * @param timeInMillis
     * @return
     */
    public static String getTimeSecondToMinute(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_SecondToMinute);
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeLong() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    /**
     * @param time
     * @param dateFormat
     * @return
     */
    public static String getDateFormateTime(String time, SimpleDateFormat dateFormat) {
        if (time == null)
            return "";
        return dateFormat.format(time);
    }

    public static Date getDateFromDateString(String source, SimpleDateFormat dateFormat) {
        Date date = null;
        try {
            date = dateFormat.parse(source);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date;
    }

    /**
     * @param time
     * @param dateFormat
     * @return
     */
    public static String getDateFormateTime(long time, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(time));
    }

    /**
     * get current time ��ȥ oldtime
     *
     * @return
     */
    public static int getNowTime2OldTime(String oldtime) {
        if (oldtime == null)
            return -1;
        try {
            Date oltime = DEFAULT_DATE_FORMAT.parse(oldtime);
            return (new Date().getDay() - oltime.getDay());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 检测当前时间 是否是今天
     *
     * @param millions
     * @return
     */
    public static boolean isCurrentDay(long millions) {
        Date index;

        index = new Date(millions);
        String curDate = DATE_FORMAT_DATE.format(index);

        index = new Date();
        String oldDate = DATE_FORMAT_DATE.format(index);

        if (curDate.equals(oldDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取时间的Long
     *
     * @param str millions 或Date类型
     * @return
     */
    public static long getLongTime(String str) {
        if (str.contains(":")) {
            return getTimeLong(str);
        }

        return getMillinsLong(str);
    }

    /**
     * 获取毫秒 Long
     *
     * @param str
     * @return
     */
    public static long getMillinsLong(String str) {
        return Long.parseLong(str);
    }

    /**
     * 获取Date 毫秒
     *
     * @param str
     * @return
     */
    public static long getTimeLong(String str) {
        Date date = new Date();
        try {
            date = DEFAULT_DATE_FORMAT.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 不获取具体时间 只获取年月日
     *
     * @param str
     * @return
     */
    public static long getTimeLongYmd(String str) {
        Date date = new Date();
        try {
            date = DATE_FORMAT_DATE.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 不获取具体时间 只获取年月日
     * 将精确到分钟的日期格式转换成long
     *
     * @param str
     * @return
     */
    public static long getTimeLongYmdhm(String str) {
        Date date = new Date();
        try {
            date = DATE_FORMAT_YYYY_MM_DD_HH_LONG.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    /**
     * 两个时间之间相差距离多少小时
     *
     * @param str1 时间参数 1：
     * @param str2 时间参数 2：
     * @return 相差天数
     */
    public static long getDistanceHours(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
        Date one;
        Date two;
        long days = 0;
        long hour = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
            hour = (diff / (60 * 60 * 1000) - days * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days * 24 + hour;
    }

    /**
     * 获取东八时区
     */
    public static long getCurrentTime() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = Calendar.getInstance().getTime();
        return date.getTime();
    }


    /**
     * 将时间毫秒值转成hh:mm:ss,小时数可以超过24
     *
     * @param timeInMillis
     * @return
     */
    public static String getTimeStr(long timeInMillis) {
        StringBuffer time = new StringBuffer();
        int hour = (int) (timeInMillis / (60 * 60 * 1000));
        if (hour < 10) {
            time.append("0" + hour);
        } else {
            time.append(hour);
        }

        time.append(":");
        timeInMillis = timeInMillis - hour * 60 * 60 * 1000;
        int min = (int) (timeInMillis / (60 * 1000));
        if (min < 10) {
            time.append("0" + min);
        } else {
            time.append(min);
        }

        time.append(":");
        timeInMillis = timeInMillis - min * 60 * 1000;
        int sec = (int) (timeInMillis / 1000);
        if (sec < 10) {
            time.append("0" + sec);
        } else {
            time.append(sec);
        }
        return time.toString();
    }

    /**
     * 将毫秒转换为mm:ss格式
     *
     * @param milliseconds
     * @return mm:ss
     */
    public static String parseToMinsAndSeconds(String milliseconds) {
        int millisecond = Integer.parseInt(milliseconds);
        return parseToMinsAndSeconds(millisecond);
    }

    public static String parseSecToMinsAndSeconds(int totalSeconds) {
        int mins = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        String time = String.format("%02d", mins).concat(":").concat(String.format("%02d", seconds));
        return time;
    }

    public static String parseToMinsAndSeconds(int milliseconds) {
        int totalSeconds = milliseconds / 1000;
        int mins = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        String time = String.format("%02d", mins).concat(":").concat(String.format("%02d", seconds));
        return time;
    }

    /**
     * 获取网络时间
     *
     * @return
     */
    public static Date getWebsiteDatetime() {
        try {
            TimeZone.setDefault(TimeZone.getTimeZone("GMT+8")); // 时区设置
            URL url = new URL("http://www.baidu.com");// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象

            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String formatDate(long timemills, String format) {
        SimpleDateFormat formater;
        if (TextUtils.isEmpty(format)) {
            formater = new SimpleDateFormat("MM-dd");
        } else {
            formater = new SimpleDateFormat(format);
        }
        return formater.format(new Date(timemills));
    }

    public static String getAge(String birthDate) {
        Date birthdate = DateUtils.parse(birthDate, DateUtils.DATE_FORMAT_YYYY_MM_DD);
        String showAge = DateUtils.getAge(birthdate) + "";
        showAge += "岁";
        return showAge;
    }

    public static int getAge(Date birthDate) {
        if (birthDate == null)
            return 0;

        int age = 0;
        Date now = new Date();
        SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
        SimpleDateFormat format_M = new SimpleDateFormat("MM");

        String birth_year = format_y.format(birthDate);
        String this_year = format_y.format(now);

        String birth_month = format_M.format(birthDate);
        String this_month = format_M.format(now);

        // 初步，估算
        age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);
        // 如果未到出生月份，则age - 1
        if (this_month.compareTo(birth_month) < 0)
            age -= 1;
        if (age < 0)
            age = 0;
        return age;
    }

    public static String getAstro(int month, int day) {
        String[] astro = new String[]{"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座",
                "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};
        int[] arr = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};// 两个星座分割日
        int index = month;
        if (day < arr[month - 1]) {
            index = index - 1;
        }
        return astro[index];
    }

    /**
     * 使用用户格式提取字符串日期
     *
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */

    public static Date parse(String strDate, String pattern) {

        if (TextUtils.isEmpty(strDate)) {
            return null;
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String parse(Long strDate, String pattern) {
        if (strDate == null) {
            return "";
        }
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(new Date(strDate));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用用户格式格式化日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */

    public static String format(Date date, String pattern) {
        String returnValue = "";
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

}
