package ru.vsu.cs.framework;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Configuration.INSTANCE.initialize();
        System.out.println(Configuration.INSTANCE.getString("database.url"));
//        SqlDatabase.INSTANCE.initialize();
    }
}
