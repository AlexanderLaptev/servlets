package ru.vsu.cs.category;

import ru.vsu.cs.framework.application.Application;
import ru.vsu.cs.framework.controller.Controller;
import ru.vsu.cs.framework.controller.HttpRequest;
import ru.vsu.cs.framework.controller.HttpResponse;

public class CategoryController extends Controller {
    private final CategoryRepository categoryRepository;

    public CategoryController() {
        super("/api/v1/category");
        addGetPath("/all", this::getAll);
        categoryRepository = Application.getApplication().getBean(CategoryRepository.class);
    }

    public HttpResponse getAll(HttpRequest request) {
        return new HttpResponse(HttpResponse.OK, categoryRepository.findAll());
    }
}
