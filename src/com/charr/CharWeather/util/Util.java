package com.charr.CharWeather.util;

import com.charr.CharWeather.db.CoolWeatherDB;
import com.charr.CharWeather.model.CityInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2015/12/29.
 */
public class Util {

    public static synchronized boolean handleCitiesResponse(CoolWeatherDB coolWeatherDb,String response){
        DocumentBuilderFactory factory = null;
        DocumentBuilder builder = null;
        Element element = null;
        CityInfo cityInfo = null;
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(response.getBytes()));
            element = doc.getDocumentElement();
            NodeList childNodes1 = element.getChildNodes();
            for(int i=0;i<childNodes1.getLength();i++){
                Node item1 = childNodes1.item(i);
                if("c".equals(item1.getNodeName())){
                    NodeList childNodes2 = item1.getChildNodes();
                    for(int j=0;j<childNodes2.getLength();j++){
                        Node item2 = childNodes2.item(j);
                        if("d".equals(item2.getNodeName())){
                            cityInfo = new CityInfo();
                            cityInfo.setCity_code(item2.getAttributes().getNamedItem("d1").getNodeValue());
                            cityInfo.setCity_name(item2.getAttributes().getNamedItem("d2").getNodeValue());
                            cityInfo.setCity_china(item2.getAttributes().getNamedItem("d3").getNodeValue());
                            cityInfo.setProvince_name(item2.getAttributes().getNamedItem("d4").getNodeValue());
                            coolWeatherDb.saveCityInfo(cityInfo);
                        }
                    }
                }
            }
            return true;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return false;
        } catch (SAXException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
