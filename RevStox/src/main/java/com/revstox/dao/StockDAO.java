package com.revstox.dao;

import com.revstox.exception.StockDataException;
import com.revstox.load.CSVImporter;
import com.revstox.util.DatabaseManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StockDAO implements IStockDAO {

    private final DatabaseManager connection;

    public StockDAO() {
        this.connection = new DatabaseManager();
    }

    @Override
    public void getStockBySymbol(String symbol) {
        String query = "SELECT * FROM stocks WHERE symbol = ? LIMIT 200";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("=== Stock Info for Symbol: " + symbol + " ===");
                    System.out.printf("Symbol: %s%nCompany Name: %s%nSector: %s%nMarket Cap: %d%n%n",
                            rs.getString("symbol"),
                            rs.getString("company_name"),
                            rs.getString("sector"),
                            rs.getLong("market_cap"));
                } else {
                    System.out.println("No stock found for symbol: " + symbol);
                }
            }
        } catch (SQLException e) {
            throw new StockDataException("Error fetching stock by symbol: " + symbol, e);
        }
    }

    @Override
    public void getAllStocks() {
        String query = "SELECT * FROM stocks LIMIT 200";
        try (Statement stmt = connection.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("=== All Stocks ===");
            System.out.printf("%-10s %-30s %-20s %-15s%n", "Symbol", "Company Name", "Sector", "Market Cap");
            System.out.println("----------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10s %-30s %-20s %-15d%n",
                        rs.getString("symbol"),
                        rs.getString("company_name"),
                        rs.getString("sector"),
                        rs.getLong("market_cap"));
            }
            System.out.println();

        } catch (SQLException e) {
            throw new StockDataException("Error fetching all stocks", e);
        }
    }

    @Override
    public void getStockDataByDateRange(Date startDate, Date endDate) {
        String query = """
                SELECT DISTINCT s.symbol, s.company_name, s.sector, s.market_cap
                FROM stocks s
                JOIN daily_prices d ON s.symbol = d.symbol
                WHERE d.trade_date BETWEEN ? AND ? LIMIT 200;
                """;
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Stocks between " + startDate + " and " + endDate + " ===");
                System.out.printf("%-10s %-30s %-20s %-15s%n", "Symbol", "Company Name", "Sector", "Market Cap");
                System.out.println("----------------------------------------------------------------------------");

                while (rs.next()) {
                    System.out.printf("%-10s %-30s %-20s %-15d%n",
                            rs.getString("symbol"),
                            rs.getString("company_name"),
                            rs.getString("sector"),
                            rs.getLong("market_cap"));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new StockDataException("Error fetching stock data by date range", e);
        }
    }

    @Override
    public void importFromCSV() {
        try {
            CSVImporter.getInstance().runImport();
            System.out.println("CSV import completed.");
        } catch (Exception e) {
            throw new StockDataException("Error during CSV import", e);
        }
    }

    @Override
    public void queryStockDataWithValidation(String symbol) {
        String query = "SELECT * FROM stocks WHERE symbol = ? LIMIT 200";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setString(1, symbol);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean found = false;
                System.out.println("=== Stock Data Validation for Symbol: " + symbol + " ===");

                while (rs.next()) {
                    found = true;
                    System.out.printf("Symbol: %s%nCompany Name: %s%nSector: %s%nMarket Cap: %d%n%n",
                            rs.getString("symbol"),
                            rs.getString("company_name"),
                            rs.getString("sector"),
                            rs.getLong("market_cap"));
                }

                if (!found) {
                    System.out.println("No stock found for symbol: " + symbol);
                }
            }
        } catch (SQLException e) {
            throw new StockDataException("Error querying stock data with validation for: " + symbol, e);
        }
    }
}
