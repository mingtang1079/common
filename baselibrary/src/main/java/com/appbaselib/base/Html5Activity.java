package com.appbaselib.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.pangu.appbaselibrary.R;

/**
 * Created by tangming on 2018/5/18.
 */

public class Html5Activity extends BaseActivity {

    private String mUrl;
    private LinearLayout mLayout;
    private WebView mWebView;


    @Override
    public Toolbar getToolbar() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_web;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}
