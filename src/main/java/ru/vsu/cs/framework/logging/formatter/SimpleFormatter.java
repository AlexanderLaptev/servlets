package ru.vsu.cs.framework.logging.formatter;

import ru.vsu.cs.framework.logging.LogLevel;
import ru.vsu.cs.framework.util.ExceptionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class SimpleFormatter implements Formatter {
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = new DateTimeFormatterBuilder()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral("][")
            .appendPattern("HH:mm:ss.SSS")
            .toFormatter();

    @Override
    public String format(LogLevel level, String entry, Throwable t) {
        var timestamp = TIMESTAMP_FORMATTER.format(LocalDateTime.now());
        var body = (t == null) ? entry : (entry + "\n" + ExceptionUtils.getStackTrace(t));
        return String.format("[%s][%s]: %s", timestamp, level, body);
    }
}
