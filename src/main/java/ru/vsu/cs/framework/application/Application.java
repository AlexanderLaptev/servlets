package ru.vsu.cs.framework.application;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import ru.vsu.cs.framework.application.configuration.Configuration;
import ru.vsu.cs.framework.controller.Controller;
import ru.vsu.cs.framework.controller.ControllerServlet;
import ru.vsu.cs.framework.logging.DisabledLogger;
import ru.vsu.cs.framework.logging.Logger;

import java.util.HashSet;
import java.util.Set;

public class Application {
    private final Configuration configuration;
    private final Logger logger;
    private final Set<Controller> controllers;

    private Tomcat tomcat;

    private Application(Configuration configuration, Logger logger, Set<Controller> controllers) {
        this.configuration = configuration;
        this.logger = logger;
        this.controllers = controllers;
    }

    public static class Builder {
        private Configuration configuration;
        private Logger logger = DisabledLogger.INSTANCE;
        private final Set<Controller> controllers = new HashSet<>();

        public Application build() {
            return new Application(configuration, logger, controllers);
        }

        public Builder withConfiguration(Configuration configuration) {
            this.configuration = configuration;
            return this;
        }

        public Builder withLogger(Logger logger) {
            this.logger = logger;
            return this;
        }

        public Builder addController(Controller controller) {
            controllers.add(controller);
            return this;
        }
    }

    public void run(String[] commandLineArgs) {
        logger.info("Starting application");
        if (tomcat != null) {
            logger.error("Application is already running");
            return;
        }

        var applicationName = configuration.getProperty("application.name");
        if (applicationName == null) {
            logger.error("Application name is not defined");
            return;
        }

        tomcat = new Tomcat();
        var serverPort = configuration.getIntProperty("server.port", 8080);
        var hostname = configuration.getProperty("server.hostname", "localhost");
        tomcat.setPort(serverPort);
        tomcat.setHostname(hostname);
        tomcat.getConnector();
        logger.info("Initialized embedded Tomcat server on port " + serverPort);

        var contextPath = "";
        var context = tomcat.addContext(contextPath, applicationName);
        context.setSessionTimeout(30);

        for (var controller : controllers) {
            var servletName = controller.getClass().getSimpleName();
            Tomcat.addServlet(context, servletName, new ControllerServlet(controller));
            context.addServletMappingDecoded(controller.getRootPath() + "/*", servletName);
        }

        try {
            tomcat.start();
            logger.info("Tomcat is running");
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            logger.error("Lifecycle exception, stopping server", e);
        }
    }
}
