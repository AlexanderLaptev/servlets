package ru.vsu.cs.framework.application;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import ru.vsu.cs.framework.application.configuration.ClasspathPropertiesConfiguration;
import ru.vsu.cs.framework.application.configuration.Configuration;
import ru.vsu.cs.framework.controller.Controller;
import ru.vsu.cs.framework.controller.ControllerServlet;
import ru.vsu.cs.framework.logging.DisabledLogger;
import ru.vsu.cs.framework.logging.Logger;
import ru.vsu.cs.framework.serialization.SerializationRegistry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class Application {
    private static Application application;

    public static Application getApplication() {
        return application;
    }

    public Application() {
        if (application != null) {
            throw new IllegalStateException("Application is already initialized");
        }
        application = this;
    }

    private final Map<Class<?>, Map<String, Supplier<?>>> beans = new HashMap<>();
    private boolean isRunning = false;

    private Tomcat tomcat;
    private Configuration configuration = new ClasspathPropertiesConfiguration("config.properties");
    private Logger logger = DisabledLogger.INSTANCE;
    private Set<Controller> controllers = new HashSet<>();
    private SerializationRegistry serializationRegistry = new SerializationRegistry();

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public Application addController(Controller controller) {
        controllers.add(controller);
        return this;
    }

    public SerializationRegistry getSerializationRegistry() {
        return serializationRegistry;
    }

    public <T> Application addFactoryBean(Class<? extends T> clazz, Supplier<? extends T> factory) {
        var forClass = beans.computeIfAbsent(clazz, k -> new HashMap<>());
        forClass.put(null, factory);
        return this;
    }

    public <T> Application addFactoryBean(Class<? extends T> clazz, String name, Supplier<? extends T> factory) {
        var forClass = beans.computeIfAbsent(clazz, k -> new HashMap<>());
        forClass.put(name, factory);
        return this;
    }

    public <T> Application addSingletonBean(Class<? extends T> clazz, T bean) {
        var forClass = beans.computeIfAbsent(clazz, k -> new HashMap<>());
        forClass.put(null, () -> bean);
        return this;
    }

    public <T> Application addSingletonBean(Class<? extends T> clazz, String name, T bean) {
        var forClass = beans.computeIfAbsent(clazz, k -> new HashMap<>());
        forClass.put(name, () -> bean);
        return this;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<? extends T> clazz) {
        var forClass = beans.computeIfAbsent(clazz, k -> new HashMap<>());
        var result = forClass.get(null);
        if (result != null) { return (T) result.get(); }
        if (!forClass.isEmpty()) {
            for (var bean : forClass.values()) {
                return (T) bean.get();
            }
        }
        throw new BeanNotFoundException("Could not find bean for class " + clazz);
    }

    public Object getBean(String name) {
        for (var clazz : beans.keySet()) {
            var classBeans = beans.get(clazz);
            if (classBeans == null) { continue; }
            for (var beanName : classBeans.keySet()) {
                if (beanName.equals(name)) { return classBeans.get(beanName).get(); }
            }
        }
        throw new BeanNotFoundException("Could not find bean with name " + name);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<? extends T> clazz, String name) {
        var forClass = beans.computeIfAbsent(clazz, k -> new HashMap<>());
        return (T) forClass.get(name);
    }

    public void run(String[] commandLineArgs) {
        logger.info("Starting application");
        if (application.isRunning) {
            logger.error("Application is already running");
            return;
        }
        application.isRunning = true;

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
