package ru.vsu.cs.category;

import ru.vsu.cs.framework.controller.Controller;
import ru.vsu.cs.framework.controller.HttpRequest;
import ru.vsu.cs.framework.controller.HttpResponse;

public class CategoryController extends Controller {
    public CategoryController() {
        setRootPath("api/v1/category/");
        addPath("", this::getAll);
    }

    public HttpResponse getAll(HttpRequest request) {
        return new HttpResponse(HttpResponse.OK);
    }
}
