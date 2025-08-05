package com.revstox.dao;

import com.revstox.exception.PriceAnalysisException;
import com.revstox.util.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceAnalysisDAO implements IPriceAnalysisDAO {

    private final DatabaseManager connection;

    public PriceAnalysisDAO() {
        this.connection = new DatabaseManager();
    }

    @Override
    public void calculateDailyVolatility(String company_name) {
        String query = "SELECT * FROM price_volatility WHERE symbol = ? LIMIT 200";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setString(1, company_name);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Daily Volatility for " + company_name + " ===");
                System.out.printf("%-10s %-12s %-12s%n", "Symbol", "Trade Date", "Volatility");
                System.out.println("-------------------------------------------");

                while (rs.next()) {
                    System.out.printf(
                            "%-10s %-12s %-12.4f%n",
                            rs.getString("symbol"),
                            rs.getDate("trade_date"),
                            rs.getDouble("volatility")
                    );
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new PriceAnalysisException("Failed to calculate daily volatility for: " + company_name, e);
        }
    }

    @Override
    public void calculateDailyPriceChange(String company_name) {
        String query = "SELECT * FROM daily_price_change WHERE symbol = ? LIMIT 200";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setString(1, company_name);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Daily Price Change for " + company_name + " ===");
                System.out.printf("%-12s %-20s %-10s%n", "Trade Date", "Price Change (%)", "Symbol");
                System.out.println("------------------------------------------------------");

                while (rs.next()) {
                    System.out.printf(
                            "%-12s %-20.2f %-10s%n",
                            rs.getDate("trade_date"),
                            rs.getDouble("price_change_percent"),
                            rs.getString("symbol")
                    );
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new PriceAnalysisException("Failed to calculate daily price change for: " + company_name, e);
        }
    }

    @Override
    public void getPriceGaps(String company_name) {
        String query = "SELECT * FROM price_gaps WHERE symbol = ? LIMIT 200";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setString(1, company_name);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Price Gaps for " + company_name + " ===");
                System.out.printf("%-10s %-12s %-12s%n", "Symbol", "Price Gap", "Trade Date");
                System.out.println("--------------------------------------------");

                while (rs.next()) {
                    System.out.printf(
                            "%-10s %-12.2f %-12s%n",
                            rs.getString("symbol"),
                            rs.getDouble("price_gap"),
                            rs.getDate("trade_date")
                    );
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new PriceAnalysisException("Failed to get price gaps for: " + company_name, e);
        }
    }

    @Override
    public void getMovingAverages(String company_name) {
        String query = "SELECT * FROM moving_averages WHERE symbol = ? LIMIT 200";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setString(1, company_name);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Moving Averages for " + company_name + " ===");
                System.out.printf("%-10s %-12s %-10s %-10s %-10s%n", "Symbol", "Trade Date", "MA_7", "MA_30", "MA_90");
                System.out.println("----------------------------------------------------");

                while (rs.next()) {
                    System.out.printf(
                            "%-10s %-12s %-10.2f %-10.2f %-10.2f%n",
                            rs.getString("symbol"),
                            rs.getDate("trade_date"),
                            rs.getDouble("ma_7"),
                            rs.getDouble("ma_30"),
                            rs.getDouble("ma_90")
                    );
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new PriceAnalysisException("Failed to get moving averages for: " + company_name, e);
        }
    }
}
