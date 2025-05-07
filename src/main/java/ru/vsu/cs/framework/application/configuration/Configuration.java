package ru.vsu.cs.framework.application.configuration;

public interface Configuration {
    String getProperty(String key);

    default String getProperty(String key, String defaultValue) {
        var property = getProperty(key);
        return (property != null) ? property : defaultValue;
    }

    int getIntProperty(String key, int defaultValue);

    boolean getBooleanProperty(String key, boolean defaultValue);
}
