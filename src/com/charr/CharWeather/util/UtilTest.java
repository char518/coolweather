package com.charr.CharWeather.util;

import com.charr.CharWeather.model.CityInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/31.
 */
public class UtilTest {

    public static void main(String[] args){
       handleCities("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
               "<xml><c c1=\"0\"><d d1=\"101010100\" d2=\"北京\" d3=\"beijing\" d4=\"北京\"/><d d1=\"101010200\" d2=\"海淀\" d3=\"haidian\" d4=\"北京\"/><d d1=\"101010300\" d2=\"朝阳\" d3=\"chaoyang\" d4=\"北京\"/><d d1=\"101010400\" d2=\"顺义\" d3=\"shunyi\" d4=\"北京\"/><d d1=\"101010500\" d2=\"怀柔\" d3=\"huairou\" d4=\"北京\"/><d d1=\"101010600\" d2=\"通州\" d3=\"tongzhou\" d4=\"北京\"/><d d1=\"101010700\" d2=\"昌平\" d3=\"changping\" d4=\"北京\"/><d d1=\"101010800\" d2=\"延庆\" d3=\"yanqing\" d4=\"北京\"/><d d1=\"101010900\" d2=\"丰台\" d3=\"fengtai\" d4=\"北京\"/><d d1=\"101011000\" d2=\"石景山\" d3=\"shijingshan\" d4=\"北京\"/><d d1=\"101011100\" d2=\"大兴\" d3=\"daxing\" d4=\"北京\"/><d d1=\"101011200\" d2=\"房山\" d3=\"fangshan\" d4=\"北京\"/><d d1=\"101011300\" d2=\"密云\" d3=\"miyun\" d4=\"北京\"/><d d1=\"101011400\" d2=\"门头沟\" d3=\"mentougou\" d4=\"北京\"/><d d1=\"101011500\" d2=\"平谷\" d3=\"pinggu\" d4=\"北京\"/><d d1=\"101020100\" d2=\"上海\" d3=\"shanghai\" d4=\"上海\"/><d d1=\"101020200\" d2=\"闵行\" d3=\"minhang\" d4=\"上海\"/><d d1=\"101020300\" d2=\"宝山\" d3=\"baoshan\" d4=\"上海\"/><d d1=\"101020500\" d2=\"嘉定\" d3=\"jiading\" d4=\"上海\"/><d d1=\"101020600\" d2=\"南汇\" d3=\"nanhui\" d4=\"上海\"/><d d1=\"101020700\" d2=\"金山\" d3=\"jinshan\" d4=\"上海\"/><d d1=\"101020800\" d2=\"青浦\" d3=\"qingpu\" d4=\"上海\"/><d d1=\"101020900\" d2=\"松江\" d3=\"songjiang\" d4=\"上海\"/><d d1=\"101021000\" d2=\"奉贤\" d3=\"fengxian\" d4=\"上海\"/><d d1=\"101021100\" d2=\"崇明\" d3=\"chongming\" d4=\"上海\"/><d d1=\"101021200\" d2=\"徐家汇\" d3=\"xujiahui\" d4=\"上海\"/><d d1=\"101021300\" d2=\"浦东\" d3=\"pudong\" d4=\"上海\"/></c></xml>");
    }

    public static boolean handleCities(String response){
        Element element = null;
        DocumentBuilder builder = null;
        DocumentBuilderFactory factory = null;
        List<CityInfo> list = null;
        CityInfo cityInfo = null;
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(response.getBytes()));
            element = doc.getDocumentElement();
            System.out.println(element.getNodeName());
            NodeList childNodes = element.getChildNodes();
            list = new ArrayList<>();
            for(int i=0;i<childNodes.getLength();i++){
                cityInfo = new CityInfo();
                Node item = childNodes.item(i);
                System.out.println(item.getNodeName());
                if("c".equals(item.getNodeName())){
                    NodeList nodes = item.getChildNodes();
                    for(int j=0;j<nodes.getLength();j++){
                        Node item1 = nodes.item(j);
                        cityInfo.setCity_code(item1.getAttributes().getNamedItem("d1").getNodeValue());
                        cityInfo.setCity_name(item1.getAttributes().getNamedItem("d2").getNodeValue());
                        cityInfo.setCity_china(item1.getAttributes().getNamedItem("d3").getNodeValue());
                        cityInfo.setProvince_name(item1.getAttributes().getNamedItem("d4").getNodeValue());
                        System.out.println(cityInfo.getCity_code()+"===="+cityInfo.getCity_name()+"===="+cityInfo.getCity_china()+"===="+cityInfo.getProvince_name());
                    }
                }
                list.add(cityInfo);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
