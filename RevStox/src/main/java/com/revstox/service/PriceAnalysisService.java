package com.revstox.service;

import com.revstox.dao.PriceAnalysisDAO;

import java.util.Scanner;

public class PriceAnalysisService implements IPriceAnalysisService {

    private final PriceAnalysisDAO priceAnalysisDAO;
    private final ServiceLogger logger;
    private final Scanner scanner;

    public PriceAnalysisService() {
        this.priceAnalysisDAO = new PriceAnalysisDAO();
        this.logger = ServiceLogger.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public PriceAnalysisService(PriceAnalysisDAO priceAnalysisDAO, ServiceLogger logger, Scanner scanner) {
        this.priceAnalysisDAO = priceAnalysisDAO;
        this.logger = logger;
        this.scanner = scanner;
    }

    private String prompt(String action) {
        System.out.print("Enter the company name for " + action + ": ");
        return scanner.nextLine().trim();
    }

    @Override
    public void calculateDailyVolatility() {
        String company = prompt("Daily Volatility");
        logger.log("PriceAnalysisService", "calculateDailyVolatility called for " + company);
        priceAnalysisDAO.calculateDailyVolatility(company);
    }

    @Override
    public void calculateDailyPriceChange() {
        String company = prompt("Daily Price Change");
        logger.log("PriceAnalysisService", "calculateDailyPriceChange called for " + company);
        priceAnalysisDAO.calculateDailyPriceChange(company);
    }

    @Override
    public void getPriceGaps() {
        String company = prompt("Price Gap Analysis");
        logger.log("PriceAnalysisService", "getPriceGaps called for " + company);
        priceAnalysisDAO.getPriceGaps(company);
    }

    @Override
    public void getMovingAverages() {
        String company = prompt("Moving Average Calculation");
        logger.log("PriceAnalysisService", "getMovingAverages called for " + company);
        priceAnalysisDAO.getMovingAverages(company);
    }
}
