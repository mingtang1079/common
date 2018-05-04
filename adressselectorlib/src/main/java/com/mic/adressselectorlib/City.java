package com.mic.adressselectorlib;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangming on 2018/4/30.
 */

public class City implements CityInterface {


    public String id;
    public String pid;
    public String level;
    public String name;
    public ArrayList<City> childs;

    @Override
    public String getCityName() {
        return name;
    }
}
