package com.revstox.load;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.revstox.util.DatabaseManager;
import com.revstox.util.LoggerConfig;
import com.revstox.util.ParseUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

public class DailyPriceImporter {

    private static final Logger logger = LoggerConfig.getLogger("DailyLogger", "daily_prices.log");
    private final DatabaseManager dbManager = new DatabaseManager();

    public DailyPriceImporter() {

    }

    public void importCSV(File csvFile) {
        String insertQuery = "INSERT INTO daily_prices " +
                "(symbol, trade_date, open_price, high_price, low_price, close_price, volume, turnover) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = dbManager.getConnection();
                PreparedStatement stmt = conn.prepareStatement(insertQuery);
                CSVReader reader = new CSVReader(new FileReader(csvFile))
        ) {
            conn.setAutoCommit(false);
            reader.readNext(); // Skip header

            String[] data;
            int count = 0;

            while ((data = reader.readNext()) != null) {
                if (data.length < 12) {
                    logger.warning("Invalid row (too few columns): " + String.join(",", data));
                    continue;
                }

                try {
                    String symbol = data[1].trim();
                    Date tradeDate = ParseUtils.parseSQLDate(data[0]);
                    double open = ParseUtils.parseDoubleSafe(data[4]);
                    double high = ParseUtils.parseDoubleSafe(data[5]);
                    double low = ParseUtils.parseDoubleSafe(data[6]);
                    double close = ParseUtils.parseDoubleSafe(data[8]);
                    long volume = ParseUtils.parseLongSafe(data[10]);
                    double turnover = ParseUtils.parseDoubleSafe(data[11]);

                    stmt.setString(1, symbol);
                    stmt.setDate(2, tradeDate);
                    stmt.setDouble(3, open);
                    stmt.setDouble(4, high);
                    stmt.setDouble(5, low);
                    stmt.setDouble(6, close);
                    stmt.setLong(7, volume);
                    stmt.setDouble(8, turnover);
                    stmt.addBatch();
                    count++;
                } catch (Exception e) {
                    logger.warning("Skipping row due to parse error: " + e.getMessage());
                }
            }

            stmt.executeBatch();
            conn.commit();
            logger.info("Successfully imported " + count + " daily price records from " + csvFile.getName());

        } catch (IOException | CsvValidationException | SQLException e) {
            logger.severe("Failed to import daily prices: " + e.getMessage());
        }
    }
}
