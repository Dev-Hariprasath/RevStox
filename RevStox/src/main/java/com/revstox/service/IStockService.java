package com.revstox.service;


public interface IStockService {
    void fetchStockBySymbol();
    void getHistoricalData();
    void getStockDataByDateRange();
    void importFromCSV();
    void queryStockDataWithValidation();


}
