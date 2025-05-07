package ru.vsu.cs.framework;

import java.io.IOException;
import java.util.Properties;

public class Configuration {
    public static final Configuration INSTANCE = new Configuration();

    private static final String CONFIG_FILE = "/config.properties";

    private Properties properties = new Properties();

    public void initialize() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (var inputStream = loader.getResourceAsStream(CONFIG_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }
}
