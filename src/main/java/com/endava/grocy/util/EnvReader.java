package com.endava.grocy.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvReader {
    private static final Properties properties = new Properties();

    static {
        String env = System.getProperty("env");
        InputStream resourceAsStream = EnvReader.class.getClassLoader().getResourceAsStream("env/" + env + ".properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException("Can't read property file, ", e);
        }
    }

    public static String getBaseUri() {
        return properties.getProperty("baseUri");
    }

    public static int getPort() {
        return Integer.parseInt(properties.getProperty("port"));
    }

    public static String getBasePath() {
        return properties.getProperty("basePath");
    }

    public static String getAPIKey() {
        return properties.getProperty("api.key");
    }


    public static String getDBUrl() {
        return properties.getProperty("db.url ");
    }

    public static String getDBUsername() {
        return properties.getProperty("db.username ");
    }

    public static String getDBPassword() {
        return properties.getProperty("db.password ");
    }

    public static String geBaseUrl() { return properties.getProperty("baseUrl"); }

    public static String getBatteriesOverview() { return properties.getProperty("batteriesOverview"); }

    public static String getJournalButton() { return properties.getProperty("journalButton"); }

    public static String getSaveButton() { return properties.getProperty("saveButton"); }

    public static String getEditNameBattery() { return properties.getProperty("editNameBattery"); }

    public static String getDescriptionBattery() { return properties.getProperty("descriptionBattery"); }

    public static String getUsedInBattery() { return properties.getProperty("usedInBattery"); }

    public static String getChargeInterval() { return properties.getProperty("chargeInterval"); }

    public static String getTile() { return properties.getProperty("title"); }

    public static String getAddButton() { return properties.getProperty("addButton"); }

    public static String getActiveCheck() { return properties.getProperty("activeCheck"); }

    public static String getFrameName() { return properties.getProperty("frameName"); }

    public static String getEditBattery() { return properties.getProperty("editBattery"); }

    public static String getThreeDots() { return properties.getProperty("threeDots"); }

    public static String getNameBattery() { return properties.getProperty("nameBattery"); }
}
