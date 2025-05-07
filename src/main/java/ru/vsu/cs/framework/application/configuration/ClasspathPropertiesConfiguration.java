package ru.vsu.cs.framework.application.configuration;

import java.io.IOException;
import java.util.Properties;

public class ClasspathPropertiesConfiguration implements Configuration {
    private final Properties properties;

    public ClasspathPropertiesConfiguration(String fileName) {
        try (var stream = ClasspathPropertiesConfiguration.class
                .getClassLoader()
                .getResourceAsStream(fileName)
        ) {
            properties = new Properties();
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    @Override
    public int getIntProperty(String key, int defaultValue) {
        var result = properties.getProperty(key);
        return (result != null) ? Integer.parseInt(result) : defaultValue;
    }

    @Override
    public boolean getBooleanProperty(String key, boolean defaultValue) {
        var result = properties.getProperty(key);
        return (result != null) ? Boolean.parseBoolean(result) : defaultValue;
    }
}
