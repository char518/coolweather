package com.charr.CharWeather.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2015/12/29.
 */
public class HttpTest {

    public static void main(String[] args){
        String address = "http://mobile.weather.com.cn/js/citylist.xml";
        String httpContent = getHttpContent(address);
        System.out.println(httpContent);
    }

    public static String getHttpContent(final String address){
        String htmlSource = null;
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(8*1000);
            InputStream inputStream = connection.getInputStream();
            byte[] data = readInputStream(inputStream);
            htmlSource = new String(data,"UTF-8");
            return htmlSource;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlSource;
    }

    private static byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = inputStream.read(buffer)) != -1){
            outStream.write(buffer,0,len);
        }
        inputStream.close();
        return outStream.toByteArray();
    }
}
