package com.revstox.util;

import java.sql.*;
import java.nio.file.*;
import java.util.stream.Stream;

public class DbUtil {

    DatabaseManager databaseManager = new DatabaseManager();

    public void initializeTables() {
        try (Connection conn = databaseManager.getConnection()) {
            checkAndCreateTable(conn, "stocks");
            checkAndCreateTable(conn, "daily_prices");
            checkAndCreateTable(conn, "stock_analytics");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkAndCreateTable(Connection conn, String tableName) throws Exception {
        if (tableExists(conn, tableName)) {
            System.out.println("Table '" + tableName + "' already exists.");
        } else {
            executeSqlScript(conn, "D:\\RevStox\\src\\main\\resources\\schema\\script.sql", tableName);
            System.out.println("Table '" + tableName + "' created.");
        }
    }

    private boolean tableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData dbm = conn.getMetaData();
        try (ResultSet rs = dbm.getTables(null, null, tableName, new String[]{"TABLE"})) {
            return rs.next();
        }
    }

    private void executeSqlScript(Connection conn, String filePath, String tableName) throws Exception {
        StringBuilder sql = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(line -> sql.append(line).append("\n"));
        }

        String[] commands = sql.toString().split(";");
        try (Statement stmt = conn.createStatement()) {
            for (String command : commands) {
                if (command.toLowerCase().contains("create table") &&
                        command.toLowerCase().contains(tableName.toLowerCase())) {
                    stmt.execute(command);
                }
            }
        }
    }
}
