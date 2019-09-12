package com.appbaselib.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import androidx.core.app.Fragment;
import androidx.core.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.appbaselib.adapter.FragmentAdapter;
import com.pangu.appbaselibrary.R;

import java.util.List;

/**
 */
public abstract class BaseTabActivity extends BaseActivity {

    public Toolbar mToolbar;
    public TabLayout mTab;
    public ViewPager mViewpager;

    FragmentAdapter mFragmentAdapter;

    @Override
    protected void initView(Bundle mSavedInstanceState) {
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
