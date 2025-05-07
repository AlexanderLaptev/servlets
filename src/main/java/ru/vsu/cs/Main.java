package ru.vsu.cs;

import ru.vsu.cs.category.CategoryController;
import ru.vsu.cs.framework.application.Application;
import ru.vsu.cs.framework.application.configuration.ClasspathPropertiesConfiguration;

public class Main {
    public static void main(String[] args) {
        var config = new ClasspathPropertiesConfiguration("config.properties");
        var application = new Application.Builder()
                .withConfiguration(config)
                .addController(new CategoryController())
                .build();
        application.run(args);
    }
}
