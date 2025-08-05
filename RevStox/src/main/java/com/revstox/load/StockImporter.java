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

public class StockImporter {
    private static final Logger logger = LoggerConfig.getLogger("AnalyticsLogger", "analytics.log");
    private final DatabaseManager dbManager = new DatabaseManager();

    public StockImporter() {
    }

    public void importCSV(File csvFile) {
        String analyticsQuery = "INSERT INTO stock_analytics (symbol, date, volatility, moving_avg_7, moving_avg_30) " +
                "VALUES (?, ?, ?, ?, ?)";
        String volatilityQuery = "SELECT calc_volatility(?, ?, ?)";
        String movingAvgProc = "{CALL generate_moving_avg(?)}";

        try (
                Connection conn = dbManager.getConnection();
                PreparedStatement analyticsStmt = conn.prepareStatement(analyticsQuery);
                PreparedStatement volStmt = conn.prepareStatement(volatilityQuery);
                CSVReader reader = new CSVReader(new FileReader(csvFile))
        ) {
            conn.setAutoCommit(false);
            reader.readNext(); // skip header
            String[] data;
            int count = 0;

            while ((data = reader.readNext()) != null) {
                if (data.length < 12) {
                    logger.warning("Invalid analytics row: " + String.join(",", data));
                    continue;
                }

                try {
                    String symbol = data[1].trim();
                    Date tradeDate = ParseUtils.parseSQLDate(data[0]);
                    double open = ParseUtils.parseDoubleSafe(data[4]);
                    double high = ParseUtils.parseDoubleSafe(data[5]);
                    double low = ParseUtils.parseDoubleSafe(data[6]);

                    // Calculate volatility using SQL function
                    double volatility = 0;
                    volStmt.setDouble(1, high);
                    volStmt.setDouble(2, low);
                    volStmt.setDouble(3, open);
                    try (ResultSet rsVol = volStmt.executeQuery()) {
                        if (rsVol.next()) {
                            volatility = rsVol.getDouble(1);
                        }
                    }

                    // Call stored procedure to get moving averages
                    double movingAvg7 = 0;
                    double movingAvg30 = 0;

                    try (CallableStatement call = conn.prepareCall(movingAvgProc)) {
                        call.setString(1, symbol);
                        if (call.execute()) {
                            try (ResultSet rs = call.getResultSet()) {
                                while (rs.next()) {
                                    if (tradeDate.equals(rs.getDate("trade_date"))) {
                                        movingAvg7 = rs.getDouble("moving_avg_7");
                                        movingAvg30 = rs.getDouble("moving_avg_30");
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    // Insert into analytics table
                    analyticsStmt.setString(1, symbol);
                    analyticsStmt.setDate(2, tradeDate);
                    analyticsStmt.setDouble(3, volatility);
                    analyticsStmt.setDouble(4, movingAvg7);
                    analyticsStmt.setDouble(5, movingAvg30);
                    analyticsStmt.addBatch();
                    count++;

                } catch (Exception e) {
                    logger.warning("Error processing row: " + e.getMessage());
                }
            }

            analyticsStmt.executeBatch();
            conn.commit();
            logger.info("Inserted " + count + " analytics records from " + csvFile.getName());

        } catch (IOException | CsvValidationException | SQLException e) {
            logger.severe("Analytics import error: " + e.getMessage());
        }
    }
}
