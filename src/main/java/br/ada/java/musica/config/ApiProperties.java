package br.ada.java.musica.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiProperties {
    private static Properties prop;
    private static String API_KEY;
    private static String API_HOST;

    private static void init(){
        try (InputStream input = new FileInputStream("src/main/resources/api.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            API_KEY=prop.getProperty("rapidapi.key");
            API_HOST=prop.getProperty("rapidapi.host");
        } catch (IOException ex) {
               ex.printStackTrace();
        }
    }
    public static String getKey(){
        init();
        return API_KEY;
    }
    public static String getHost(){
        init();
        return API_HOST;
    }
}
