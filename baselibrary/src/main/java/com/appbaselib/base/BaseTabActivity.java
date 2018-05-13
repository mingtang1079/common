package com.appbaselib.base;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbaselib.adapter.FragmentAdapter;
import com.appbaselib.utils.ScreenUtils;
import com.pangu.appbaselibrary.R;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by tangming on 2017/6/6.   简单封装 关于改变tab数字的情况,自行在子类通过fragmment调用父activity方法实现 （例如接口回调）
 */
public abstract class BaseTabActivity extends BaseActivity {

    public Toolbar mToolbar;
    public TabLayout mTab;
    public ViewPager mViewpager;

    FragmentAdapter mFragmentAdapter;

    @Override
    protected void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTab = (TabLayout) findViewById(R.id.tab);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getFragments(), getTabTitle());
        mViewpager.setAdapter(mFragmentAdapter);
        mTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTab.setupWithViewPager(mViewpager);
      //  setTabLine(mTab, 50, 50);
    }


    protected abstract String[] getTabTitle();

    public abstract List<Fragment> getFragments();

    @Override
    public Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_tab;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }



}
