package com.wargame.config;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.InputStream;

public class ConfigManager {

    private static String url;
    private static String user;
    private static String password;
    private static boolean loaded = false;

    private static void loadConfig() {
        if (loaded) return;

        try {
            InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config.xml");
            if (input == null) {
                throw new RuntimeException("config.xml not found in resources folder!");
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(input);

            url = doc.getElementsByTagName("url").item(0).getTextContent();
            user = doc.getElementsByTagName("user").item(0).getTextContent();
            password = doc.getElementsByTagName("password").item(0).getTextContent();

            loaded = true;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration: " + e.getMessage(), e);
        }
    }

    public static String getUrl() {
        loadConfig();
        return url;
    }

    public static String getUser() {
        loadConfig();
        return user;
    }

    public static String getPassword() {
        loadConfig();
        return password;
    }
}
