package com.charr.CharWeather.util;

import java.io.*;

/**
 * Created by Administrator on 2016/1/6.
 */
public class SplitTest {
    public static void main(String[] args){
        File file = new File("I:\\APK\\software.txt");
        splitTxt(file);
    }

    private static void splitTxt(File file){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            String replace;
            StringBuilder sb = new StringBuilder();
            while((line = reader.readLine()) != null){
                String[] split = line.split("Software：");
                replace = split[1].replace(" ","");
                sb.append(replace+" ");
                System.out.println(replace);
            }
            writeTxt(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeTxt(String line) throws IOException {
        File file1 = new File("I:\\APK\\software01.txt");
        if(!file1.exists()){
            file1.createNewFile();
        }
        FileWriter fw = new FileWriter(file1);
        fw.write(line);
        fw.flush();
    }
}
