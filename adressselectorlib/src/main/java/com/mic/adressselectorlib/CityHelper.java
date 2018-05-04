package com.mic.adressselectorlib;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangming on 2018/4/30.
 */

public class CityHelper {

    private ArrayList<City> mCities;


    private static class SingletonHolder {
        private static final CityHelper instance = new CityHelper();
    }

    public static final CityHelper getInsatance() {
        return SingletonHolder.instance;
    }

    public ArrayList<City> getCities(Context mContext) {

        if (mCities != null)
            return mCities;

        ArrayList<City> mList = new ArrayList<>();
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = mContext.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open("area.json")));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONObject mJSONObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray3 = new JSONArray(mJSONObject.getJSONArray("data").toString());
            for (int i = 0; i < jsonArray3.length(); i++) {
                mList.add(new Gson().fromJson(jsonArray3.get(i).toString(), City.class));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException mE) {
            mE.printStackTrace();
        }
        mCities = mList;
        return mList;

    }

}
