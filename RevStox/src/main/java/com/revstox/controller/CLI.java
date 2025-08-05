package com.revstox.controller;

import java.util.Scanner;

public class CLI {

    private final StockController stockController = new StockController();
    private final PriceAnalysisController priceController = new PriceAnalysisController();
    private final VolumeAnalysisController volumeController = new VolumeAnalysisController();
    private final ComparativeController comparativeController = new ComparativeController();
    private final Scanner scanner = new Scanner(System.in);;

    public CLI() {

    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n--- Stock Analysis Main Menu ---");
            System.out.println("1. Stock Data Management");
            System.out.println("2. Price Analysis");
            System.out.println("3. Volume Analysis");
            System.out.println("4. Comparative Analysis");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> stockController.showStockDataMenu();
                case 2 -> priceController.showPriceMenu();
                case 3 -> volumeController.showVolumeMenu();
//                case 4 -> comparativeController.showComparativeMenu();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }
}
