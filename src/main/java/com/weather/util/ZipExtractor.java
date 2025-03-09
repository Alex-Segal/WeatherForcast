package com.weather.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZipExtractor {
    public static String extractZipCode(String text) {
        Pattern pattern = Pattern.compile("\\b\\d{5}(?:-\\d{4})?\\b");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}
