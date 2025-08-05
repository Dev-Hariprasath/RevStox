package com.revstox.controller;

import com.revstox.service.PriceAnalysisService;

import java.util.Scanner;

public class PriceAnalysisController {

    private final PriceAnalysisService priceService;
    private final Scanner scanner;

    public PriceAnalysisController() {
        this.priceService = new PriceAnalysisService();
        this.scanner = new Scanner(System.in);
    }

    public PriceAnalysisController(PriceAnalysisService priceService, Scanner scanner) {
        this.priceService = priceService;
        this.scanner = scanner;
    }

    public void showPriceMenu() {
        while (true) {
            System.out.println("\n--- Price Analysis ---");
            System.out.println("1. Daily Price Volatility");
            System.out.println("2. Daily Price Change");
            System.out.println("3. Identify Price Gaps");
            System.out.println("4. Moving Averages");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> priceService.calculateDailyVolatility();
                case 2 -> priceService.calculateDailyPriceChange();
                case 3 -> priceService.getPriceGaps();
                case 4 -> priceService.getMovingAverages();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next(); // discard invalid input
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }
}
