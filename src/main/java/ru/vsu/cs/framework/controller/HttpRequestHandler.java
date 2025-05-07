package ru.vsu.cs.framework.controller;

public interface HttpRequestHandler {
    HttpResponse handleRequest(HttpRequest request);
}
