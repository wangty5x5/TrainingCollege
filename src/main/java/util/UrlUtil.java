package util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class UrlUtil {

    private static Map<String,String> map = new HashMap<String, String>(){
        {
            put("qq.com", "http://mail.qq.com");
            put("gmail.com", "http://mail.google.com");
            put("163.com", "http://mail.163.com");
            put("sina.com", "http://mail.sina.com.cn");
            put("126.com", "http://mail.126.com");
            put("sohu.com", "http://mail.sohu.com/");
            put("yeah.net", "http://www.yeah.net/");
            put("sogou.com", "http://mail.sogou.com/");
            put("139.com", "http://mail.10086.cn/");
            put("189.com", "http://webmail16.189.cn/webmail");
            put("yahoo.com.cn", "http://mail.cn.yahoo.com/");
            put("yahoo.cn", "http://mail.cn.yahoo.com/");
        }
    };

    public static String getUrl(String mail){
        String str = mail.split("@")[1];
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(entry.getKey().equals(str))
                return entry.getValue();
        }
        return null;
    }
}
