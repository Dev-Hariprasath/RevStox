package com.revstox.controller;

import com.revstox.util.DbUtil;

public class MainController {

     CLI cli = new CLI();

    public MainController() {
    }

    public void start() {
        // Initialize or verify DB tables
        DbUtil dbUtil = new DbUtil();
        dbUtil.initializeTables();

        // Start CLI
        cli.showMainMenu();
    }
}
