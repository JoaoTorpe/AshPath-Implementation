package com.pdsc.ashpath.selenium.utils;

public class SeleniumUtils {
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            throw new RuntimeException("Sleep interrupted", e);
        }
    }
}
