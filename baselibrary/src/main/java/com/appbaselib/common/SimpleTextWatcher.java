package com.appbaselib.common;

import android.text.TextWatcher;

/**
 * Description: 简化TextWatcher
 * Created by lbw on 2017/7/18 0018.
 */

public abstract class SimpleTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }
}
