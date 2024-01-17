package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance(){
        if( configLoader == null ){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getUserId(){
        String prop = properties.getProperty("user_id");
        if(prop != null)
            return prop;
        else
            throw new RuntimeException("Property User_Id is not specified in the config.properties file");
    }
}
