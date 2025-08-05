package com.revstox.controller;

import com.revstox.service.VolumeAnalysisService;

import java.util.Scanner;

public class VolumeAnalysisController {

    private final VolumeAnalysisService volumeService;
    private final Scanner scanner;

    public VolumeAnalysisController() {
        this.volumeService = new VolumeAnalysisService();
        this.scanner = new Scanner(System.in);
    }


    public void showVolumeMenu() {
        while (true) {
            System.out.println("\n--- Volume Analysis ---");
            System.out.println("1. VWAP");
            System.out.println("2. Daily Turnover");
            System.out.println("3. Volume Trends");
            System.out.println("4. Deliverable Volume %");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> volumeService.calculateVWAP();
                case 2 -> volumeService.calculateDailyTurnover();
                case 3 -> volumeService.analyzeVolumeTrends();
                case 4 -> volumeService.calculateDeliverableVolumePercentage();
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
