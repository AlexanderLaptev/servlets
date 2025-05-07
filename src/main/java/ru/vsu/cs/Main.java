package ru.vsu.cs;

import ru.vsu.cs.category.CategoryController;
import ru.vsu.cs.category.CategoryRepository;
import ru.vsu.cs.category.MockCategoryRepository;
import ru.vsu.cs.framework.application.Application;
import ru.vsu.cs.framework.logging.ConsoleLogger;

public class Main {
    public static void main(String[] args) {
        var logger = new ConsoleLogger();
        var application = new Application();
        application.setLogger(logger);

        application
                .addSingletonBean(CategoryRepository.class, new MockCategoryRepository());
        application
                .addController(new CategoryController());

        application.run(args);
    }
}
