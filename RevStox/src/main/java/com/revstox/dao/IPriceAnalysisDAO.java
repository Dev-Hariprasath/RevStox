package com.revstox.dao;

public interface IPriceAnalysisDAO {
     void calculateDailyVolatility(String company_name);
     void calculateDailyPriceChange(String company_name);
     void getPriceGaps(String company_name);
     void getMovingAverages(String company_name);
}
