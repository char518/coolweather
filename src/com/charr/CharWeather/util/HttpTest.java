package com.charr.CharWeather.util;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2015/12/29.
 */
public class HttpTest {
    public static final String appKey = "66c6e4ddf8eeb14a86ac08d5dbb3aabf";
    public static final String CHARSET = "UTF-8";
    public static void main(String[] args){
        String str = getHttpContent("上海");
        System.out.println(str);
    }

    public static String getHttpContent(final String cityName) {
        String result = null;
        String url = "http://op.juhe.cn/onebox/weather/query";
        HashMap<String, Object> params = new HashMap<String,Object>();
        params.put("cityname",cityName);
        params.put("key",appKey);
        params.put("dtype","");
        result = getContent(url,params,"GET");
        return result;
    }

    private static String getContent(String url, HashMap<String, Object> params, String method) {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        StringBuilder sb = new StringBuilder();
        if(method == null || method.equals("GET")){
            url = url + "?" + urlencode(params);
        }
        try {
            URL strUrl = new URL(url);
            conn = (HttpURLConnection) strUrl.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setConnectTimeout(3*1000);
            conn.setReadTimeout(3*1000);
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if(params != null && method.equals("POST")){
                try(DataOutputStream out = new DataOutputStream(conn.getOutputStream())){
                    out.writeBytes(urlencode(params));
                }
            }
            InputStream inputStream = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream,CHARSET));
            String strRead = null;
            while(null != (strRead=reader.readLine())){
                sb.append(strRead);
            }
            rs = sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null){
                conn.disconnect();
            }
        }
        return rs;
    }

    private static String urlencode(HashMap<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Object> i : params.entrySet()){
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
