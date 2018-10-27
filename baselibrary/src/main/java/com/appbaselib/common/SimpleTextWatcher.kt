package com.appbaselib.common

import android.text.TextWatcher

/**
 * Description: 简化TextWatcher
 * Created by lbw on 2017/7/18 0018.
 */

abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

    }
}
