package com.appbaselib.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appbaselib.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pangu.appbaselibrary.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tangming on 2016/12/14.
 */

public abstract class BaseRefreshFragment<T> extends BaseFragment {


    //  @BindView(R2.id.recyclerview)
    public RecyclerView mRecyclerview;
    // @BindView(R2.id.swipe)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    public List<T> mList;
    public BaseQuickAdapter mAdapter;
    public LinearLayoutManager mLinearLayoutManager;
    public boolean isReReresh = true;//重新刷新 清楚数据
    public int pageNo = 1;  //当前页
    public boolean isFirstReresh = true;
    public int pageSize = 10; //每页条数
    public boolean isLoadmore = false; //是否开启加载更多
    public boolean isLoadmoreIng = false;  //是否正在加载更多

    //butternife  暂时无法解决
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = super.onCreateView(inflater, container, savedInstanceState);
        mRecyclerview = (RecyclerView) mView.findViewById(R.id.recyclerview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipe);
        return mView;
    }

    @CallSuper
    @Override
    protected void initView() {
        initList();
        //设置只在viewpager的情况下 开启懒加载

        if (isLazyLoad()) {
            if (!(this.getView().getParent() instanceof ViewPager)) {   //懒加载 如果 父控件不是  viewpager （adapter必须是fragmentadapter） 不会调用  setvisiblehint
                initData();
            }
        } else {
            initData();
        }

    }

    protected boolean isLazyLoad() {
        return false;
    }

    @Override
    protected void onFirstUserVisible() {
        if (isLazyLoad() && this.getView().getParent() instanceof ViewPager)  //只有当 懒加载开启，而且父控件不是viewpager的情况下 才加载数据
            initData();
    }


    @Override
    protected View getLoadingTargetView() {
        return mSwipeRefreshLayout;
    }

    //默认实现
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_recyclerview;
    }

    protected void initData() {

        toggleShowLoading(true, "加载中……");
        requestData();

    }

    // tips:要获取 getintent 的数据 ，必须重写 getIntentData方法，在里面去获取
    public void initList() {

        View mView = getActivity().getLayoutInflater().inflate(R.layout.view_empty, (ViewGroup) mRecyclerview.getParent(), false);
        mList = new ArrayList<>();
        initAdapter();
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerview.setLayoutManager(mLinearLayoutManager);
        if (mAdapter == null)
            throw new NullPointerException("adapter is null");
        mAdapter.setEmptyView(mView);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoadmoreIng)
                    refreshData(false);
                else
                    mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public void setLoadMoreListener() {
        isLoadmore = true;
        mAdapter.setEnableLoadMore(false);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                if (!mSwipeRefreshLayout.isRefreshing()) {
                    isLoadmoreIng = true;
                    requestData();
                } else {

                }

            }
        }, mRecyclerview);

    }


    public abstract void initAdapter();


    public abstract void requestData();

    //重新刷新数据
    public void refreshData(boolean isShow) {

        mRecyclerview.scrollToPosition(0);
        isReReresh = true;
        pageNo = 1;
        if (isShow)
            mSwipeRefreshLayout.setRefreshing(true);
        requestData();
        if (isLoadmore) {
            mAdapter.setEnableLoadMore(false);
            //重新刷新,重新设置加载更多的逻辑
        }

    }

    public void loadComplete(List<? extends T> mData) {

        // 2017.6.8防止 mdata 为空的情况  ，（后台数据返回为null）
        if (mData == null)
            mData = new ArrayList<>();

        if (isReReresh) {
            mList.clear();
        }
        pageNo++;
        mAdapter.addData(mData);
        mSwipeRefreshLayout.setRefreshing(false);
        toggleShowLoading(false);

        if (isFirstReresh || isReReresh) {
            if (isFirstReresh)
                mRecyclerview.setAdapter(mAdapter);  //如果一开始就设置，会导致 先出现  空数据 再加载数据
            isFirstReresh = false;
            isReReresh = false;
            //当数据不满一页的时候，取消加载更多
            if (isLoadmore) {

                //延时操作，避免 lastitem为 -1
//                mRecyclerview.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        int lastItem = ((LinearLayoutManager) mRecyclerview.getLayoutManager()).findLastCompletelyVisibleItemPosition();
//                        int all_item = mAdapter.requestData().size() + mAdapter.getHeaderLayoutCount() + mAdapter.getFooterLayoutCount();
//
//                        if (lastItem == all_item - 1)   //表示数据不满一页
//                            mAdapter.setEnableLoadMore(false);
//                        else {
//                            mAdapter.notifyDataSetChanged();
//                            mAdapter.setEnableLoadMore(true);
//                            mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//                                @Override
//                                public void onLoadMoreRequested() {
//
//                                    if (!mSwipeRefreshLayout.isRefreshing()) {
//                                        isLoadmoreIng = true;
//                                        requestData();
//                                    } else {
//
//                                    }
//
//                                }
//                            }, mRecyclerview);
//
//                        }
//
//                    }
//                }, 300);

                mAdapter.disableLoadMoreIfNotFullPage(mRecyclerview);
            }
        }

        if (isLoadmore && isLoadmoreIng) {
            isLoadmoreIng = false;
            if (mData == null || mData.size() == 0)
                mAdapter.loadMoreEnd();
            else
                mAdapter.loadMoreComplete();
        }

    }


    public void loadError(String mes) {

        if (isFirstReresh) {

            toggleShowError(true, "加载失败,点击重新加载", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleShowLoading(true, "加载中……");
                    requestData();
                }
            });
        } else {
            toggleShowLoading(false);
            ToastUtils.showToast(mContext, mes, Toast.LENGTH_SHORT);
            if (mSwipeRefreshLayout != null)
                mSwipeRefreshLayout.setRefreshing(false);

            if (isLoadmoreIng) {
                isLoadmoreIng = false;
                mAdapter.loadMoreFail();
            }
        }

    }

    //===================================================我是分隔符=========================================================

}
