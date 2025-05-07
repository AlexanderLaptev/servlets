package ru.vsu.cs.framework.logging;

import ru.vsu.cs.framework.logging.formatter.Formatter;
import ru.vsu.cs.framework.logging.formatter.SimpleFormatter;

public class ConsoleLogger implements Logger {
    private final Formatter formatter;

    public ConsoleLogger() {
        this.formatter = new SimpleFormatter();
    }

    public ConsoleLogger(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void info(String message) {
        System.out.println(formatter.format(LogLevel.INFO, message, null));
    }

    @Override
    public void error(String message) {
        System.err.println(formatter.format(LogLevel.ERROR, message, null));
    }

    @Override
    public void error(String message, Throwable t) {
        System.err.println(formatter.format(LogLevel.INFO, message, t));
    }
}
