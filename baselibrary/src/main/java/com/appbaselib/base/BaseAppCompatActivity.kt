/*
 * Copyright (c) 2015 [1076559197@qq.com | tchen0707@gmail.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License”);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appbaselib.base

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager

import com.appbaselib.netstatus.NetChangeObserver
import com.appbaselib.netstatus.NetStateReceiver
import com.appbaselib.netstatus.NetUtils
import com.appbaselib.utils.ToastUtils
import com.pangu.appbaselibrary.R


abstract class BaseAppCompatActivity : AppCompatActivity() {

    /**
     * Screen information
     */
    protected var mScreenWidth = 0
    protected var mScreenHeight = 0
    protected var mScreenDensity = 0.0f

    /**
     * context
     */
    protected var mContext: Context? = null

    /**
     * network status
     */
    protected var mNetChangeObserver: NetChangeObserver? = null

    protected abstract val isListenNetWork: Boolean

    /**
     * get bundle data
     *
     * @param extras
     */
    //   protected abstract void getBundleExtras(Bundle extras);

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract fun getContentViewLayoutID(): Int


    /**
     * get loading target view
     */
    protected abstract fun getLoadingTargetView(): View?

    /**
     * get the overridePendingTransition mode
     */
    protected abstract val overridePendingTransitionMode: TransitionMode?

    /**
     * overridePendingTransition mode
     */
    enum class TransitionMode {
        LEFT, RIGHT, TOP, BOTTOM, SCALE, FADE
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        if (toggleOverridePendingTransition()) {
            when (overridePendingTransitionMode) {
                BaseAppCompatActivity.TransitionMode.LEFT -> overridePendingTransition(R.anim.left_in, R.anim.left_out)
                BaseAppCompatActivity.TransitionMode.RIGHT -> overridePendingTransition(R.anim.right_in, R.anim.right_out)
                BaseAppCompatActivity.TransitionMode.TOP -> overridePendingTransition(R.anim.top_in, R.anim.top_out)
                BaseAppCompatActivity.TransitionMode.BOTTOM -> overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out)
                BaseAppCompatActivity.TransitionMode.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
                BaseAppCompatActivity.TransitionMode.FADE -> overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }
        super.onCreate(savedInstanceState)
        mContext = this
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        //屏幕参数
        mScreenDensity = displayMetrics.density
        mScreenHeight = displayMetrics.heightPixels
        mScreenWidth = displayMetrics.widthPixels

        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID())
        } else {
            throw IllegalArgumentException("You must return a right contentView layout resource Id")
        }

        //监听网络
        if (isListenNetWork) {

            mNetChangeObserver = object : NetChangeObserver() {
                override fun onNetConnected(type: NetUtils.NetType) {
                    super.onNetConnected(type)
                    onNetworkConnected(type)
                }

                override fun onNetDisConnect() {
                    super.onNetDisConnect()
                    onNetworkDisConnected()
                }
            }

            NetStateReceiver.registerObserver(mNetChangeObserver)
        }
    }

    override fun finish() {
        super.finish()
        if (toggleOverridePendingTransition()) {
            when (overridePendingTransitionMode) {
                BaseAppCompatActivity.TransitionMode.LEFT -> overridePendingTransition(R.anim.left_in, R.anim.left_out)
                BaseAppCompatActivity.TransitionMode.RIGHT -> overridePendingTransition(R.anim.right_in, R.anim.right_out)
                BaseAppCompatActivity.TransitionMode.TOP -> overridePendingTransition(R.anim.top_in, R.anim.top_out)
                BaseAppCompatActivity.TransitionMode.BOTTOM -> overridePendingTransition(R.anim.bottom_in, R.anim.bottom_out)
                BaseAppCompatActivity.TransitionMode.SCALE -> overridePendingTransition(R.anim.scale_in, R.anim.scale_out)
                BaseAppCompatActivity.TransitionMode.FADE -> overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (isListenNetWork)
            NetStateReceiver.removeRegisterObserver(mNetChangeObserver)

    }

    /**
     * init all views and add events
     */
    //  protected abstract void initView();

    /**
     * network connected
     */
    protected abstract fun onNetworkConnected(type: NetUtils.NetType)

    /**
     * network disconnected
     */
    protected abstract fun onNetworkDisConnected()

    /**
     * is bind eventBus
     *
     * @return
     */
    //   protected abstract boolean isBindEventBusHere();

    /**
     * toggle overridePendingTransition
     *
     * @return
     */
    protected abstract fun toggleOverridePendingTransition(): Boolean

    /**
     * startActivity
     *
     * @param clazz
     */
    protected fun start(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
    }

    /**
     * startActivity with bundle
     *
     * @param clazz
     * @param bundle
     */
    protected fun start(clazz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * startActivity then finish
     *
     * @param clazz
     */
    protected fun startThenKill(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        startActivity(intent)
        finish()
    }

    /**
     * startActivity with bundle then finish
     *
     * @param clazz
     * @param bundle
     */
    protected fun startThenKill(clazz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        finish()
    }

    /**
     * startActivityForResult
     *
     * @param clazz
     * @param requestCode
     */
    protected fun startForResult(clazz: Class<*>, requestCode: Int) {
        val intent = Intent(this, clazz)
        startActivityForResult(intent, requestCode)
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected fun startForResult(clazz: Class<*>, requestCode: Int, bundle: Bundle?) {
        val intent = Intent(this, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }

    /**
     * show toast
     *
     * @param msg  material design   snackbar
     */
    //    protected void showSnackbar(String msg) {
    //        //防止遮盖虚拟按键
    //        if (null != msg && !CommonUtils.isEmpty(msg)) {
    //            Snackbar.make(getLoadingTargetView(), msg, Snackbar.LENGTH_SHORT).show();
    //        }
    //    }

    /**
     * show toast
     *
     * @param msg
     */
    protected fun showToast(msg: String?) {
        if (null != msg && !TextUtils.isEmpty(msg)) {
            ToastUtils.showShort(mContext, msg)
        }
    }

    protected fun showLongToast(msg: String?) {
        if (null != msg && !TextUtils.isEmpty(msg)) {
            ToastUtils.showLong(mContext, msg)
        }
    }

    /**
     * set status bar translucency
     *
     * @param on
     */
    @Deprecated("")
    protected fun setTranslucentStatus(on: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val win = window
            val winParams = win.attributes
            val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            if (on) {
                winParams.flags = winParams.flags or bits
            } else {
                winParams.flags = winParams.flags and bits.inv()
            }
            win.attributes = winParams
        }
    }
}
