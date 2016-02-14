package com.charr.CharWeather.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2015/12/25.
 */
public class HttpUtil {
//
//    public static void main(String[] args){
//        HttpUtil hu = new HttpUtil();
//        HttpUtil.sendHttpRequest("http://www.weather.com.cn/adat/cityinfo/101010100.html",null);
//    }

    public static void sendHttpRequest(final String address,final HttpCallBackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setRequestProperty("contentType","UTF-8");
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null){
                        response.append(line);
                    }
                    if(listener != null){
                        listener.onFinish(response.toString());
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                    if(listener != null){
                        listener.onError(e);
                    }
                }finally {
                    connection.disconnect();
                }
          }
        }).start();
    }

}