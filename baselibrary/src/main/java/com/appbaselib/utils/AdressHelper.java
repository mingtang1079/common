package com.appbaselib.utils;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.mic.adressselectorlib.AddressSelector;
import com.mic.adressselectorlib.City;
import com.mic.adressselectorlib.CityHelper;
import com.mic.adressselectorlib.OnItemClickListener;

/**
 * Created by tangming on 2018/5/1.
 */

public class AdressHelper {

    public static BottomSheetDialog showAddressSelector(Context mContext, final OnItemClickListener mOnItemClickListener) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mContext);
        AddressSelector mAddressSelector  = new AddressSelector(mContext);
        mAddressSelector.setAllCitiys(CityHelper.getInsatance().getCities(mContext));
        mAddressSelector.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClick(City mProvice, City mCity, City mCounty) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.itemClick(mProvice, mCity, mCounty);

                }
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(mAddressSelector);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();
        return bottomSheetDialog;
    }

}
