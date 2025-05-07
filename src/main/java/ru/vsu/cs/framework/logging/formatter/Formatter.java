package ru.vsu.cs.framework.logging.formatter;

import ru.vsu.cs.framework.logging.LogLevel;

public interface Formatter {
    String format(LogLevel level, String entry, Throwable t);
}
