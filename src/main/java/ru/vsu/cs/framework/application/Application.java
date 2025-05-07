package ru.vsu.cs.framework.application;

import ru.vsu.cs.framework.controller.Controller;

public class Application {
    private Application() {
    }

    public static class Builder {
        public Application build() {
            return new Application();
        }

        public Builder setConfigPath(String configPath) {
            return this;
        }

        public Builder addController(Controller controller) {
            return this;
        }
    }

    public void run(String[] commandLineArgs) {
    }
}
