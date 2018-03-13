package com.appbaselib.mode.address;

import java.util.List;

/**
 * Description: TODO
 * Created by lbw on 2017/7/13 0013.
 */

public class Province {

    private String province;

    private List<City> city;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    public String[] getCitiesArray() {
        if (city == null || city.size() == 0) {
            return null;
        }

        int len = city.size();
        String[] cities = new String[len];

        for (int i = 0; i < len; i++) {
            cities[i] = this.city.get(i).getName();
        }

        return cities;
    }
}
