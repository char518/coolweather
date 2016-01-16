package com.charr.CharWeather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.charr.CharWeather.model.CityInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/24.
 */
public class CoolWeatherDB {
    public static final String DB_NAME = "cool_weather";
    public static final int VERSION = 1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    private CoolWeatherDB(Context context) {
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public static synchronized CoolWeatherDB getInstance(Context context) {
        if (coolWeatherDB != null) {
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }

    public void saveCityInfo(CityInfo cityInfo) {
        if (cityInfo != null) {
            ContentValues values = new ContentValues();
            values.put("city_code", cityInfo.getCity_code());
            values.put("city_china", cityInfo.getCity_china());
            values.put("city_name", cityInfo.getCity_name());
            values.put("province_name", cityInfo.getProvince_name());
            db.insert("Citycode", null, values);
        }
    }

    public List<CityInfo> loadCityInfo(String cityName) {
        ArrayList<CityInfo> list = new ArrayList<CityInfo>();
        Cursor cursor = db.query("Citycode", null, "city_name = ?", new String[]{cityName}, null, null, null);
        if(cursor.moveToNext()){
            CityInfo cityInfo = new CityInfo();
            cityInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
            cityInfo.setCity_code(cursor.getString(cursor.getColumnIndex("city_code")));
            cityInfo.setCity_china(cursor.getString(cursor.getColumnIndex("city_china")));
            cityInfo.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
            cityInfo.setProvince_name(cursor.getString(cursor.getColumnIndex("province_name")));
            list.add(cityInfo);
        }
        return list;
    }

    public List<CityInfo> loadCityInfo() {
        ArrayList<CityInfo> list = new ArrayList<CityInfo>();
        Cursor cursor = db.query("Citycode", null,null,null, null, null, null);
        if(cursor.moveToNext()){
            CityInfo cityInfo = new CityInfo();
            cityInfo.setId(cursor.getInt(cursor.getColumnIndex("id")));
            cityInfo.setCity_code(cursor.getString(cursor.getColumnIndex("city_code")));
            cityInfo.setCity_china(cursor.getString(cursor.getColumnIndex("city_china")));
            cityInfo.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
            cityInfo.setProvince_name(cursor.getString(cursor.getColumnIndex("province_name")));
            list.add(cityInfo);
        }
        return list;
    }
}
