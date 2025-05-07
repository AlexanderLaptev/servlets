package ru.vsu.cs.framework.controller;

import java.util.HashMap;
import java.util.Map;

public class Controller {
    private final String rootPath;

    private final Map<String, HttpRequestHandler> getHandlers = new HashMap<>();

    public Controller(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void addGetPath(String path, HttpRequestHandler requestHandler) {
        if (getHandlers.get(path) != null) {
            throw new IllegalStateException("A GET handler is already defined for path " + path);
        }
        getHandlers.put(path, requestHandler);
    }

    public Map<String, HttpRequestHandler> getGetHandlers() {
        return getHandlers;
    }
}
