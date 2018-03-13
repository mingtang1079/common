package com.appbaselib.mode.address;

import java.util.List;

/**
 * Description: TODO
 * Created by lbw on 2017/7/13 0013.
 */

public class City {

    private String name;

    private List<Area> area;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Area> getArea() {
        return area;
    }

    public void setArea(List<Area> area) {
        this.area = area;
    }

    public String[] getAreasArray() {
        if (area == null || area.size() == 0) {
            return null;
        }

        int len = area.size();
        String[] areas = new String[len];

        for (int i = 0; i < len; i++) {
            areas[i] = this.area.get(i).getName();
        }

        return areas;
    }
}
