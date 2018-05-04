package com.mic.adressselectorlib;

/**
 * Author: Blincheng.
 * Date: 2017/5/9.
 * Description:
 */

public interface OnItemClickListener {
    /**
     * @param mCity 返回地址列表对应点击的对象
     * */
    void itemClick(City mProvice,City mCity,City mCounty);
}
