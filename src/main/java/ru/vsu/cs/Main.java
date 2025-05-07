package ru.vsu.cs;

import ru.vsu.cs.category.CategoryController;
import ru.vsu.cs.framework.application.Application;

public class Main {
    public static void main(String[] args) {
        var application = new Application.Builder()
                .addController(new CategoryController())
                .build();
        application.run(args);
    }
}
