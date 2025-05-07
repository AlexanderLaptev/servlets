package ru.vsu.cs.framework.application.configuration;

public interface Configuration {
    String getProperty(String key);

    int getIntProperty(String key, int defaultValue);

    boolean getBooleanProperty(String key, boolean defaultValue);
}
