package ru.vsu.cs;

import ru.vsu.cs.category.CategoryController;
import ru.vsu.cs.framework.application.Application;
import ru.vsu.cs.framework.logging.ConsoleLogger;

public class Main {
    public static void main(String[] args) {
        var logger = new ConsoleLogger();
        var application = new Application.Builder()
                .withLogger(logger)
                .addController(new CategoryController())
                .build();
        application.run(args);
    }
}
