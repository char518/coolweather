package com.charr.CharWeather.model;

/**
 * Created by Administrator on 2015/12/23.
 */
public class CityInfo {
    private int id;
    private String city_code;
    private String city_name;
    private String city_china;
    private String province_name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCity_code() {
        return city_code;
    }
    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProvince_name() {
        return province_name;
    }
    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_china() {
        return city_china;
    }

    public void setCity_china(String city_china) {
        this.city_china = city_china;
    }
}
