package com.revstox.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.*;

public class LoggerConfig {


    public static Logger getLogger(String loggerName, String logFileName) {
        Logger logger = Logger.getLogger(loggerName);

        if (logger.getHandlers().length > 0) return logger;

        try {
            Files.createDirectories(Paths.get("logs"));

            FileHandler handler = new FileHandler("logs/" + logFileName, true);
            handler.setFormatter(new SimpleFormatter());
            handler.setLevel(Level.INFO);

            logger.setLevel(Level.INFO);
            logger.addHandler(handler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Failed to configure logger for " + loggerName + ": " + e.getMessage());
        }

        return logger;
    }
}
