package com.appbaselib.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.appbaselib.adapter.ObjectAdapter;
import com.appbaselib.adapter.StringAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pangu.appbaselibrary.R;

import java.util.List;



/**
 * Created by tangming on 2017/7/12.
 */

public class BottomDialogUtils {
    /**
     * 显示底部dialog
     *
     * @param mContext 上下文
     * @param resource view 资源文件
     */
    public static void showBottomDialog(Context mContext, @LayoutRes int resource) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        View view = LayoutInflater.from(mContext).inflate(resource, null);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
    }

    /**
     * 显示底部dialog
     *  @param mContext
     * @param mView    view
     */
    public static BottomSheetDialog showBottomDialog(Context mContext, View mView) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        bottomSheetDialog.setContentView(mView);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
        return bottomSheetDialog;
    }

    /**
     * @param mContext
     * @param mStrings             list（string类型）
     * @param mOnItemClickListener 点击事件
     */
    public static BottomSheetDialog showBottomDialog(Context mContext, List<String> mStrings, final BaseQuickAdapter.OnItemClickListener mOnItemClickListener) {
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(mContext);
        RecyclerView mRecyclerView = new RecyclerView(mContext);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        StringAdapter mStringAdapter = new StringAdapter(R.layout.item_list, mStrings);
        mStringAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mOnItemClickListener.onItemClick(adapter, view, position);
                mBottomSheetDialog.dismiss();
            }
        });
        mRecyclerView.setAdapter(mStringAdapter);
        mBottomSheetDialog.setContentView(mRecyclerView);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        return mBottomSheetDialog;
    }
    /**
     * @param mContext
     * @param mStrings             list（object类型）
     * @param mOnItemClickListener 点击事件
     */
    public static BottomSheetDialog showBottomDialog2(Context mContext, List<ObjectAdapter.Item> mStrings, final BaseQuickAdapter.OnItemClickListener mOnItemClickListener) {
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(mContext);
        RecyclerView mRecyclerView = new RecyclerView(mContext);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        ObjectAdapter mStringAdapter = new ObjectAdapter(R.layout.item_list, mStrings);
        mStringAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mOnItemClickListener.onItemClick(adapter, view, position);
                mBottomSheetDialog.dismiss();
            }
        });
        mRecyclerView.setAdapter(mStringAdapter);
        mBottomSheetDialog.setContentView(mRecyclerView);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        return mBottomSheetDialog;
    }


    public static BottomSheetDialog showBottomDialog(Context mContext, String title, List<String> mStrings, final BaseQuickAdapter.OnItemClickListener mOnItemClickListener) {
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(mContext);
        View mView = LayoutInflater.from(mContext).inflate(R.layout.view_bottom_list_with_title, null);
        RecyclerView mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview);
        TextView mTextView = (TextView) mView.findViewById(R.id.title);
        if (!TextUtils.isEmpty(title)) {
            mTextView.setText(title);
        } else
            mTextView.setVisibility(View.GONE);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mContext);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        StringAdapter mStringAdapter = new StringAdapter(R.layout.item_list, mStrings);
        mStringAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mOnItemClickListener.onItemClick(adapter, view, position);
                mBottomSheetDialog.dismiss();
            }
        });
        mRecyclerView.setAdapter(mStringAdapter);
        mBottomSheetDialog.setContentView(mView);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.setCanceledOnTouchOutside(true);
        return mBottomSheetDialog;
    }
}
