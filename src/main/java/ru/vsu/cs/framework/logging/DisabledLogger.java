package ru.vsu.cs.framework.logging;

public class DisabledLogger implements Logger {
    public static DisabledLogger INSTANCE = new DisabledLogger();

    private DisabledLogger() { }

    @Override
    public void info(String message) { }

    @Override
    public void error(String message) { }

    @Override
    public void error(String message, Throwable t) { }
}
