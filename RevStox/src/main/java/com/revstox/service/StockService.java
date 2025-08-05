package com.revstox.service;

import com.revstox.dao.StockDAO;

import java.sql.Date;
import java.util.Scanner;

public class StockService implements IStockService {

    private final StockDAO stockDAO;
    private final ServiceLogger logger;
    private final Scanner scanner;

    public StockService() {
        this.stockDAO = new StockDAO();
        this.logger = ServiceLogger.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public StockService(StockDAO stockDAO, ServiceLogger logger, Scanner scanner) {
        this.stockDAO = stockDAO;
        this.logger = logger;
        this.scanner = scanner;
    }

    private String prompt(String action) {
        System.out.print("Enter the company name for " + action + ": ");
        return scanner.nextLine().trim();
    }

    @Override
    public void fetchStockBySymbol() {
        String symbol = prompt("Enter stock symbol: ");
        logger.log("StockService", "fetchStockBySymbol called for symbol: " + symbol);
        stockDAO.getStockBySymbol(symbol);
    }

    @Override
    public void getHistoricalData() {
        logger.log("StockService", "getHistoricalData called");
        stockDAO.getAllStocks();
    }

    @Override
    public void getStockDataByDateRange() {
        try {
            String start = prompt("Enter the start date (yyyy-mm-dd): ");
            String end = prompt("Enter the end date (yyyy-mm-dd): ");
            Date startDate = Date.valueOf(start);
            Date endDate = Date.valueOf(end);
            logger.log("StockService", "getStockDataByDateRange called from " + start + " to " + end);
            stockDAO.getStockDataByDateRange(startDate, endDate);
        } catch (IllegalArgumentException e) {
            logger.log("StockService", "Invalid date format provided");
            System.err.println("Invalid date format. Please use yyyy-mm-dd.");
        }
    }

    @Override
    public void importFromCSV() {
        logger.log("StockService", "importFromCSV called");
        stockDAO.importFromCSV();
    }

    @Override
    public void queryStockDataWithValidation() {
        String symbol = prompt("Enter the company symbol: ");
        logger.log("StockService", "queryStockDataWithValidation called for symbol: " + symbol);
        stockDAO.queryStockDataWithValidation(symbol);
    }
}
