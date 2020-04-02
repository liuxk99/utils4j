package com.sj4j.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class PropertiesUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void readPropertiesFile() {
        final String PROP_FILE = "app.properties";
        try {
            Properties properties = PropertiesUtils.readPropertiesFile(PROP_FILE);
//            System.out.println(properties);

            System.out.println("user: " + properties.getProperty("user"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}