package util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class LocalUtil {

    private SAXReader reader;
    private Document document;
    private Element root;

    public LocalUtil(){
        reader = new SAXReader();
        try {
            InputStream is = LocalUtil.class.getClassLoader().getResourceAsStream("LocList.xml");
            document = reader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        root = document.getRootElement();
    }

    private List<Element> getProvinceList(){
        Iterator it = root.elementIterator();
        List<Element> provinces = new ArrayList<Element>();
        Element element = null;
        while (it.hasNext()){
            element = (Element) it.next();
            if("中国".equals(element.attributeValue("Name"))){
                provinces = element.elements();
                break;
            }
        }
        return provinces;
    }

    public int getProvinceCode(String province){
        List<Element> provinces = this.getProvinceList();
        Element element = null;
        for(Element e:provinces){
            if(province.equals(e.attributeValue("Name"))){
                element = e;
                break;
            }
        }
        int code = Integer.parseInt(element.attributeValue("Code"));
        return code;
    }

    private List<Element> getCityList(String province){
        List<Element> provinces = this.getProvinceList();
        List<Element> cities = new ArrayList<Element>();
        if(provinces==null || provinces.size()==0)
            return cities;
        for(Element element:provinces){
            if(province.equals(element.attributeValue("Name"))){
                cities = element.elements();
                break;
            }
        }
        return cities;
    }

    public int getCityCode(String province ,String city){
        List<Element> cities = this.getCityList(province);
        if(city==""){
            return 0;
        }
        Element element = null;
        for(Element e:cities){
            if(city.equals(e.attributeValue("Name"))){
                element = e;
                break;
            }
        }
        int code = Integer.parseInt(element.attributeValue("Code"));
        return code;
    }

    public int getCode(String province, String city){
        int code = this.getProvinceCode(province)*100000 + this.getCityCode(province,city)*1000;
        return code;
    }
}
