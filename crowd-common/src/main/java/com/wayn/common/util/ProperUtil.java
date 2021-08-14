package com.wayn.common.util;

import java.io.IOException;
import java.util.Properties;

public class ProperUtil {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(ProperUtil.class.getClassLoader().getResourceAsStream("proj-common.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

}
