package cn.wsd.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * Sample: PropertiesUtil.getValue("filename.properties", "key")
 */

public class PropertiesUtil {
    private static Properties getProperties(String properties) {
        try {
            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(properties);
            Properties p = new Properties();
            p.load(in);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static String getValue(String properties,String key){
        Properties p = getProperties(properties);
        return p.getProperty(key);
    }
}
