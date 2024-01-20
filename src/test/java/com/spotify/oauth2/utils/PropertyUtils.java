package com.spotify.oauth2.utils;

import java.io.*;
import java.nio.Buffer;
import java.util.Properties;

public class PropertyUtils {

    public static Properties propertyLoader(String filepath){
        Properties properties = new Properties();
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        try{
            bufferedReader = new BufferedReader(new FileReader(filepath));
            try{
                properties.load(bufferedReader);
                bufferedReader.close();
            } catch (IOException e) {
                //e.printStackTrace();
                throw new RuntimeException("Failed to load properties file: " +filepath);
            }

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            throw new RuntimeException("Properties file not found at: " +filepath);
        }
        return properties;

    }
}
