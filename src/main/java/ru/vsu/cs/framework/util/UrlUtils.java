package ru.vsu.cs.framework.util;

public class UrlUtils {
    public static String getResourcePath(StringBuffer url) {
        var asString = url.toString();
        var first = asString.indexOf("//");
        var second = asString.indexOf("/", first + 2);
        return asString.substring(second);
    }
}
