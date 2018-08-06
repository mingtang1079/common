package com.appbaselib.base;


import android.util.SparseBooleanArray;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangming on 2016/11/28.   加入单选 多选 / 2017 5/7 xiugai
 */

public abstract class BaseRecyclerViewAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {


    private boolean isShowMutilChoose = false;  //，默认是否打开多选开关
    private SparseBooleanArray selectedItems = new SparseBooleanArray();//记录选中的position

    public int mSinglePosition = -1;

    public BaseRecyclerViewAdapter(int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    //多布局 构造方法
    public BaseRecyclerViewAdapter() {
        super(null);
    }

    /**
     * 多选判读是否选中
     */
    public boolean isSelected(int position) {
        return getSelectedItemsKey().contains(position);
    }

    /**
     * 多选切换选中或取消选中
     */
    public void switchSelectedState(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        } else {
            selectedItems.put(position, true);
        }
        notifyItemChanged(position);
    }

    public boolean isShowMutilChoose() {
        return isShowMutilChoose;
    }

    public void setShowMutilChoose(boolean mShowMutilChoose) {
        isShowMutilChoose = mShowMutilChoose;
        if (mShowMutilChoose) {
            if (getOnItemClickListener() != null)  //原点击事件不为空的话，先保存原点击事件；
            {
                mOrigionOnItemClickListener = getOnItemClickListener();

            }
            setOnItemClickListener(mSelectOnItemClickListener);

        } else {

            if (getOnItemClickListener() != null) {//原点击事件不为空的话，先保存原点击事件；
                setOnItemClickListener(mOrigionOnItemClickListener);
            } else {
                setOnItemClickListener(null);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 清除所有选中item的标记
     */
    public void clearSelectedState() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    /**
     * 全选
     */
    public void selectAllItems() {
        clearSelectedState();
        for (int i = 0; i < getData().size(); i++) {
            selectedItems.put(i, true);
        }
        notifyDataSetChanged();
    }

    /**
     * 获取item数目
     */
    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItemsKey() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    /**
     * 多选获取选中的items
     */
    public List<T> getSelectedItems() {
        List<T> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add((T) mData.get(selectedItems.keyAt(i)));
        }
        return items;
    }


    /**
     * 单选获取选中的items
     */
    public T getSingleSelectedItems() {

        if (mSinglePosition >= 0) {
            return mData.get(mSinglePosition);

        } else
            return null;
    }


    public void setSingleChoosed(int positon) {
        mSinglePosition = positon;
        notifyDataSetChanged();
    }


    public void addData(T newData) {
        this.mData.add(newData);
        this.notifyItemInserted(this.mData.size());
    }


    OnItemClickListener mOrigionOnItemClickListener;//多选模式下，原来的点击事件 （为了多选点击事件能覆盖整个item）

    BaseQuickAdapter.OnItemClickListener mSelectOnItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            switchSelectedState(position);
        }
    };


}
