package com.charr.CharWeather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/12/23.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper{

    private static final String CREATE_CITY_CODE = "create table Citycode ("
            +"id integer primary key autoincrement,"
            +"city_code text,"
            +"city_name text,"
            +"province_name text)";

    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_CITY_CODE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
