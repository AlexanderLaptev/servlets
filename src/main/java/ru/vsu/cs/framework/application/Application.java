package ru.vsu.cs.framework.application;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import ru.vsu.cs.framework.application.configuration.Configuration;
import ru.vsu.cs.framework.controller.Controller;
import ru.vsu.cs.framework.logging.DisabledLogger;
import ru.vsu.cs.framework.logging.Logger;

public class Application {
    private final Configuration configuration;
    private final Logger logger;

    private Application(Configuration configuration, Logger logger) {
        this.configuration = configuration;
        this.logger = logger;
    }

    public static class Builder {
        private Configuration configuration;
        private Logger logger = DisabledLogger.INSTANCE;

        public Application build() {
            return new Application(configuration, logger);
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
            return this;
        }
    }

    public void run(String[] commandLineArgs) {
        logger.info("Starting application");

        var tomcat = new Tomcat();
        var serverPort = configuration.getIntProperty("server.port", 8080);
        tomcat.setPort(serverPort);
        logger.info("Initialized embedded Tomcat server on port " + serverPort);

        try {
            tomcat.start();
            logger.info("Tomcat started");
        } catch (LifecycleException e) {
            logger.error("Lifecycle exception:", e);
        }
    }
}
