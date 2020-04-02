package com.sj4j.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
    static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return prop;
    }
}
