package com.revstox.service;

import com.revstox.dao.VolumeAnalysisDAO;
import java.util.Scanner;

public class VolumeAnalysisService implements IVolumeAnalysisService {

    private final VolumeAnalysisDAO volumeAnalysisDAO;
    private final ServiceLogger logger;
    private final Scanner scanner;

    public VolumeAnalysisService() {
        this.volumeAnalysisDAO = new VolumeAnalysisDAO();
        this.logger = ServiceLogger.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public VolumeAnalysisService(VolumeAnalysisDAO volumeAnalysisDAO, ServiceLogger logger) {
        this.volumeAnalysisDAO = volumeAnalysisDAO;
        this.logger = logger;
        this.scanner = new Scanner(System.in);
    }

    // FIXED: Don't close System.in
    private String prompt(String action) {
        System.out.print("Enter the company name for " + action + ": ");
        return scanner.nextLine().trim();
    }

    @Override
    public void calculateVWAP() {
        String companyName = prompt("VWAP Calculation");
        logger.log("VolumeAnalysisService", "calculateVWAP called for: " + companyName);
        volumeAnalysisDAO.calculateVWAP(companyName);
    }

    @Override
    public void calculateDailyTurnover() {
        String companyName = prompt("Daily Turnover Calculation");
        logger.log("VolumeAnalysisService", "calculateDailyTurnover called for: " + companyName);
        volumeAnalysisDAO.calculateDailyTurnover(companyName);
    }

    @Override
    public void analyzeVolumeTrends() {
        String companyName = prompt("Volume Trends Analysis");
        logger.log("VolumeAnalysisService", "analyzeVolumeTrends called for: " + companyName);
        volumeAnalysisDAO.analyzeVolumeTrends(companyName);
    }

    @Override
    public void calculateDeliverableVolumePercentage() {
        String companyName = prompt("Deliverable Volume Percentage Calculation");
        logger.log("VolumeAnalysisService", "calculateDeliverableVolumePercentage called for: " + companyName);
        volumeAnalysisDAO.calculateDeliverableVolumePercentage(companyName);
    }
}
