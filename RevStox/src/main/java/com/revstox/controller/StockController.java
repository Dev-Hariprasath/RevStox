package com.revstox.controller;

import com.revstox.service.IStockService;
import com.revstox.service.StockService;

import java.util.Scanner;

public class StockController {

    private final IStockService stockService;
    private final Scanner scanner;

    public StockController() {
        this.stockService = new StockService();
        this.scanner = new Scanner(System.in);
    }

    public StockController(IStockService stockService, Scanner scanner) {
        this.stockService = stockService;
        this.scanner = scanner;
    }

    public void showStockDataMenu() {
        while (true) {
            System.out.println("\n--- Stock Data Management ---");
            System.out.println("1. Retrieve Historical Data");
            System.out.println("2. Filter Data by Date Range");
            System.out.println("3. Import from CSV");
            System.out.println("4. Query Data with Validation");
            System.out.println("5. Fetch Stock by Symbol");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> stockService.getHistoricalData();
                case 2 -> stockService.getStockDataByDateRange();
                case 3 -> stockService.importFromCSV();
                case 4 -> stockService.queryStockDataWithValidation();
                case 5 -> stockService.fetchStockBySymbol();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private int readInt() {
        while (true) {
            if (!scanner.hasNextLine()) {
                System.out.println("No input available. Exiting.");
                throw new RuntimeException("No input available.");
            }

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.print("Please enter a valid number: ");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    private String prompt(String message) {
        System.out.print(message);
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        } else {
            return "";
        }
    }
}
