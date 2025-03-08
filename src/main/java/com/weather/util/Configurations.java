package com.weather.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {

    public static Properties getProperties() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream("config.properties")) {
            props.load(resourceStream);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }
}
