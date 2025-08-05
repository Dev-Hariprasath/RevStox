package com.revstox.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ServiceLogger {

    private static final String LOG_FILE = "logs/service.log";
    private static final ServiceLogger INSTANCE = new ServiceLogger();

    private ServiceLogger() {
        try {
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get("logs"));
        } catch (IOException e) {
            System.err.println("Failed to create log directory: " + e.getMessage());
        }
    }

    public static ServiceLogger getInstance() {
        return INSTANCE;
    }

    public synchronized void log(String serviceName, String action) {
        String timestamp = LocalDateTime.now().toString();
        String message = String.format("[%s] [%s] %s", timestamp, serviceName, action);

        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println(message);
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }
}

