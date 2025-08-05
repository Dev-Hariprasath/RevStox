package com.revstox.load;

import java.io.File;

public class CSVImporter {

    private static volatile CSVImporter instance;

    private final StockImporter stockImporter;
    private final DailyPriceImporter dailyPriceImporter;
    private final StockAnalyticsImporter stockAnalyticsImporter;

    private static final String IMPORT_DIR = "D:\\RevStox\\src\\main\\resources\\data";

    // Private constructor for Singleton
    private CSVImporter() {
        this.stockImporter = new StockImporter();
        this.dailyPriceImporter = new DailyPriceImporter();
        this.stockAnalyticsImporter = new StockAnalyticsImporter();
    }

    public static CSVImporter getInstance() {
        if (instance == null) {
            synchronized (CSVImporter.class) {
                if (instance == null) {
                    instance = new CSVImporter();
                }
            }
        }
        return instance;
    }

    public void runImport() {
        File folder = new File(IMPORT_DIR);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

        if (files != null && files.length > 0) {
            for (File file : files) {
                try {
                    dailyPriceImporter.importCSV(file);
                    stockImporter.importCSV(file);
                    stockAnalyticsImporter.importAnalytics(file);
                } catch (Exception e) {
                    System.err.println("Error importing file " + file.getName() + ": " + e.getMessage());
                }
            }
        } else {
            System.out.println("No CSV files found in directory: " + IMPORT_DIR);
        }
    }
}
