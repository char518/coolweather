package com.charr.CharWeather.util;

/**
 * Created by Administrator on 2015/12/29.
 */
public interface HttpCallBackListener {
    void onFinish(String response);
    void onError(Exception e);
}
