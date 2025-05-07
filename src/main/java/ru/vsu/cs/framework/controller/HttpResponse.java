package ru.vsu.cs.framework.controller;

public class HttpResponse {
    public static final int OK = 200;

    private final int statusCode;

    private final Object body;

    public HttpResponse(int statusCode) {
        this.statusCode = statusCode;
        this.body = null;
    }

    public HttpResponse(int statusCode, Object body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
