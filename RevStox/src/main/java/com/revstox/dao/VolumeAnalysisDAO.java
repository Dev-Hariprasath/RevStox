package com.revstox.dao;

import com.revstox.exception.VolumeAnalysisException;
import com.revstox.util.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VolumeAnalysisDAO implements IVolumeAnalysisDAO {

    private final DatabaseManager connection;

    public VolumeAnalysisDAO() {
        this.connection = new DatabaseManager();
    }

    @Override
    public void calculateVWAP(String company_name) {
        String query = "SELECT * FROM vwap WHERE symbol = ? LIMIT 200";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setString(1, company_name);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== VWAP for " + company_name + " ===");
                System.out.printf("%-12s %-10s %-10s%n", "Trade Date", "Symbol", "VWAP");
                System.out.println("---------------------------------------");

                while (rs.next()) {
                    System.out.printf(
                            "%-12s %-10s %-10.2f%n",
                            rs.getDate("trade_date"),
                            rs.getString("symbol"),
                            rs.getDouble("vwap")
                    );
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new VolumeAnalysisException("Error calculating VWAP for: " + company_name, e);
        }
    }

    @Override
    public void calculateDailyTurnover(String company_name) {
        String query = "SELECT * FROM company_turnover_view LIMIT 200;";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("=== Daily Turnover (First 200 Records from View) ===");
            System.out.printf("%-12s %-20s%n", "Trade Date", "Turnover (₹ Cr)");
            System.out.println("------------------------------------------");

            while (rs.next()) {
                System.out.printf(
                        "%-12s %-20.2f%n",
                        rs.getDate("trade_date"),
                        rs.getDouble("turnover_crores")
                );
            }
            System.out.println();

        } catch (SQLException e) {
            throw new VolumeAnalysisException("Error retrieving turnover data", e);
        }
    }

    @Override
    public void analyzeVolumeTrends(String company_name) {
        String query = "SELECT * FROM volume_trends WHERE symbol = ? LIMIT 200;";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setString(1, company_name);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Volume Trends for " + company_name + " ===");
                System.out.printf("%-10s %-12s %-12s %-15s %-15s%n", "Symbol", "Trade Date", "Volume", "Avg Vol (7)", "Avg Vol (30)");
                System.out.println("--------------------------------------------------------------------------");

                while (rs.next()) {
                    System.out.printf(
                            "%-10s %-12s %-12d %-15s %-15s%n",
                            rs.getString("symbol"),
                            rs.getDate("trade_date"),
                            rs.getLong("volume"),
                            rs.getBigDecimal("avg_vol_7"),
                            rs.getBigDecimal("avg_vol_30")
                    );
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new VolumeAnalysisException("Error analyzing volume trends for: " + company_name, e);
        }
    }

    @Override
    public void calculateDeliverableVolumePercentage(String company_name) {
        String query = "SELECT * FROM company_deliverable_ratio_view WHERE company_name = ? LIMIT 200;";
        try (PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setString(1, company_name);
            try (ResultSet rs = stmt.executeQuery()) {
                System.out.println("=== Deliverable Volume Ratio for " + company_name + " ===");
                System.out.printf("%-12s %-25s%n", "Trade Date", "Deliverable Ratio (%)");
                System.out.println("---------------------------------------------");

                while (rs.next()) {
                    System.out.printf(
                            "%-12s %-25s%n",
                            rs.getDate("trade_date"),
                            rs.getString("deliverable_ratio_percent")
                    );
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new VolumeAnalysisException("Error calculating deliverable volume percentage for: " + company_name, e);
        }
    }
}
