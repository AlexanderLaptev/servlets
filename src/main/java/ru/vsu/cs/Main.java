package ru.vsu.cs;

import ru.vsu.cs.category.CategoryController;
import ru.vsu.cs.framework.application.Application;
import ru.vsu.cs.framework.application.configuration.ClasspathPropertiesConfiguration;
import ru.vsu.cs.framework.logging.ConsoleLogger;
import ru.vsu.cs.framework.serialization.GsonBodySerializer;

public class Main {
    public static void main(String[] args) {
        var config = new ClasspathPropertiesConfiguration("config.properties");
        var logger = new ConsoleLogger();
        var application = new Application.Builder()
                .withConfiguration(config)
                .withLogger(logger)
                .setFallbackSerializer(new GsonBodySerializer())
                .addController(new CategoryController())
                .build();
        application.run(args);
    }
}
