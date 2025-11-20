package com.automation.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties prop;

    public static void initConfig(){
        prop = new Properties();
        try {
            prop.load(new FileReader("src/test/resources/config/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getConfigValue(String key){
        return prop.getProperty(key);
    }

    public static void setConfigValue(String key,String value){
        prop.setProperty(key,value);
    }

    public static Object getObject(String key){
        return prop.get(key);
    }

    public static void setObject(String key,Object value){
        prop.put(key,value);
    }
}
