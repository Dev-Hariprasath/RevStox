package com.revstox.controller;

import com.revstox.service.ComparativeMetricsService;

import java.util.Scanner;

public class ComparativeController {
//    private final ComparativeMetricsService comparativeService = new ComparativeMetricsService();
//    private final Scanner scanner = new Scanner(System.in);
//
//    public ComparativeController() {
//
//    }
//
//    public void showComparativeMenu() {
//        while (true) {
//            System.out.println("\n--- Comparative Analysis ---");
//            System.out.println("1. Compare Stock Metrics");
//            System.out.println("2. Rank Stocks");
//            System.out.println("3. Summarize Portfolio");
//            System.out.println("4. Volume-Price Correlation");
//            System.out.println("0. Back");
//            System.out.print("Choose an option: ");
//
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // consume leftover newline
//
//            switch (choice) {
//                case 1 -> comparativeService.compareStockMetrics();
//                case 2 -> comparativeService.rankStocks();
//                case 3 -> comparativeService.summarizePortfolio();
//                case 4 -> comparativeService.analyzeVolumePriceCorrelation();
//                case 0 -> { return; }
//                default -> System.out.println("Invalid choice.");
//            }
//        }
//    }
//
//    private String prompt(String message) {
//        System.out.print(message);
//        return scanner.nextLine().trim();
//    }
}
