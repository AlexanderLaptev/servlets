package ru.vsu.cs.framework.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.vsu.cs.framework.util.UrlUtils;

import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    private final Controller controller;

    public ControllerServlet(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var path = getRelativeMapping(UrlUtils.getResourcePath(req.getRequestURL()));

        var handlers = controller.getGetHandlers();
        var handler = handlers.get(path);
        if (handler == null) {
            resp.sendError(HttpResponse.NOT_FOUND);
            return;
        }

        var response = handler.handleRequest(new HttpRequest());
        resp.setStatus(response.getStatusCode());
    }

    private String getRelativeMapping(String resourcePath) {
        var result = resourcePath.substring(controller.getRootPath().length());
        if (result.endsWith("/")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
