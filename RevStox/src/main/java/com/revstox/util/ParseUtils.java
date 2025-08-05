package com.revstox.util;



import java.sql.Date;

public class ParseUtils {

    public static Date parseSQLDate(String str) {
        try {
            String cleaned = str.trim();
            if (!cleaned.matches("\\d{4}-\\d{2}-\\d{2}")) {
                throw new IllegalArgumentException("Invalid date format: " + cleaned);
            }
            return Date.valueOf(cleaned);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date input: " + str, e);
        }
    }

    public static double parseDoubleSafe(String input) {
        if (input == null || input.trim().isEmpty()) return 0.0;
        return Double.parseDouble(input.replace(",", "").trim());
    }

    public static long parseLongSafe(String input) {
        if (input == null || input.trim().isEmpty()) return 0;
        return Long.parseLong(input.replace(",", "").trim());
    }
}

