package com.revstox.dao;

import java.sql.Date;

public interface IStockDAO {
    void getStockBySymbol(String symbol);
    void getAllStocks();
    void getStockDataByDateRange(Date startDate, Date endDate);
    void importFromCSV();
    void queryStockDataWithValidation(String symbol);
}

