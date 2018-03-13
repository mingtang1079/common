package com.appbaselib.adapter;

import com.appbaselib.base.BaseRecyclerViewAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pangu.appbaselibrary.R;

import java.util.List;

/**
 * Created by tangming on 2017/7/12.  对于底部弹窗 选择的list 通用adapter
 */

public class StringAdapter extends BaseRecyclerViewAdapter<String> {
    public StringAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.name,item);
    }
}
