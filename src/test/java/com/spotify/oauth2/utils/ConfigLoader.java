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

    public String getClientId(){
        String prop = properties.getProperty("client_id");
        if(prop != null )
            return prop;
        else
            throw new RuntimeException(" Property Client_id is not specified in the Config.properties file");
    }

    public String getClientSecret(){
        String prop = properties.getProperty("client_secret");
        if(prop != null )
            return prop;
        else
            throw new RuntimeException("Property Client_Secret is not specified in the Config.Properties file");
    }

    public String getRefreshToken(){
        String prop = properties.getProperty("refresh_token");
        if( prop != null )
            return prop;
        else
            throw new RuntimeException("Property refresh_token is not specified in the Config.properties file");
    }

    public String getGrantType(){
        String prop = properties.getProperty("grant_type");
        if( prop != null )
            return prop;
        else
            throw new RuntimeException("Property grant_type is not specified in the Config.Properties file");

    }

    public String getAccessToken(){
        String prop = properties.getProperty("access_token");
        if( prop != null )
            return prop;
        else
            throw new RuntimeException("Property access_token is not specified in the Config.Properties file");

    }
}
