package com.appbaselib.utils;

/**
 * Description: 正则表达式常量
 * Created by Sum41forever on 2017/10/16
 * 修改备注:
 */
public class RegexConstants {

    /**
     * 正则：手机号（简单）
     */
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    /**
     * 正则：手机号（精确）
     * <p>移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188</p>
     * <p>联通：130、131、132、145、155、156、171、175、176、185、186</p>
     * <p>电信：133、153、173、177、180、181、189</p>
     * <p>全球星：1349</p>
     * <p>虚拟运营商：170</p>
     */
    public static final String REGEX_MOBILE_EXACT  = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,1,3,5-8])|(18[0-9])|(147))\\d{8}$";
    /**
     * 正则：电话号码
     */
    public static final String REGEX_TEL           = "^0\\d{2,3}[- ]?\\d{7,8}";
    /**
     * 正则：身份证号码15位
     */
    public static final String REGEX_ID_CARD15     = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    /**
     * 正则：身份证号码18位
     */
    public static final String REGEX_ID_CARD18     = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
    /**
     * 正则：邮箱
     */
    public static final String REGEX_EMAIL         = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 正则：URL
     */
    public static final String REGEX_URL           = "[a-zA-z]+://[^\\s]*";
    /**
     * 正则：汉字
     */
    public static final String REGEX_ZH            = "^[\\u4e00-\\u9fa5]+$";
    /**
     * 正则：用户名，取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
     */
    public static final String REGEX_USERNAME      = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    /**
     * 正则：yyyy-MM-dd格式的日期校验，已考虑平闰年
     */
    public static final String REGEX_DATE          = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    /**
     * 正则：IP地址
     */
    public static final String REGEX_IP            = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    ///////////////////////////////////////////////////////////////////////////
    // 以下摘自http://tool.oschina.net/regex
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 正则：双字节字符(包括汉字在内)
     */
    public static final String REGEX_DOUBLE_BYTE_CHAR     = "[^\\x00-\\xff]";
    /**
     * 正则：空白行
     */
    public static final String REGEX_BLANK_LINE           = "\\n\\s*\\r";
    /**
     * 正则：QQ号
     */
    public static final String REGEX_TENCENT_NUM          = "[1-9][0-9]{4,}";
    /**
     * 正则：中国邮政编码
     */
    public static final String REGEX_ZIP_CODE             = "[1-9]\\d{5}(?!\\d)";

    ///////////////////////////////////////////////////////////////////////////
    // 以下摘自http://www.cnblogs.com/ly5201314/archive/2008/09/04/1284139.html
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 正则：正整数
     */
    public static final String w     = "^[1-9]\\d*$";
    /**
     * 正则：负整数
     */
    public static final String REGEX_NEGATIVE_INTEGER     = "^-[1-9]\\d*$";
    /**
     * 正则：整数
     */
    public static final String REGEX_INTEGER              = "^-?[1-9]\\d*$";
    /**
     * 正则：非负整数(正整数 + 0)
     */
    public static final String REGEX_NOT_NEGATIVE_INTEGER = "^[1-9]\\d*|0$";
    /**
     * 正则：非正整数（负整数 + 0）
     */
    public static final String REGEX_NOT_POSITIVE_INTEGER = "^-[1-9]\\d*|0$";
    /**
     * 正则：正浮点数
     */
    public static final String REGEX_POSITIVE_FLOAT       = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";
    /**
     * 正则：负浮点数
     */
    public static final String REGEX_NEGATIVE_FLOAT       = "^-[1-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*$";
    /**
     * 正则：验证至少n位数字
     */
    public static final String REGEX_INTEGER_AT_LEAST       = "^\\d{n,}$";
    /**
     * 正则：验证m-n位的数字
     */
    public static final String REGEX_INTEGER_M2N_LEAST       = "^\\d{m,n}$";
    /**
     * 验证零和非零开头的数字
     */
    public static final String REGEX_START_WITHOUT_ZERO       = "^(0|[1-9][0-9]*)$";
    /**
     * 验证汉字
     */
    public static final String REGEX_IS_CHINESE_CHAR       = "^[\\u4e00-\\u9fa5],{0,}$";
    /**
     * 验证一年的12个月 正确格式为： “01”-“09”和“1”“12”
     */
    public static final String REGEX_12MONTHS_YEAR       = "^(0?[1-9]|1[0-2])$ ";

    /**
     * 验证一个月的31天 正确格式为：01、09和1、31。
     */
    public static final String REGEX_31DAYS_MONTH      = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
    /**
     * 验证由26个英文字母组成的字符串
     */
    public static final String REGEX_26ENGLISH_CHAR      = "^[A-Za-z]+$";
    /**
     * 验证由26个大写英文字母组成的字符串
     */
    public static final String REGEX_26ENGLISH_CHAR_LOW      = "^[A-Z]+$";
    /**
     * 验证由26个小写英文字母组成的字符串
     */
    public static final String REGEX_26ENGLISH_CHAR_UPPER      = "^[a-z]+$";
    /**
     * 验证由数字和26个英文字母组成的字符串
     */
    public static final String REGEX_26ENGLISH_CHAR_AND_NUM      = "^[A-Za-z0-9]+$";


}
