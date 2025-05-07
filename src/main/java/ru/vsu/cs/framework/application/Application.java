package ru.vsu.cs.framework.application;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import ru.vsu.cs.framework.application.configuration.Configuration;
import ru.vsu.cs.framework.controller.Controller;

public class Application {
    private final Configuration configuration;

    private Application(Configuration configuration) {
        this.configuration = configuration;
    }

    public static class Builder {
        private Configuration configuration;

        public Application build() {
            return new Application(configuration);
        }

        public Builder withConfiguration(Configuration configuration) {
            this.configuration = configuration;
            return this;
        }

        public Builder addController(Controller controller) {
            return this;
        }
    }

    public void run(String[] commandLineArgs) {
        var tomcat = new Tomcat();
        var serverPort = configuration.getIntProperty("server.port", 8080);
        tomcat.setPort(serverPort);

        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}
