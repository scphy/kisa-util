package net.scphy.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * @author scphy 2024/2/26
 **/
public class DateTimeUtils {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO8601_UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
    public static final DateTimeFormatter ISO8601_UTC_FORMATTER = DateTimeFormatter.ofPattern(ISO8601_UTC_PATTERN);

    public static String formatDate(Temporal dateTime) {
        return formatDate(dateTime, null);
    }

    public static String formatDate(Temporal dateTime, DateTimeFormatter formatter) {
        if (ObjectUtils.isEmpty(formatter)) {
            formatter = DEFAULT_FORMATTER;
        }
        return formatter.format(dateTime);
    }

    public static String formatNow() {
        return formatDate(LocalDateTime.now());
    }

    public static String formatNow(ZoneOffset zoneOffset) {
        return formatDate(LocalDateTime.now(zoneOffset));
    }

    public static String formatNow(DateTimeFormatter formatter) {
        return formatDate(LocalDateTime.now(), formatter);
    }

    public static String formatNow(ZoneOffset zoneOffset, DateTimeFormatter formatter) {
        return formatDate(LocalDateTime.now(zoneOffset), formatter);
    }

}
