package xyz.sangsik.blog.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter {
    public static String convertDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTimeFormatter.format(localDateTime);
    }
}
