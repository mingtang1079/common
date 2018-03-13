package com.appbaselib.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.appbaselib.loading.VaryViewHelperController;
import com.appbaselib.netstatus.NetUtils;
import com.appbaselib.utils.LogUtils;
import com.pangu.appbaselibrary.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


/**
 * Created by tangming on 2016/11/15.
 * Altered by Allen on 2017/09/15, 添加Toolbar menu加载
 */

public abstract class BaseActivity extends BaseAppCompatActivity {

    private VaryViewHelperController mVaryViewHelperController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findView();//用于解决部分控件使用了VaryViewHelperController，在在initView中初始化的问题(R2不知道为啥不起作用)
        ButterKnife.bind(this);
        if (null != getLoadingTargetView()) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
        if (registerEventBus()) {
            EventBus.getDefault().register(this);    //alter  by  tangming  加入eventbus
        }
        getIntentData();
        initView();
        initToolbar();
    }

    // 初始化Toolbar
    @CallSuper
    protected void initToolbar() {
        if (getToolbar() != null) {   //设置标题必须在此之前设置  （比如initview）
//            setSupportActionBar(getToolbar());
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true); //显示上一步按钮
            getToolbar().setNavigationIcon(R.drawable.icon_back);
            getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View mView) {
                    finish();
                }
            });
        } else {
            LogUtils.e("木有toolbar");
        }
    }

    /**
     * moudle 里面的 butternife 使用 R2好像无效
     */
    protected void findView() {

    }

    /**
     * 获取intentdata
     */
    protected void getIntentData() {

    }

    public abstract Toolbar getToolbar();

    //请求数据统一的方法,以便统一修改  不强制使用，但如果不使用会导致下面的loaderror方法无作用，且以后不方便修改
    protected void requestData() {
    }

    //统一的加载失败处理方式  不强制使用
    public void loadError() {
        toggleShowError(true, "加载失败,点击重新加载", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleShowLoading(true, "加载中……");
                requestData();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {   //但点击toolbar的返回按钮的时候，
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (registerEventBus()) {
            EventBus.getDefault().unregister(this);   //alter  tm   17 5/26
        }
    }

    protected abstract void initView();

    /**
     * 是否注册EventBus，默认不注册
     *
     * @return
     */
    protected boolean registerEventBus() {
        return false;
    }


    //===============================================我是分隔符================================================


    @Override
    protected boolean isListenNetWork() {
        return false;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return null;
    }


    /**
     * toggle show loading
     *
     * @param toggle
     */
    protected void toggleShowLoading(boolean toggle, String msg) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showLoading(msg);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * @param toggle
     */

    protected void toggleShowLoading(boolean toggle) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showLoading("加载中……");
        } else {
            mVaryViewHelperController.restore();
        }
    }


    /**
     * toggle show empty
     *
     * @param toggle
     */
    protected void toggleShowEmpty(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showEmpty(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * toggle show error
     *
     * @param toggle
     */
    protected void toggleShowError(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showError(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }


    /**
     * toggle show error
     *
     * @param toggle
     */
    protected void toggleShowError(boolean toggle, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showError("获取失败，请点击重新获取~", onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * toggle show network error
     *
     * @param toggle
     */
    protected void toggleNetworkError(boolean toggle, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showNetworkError(onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

}
