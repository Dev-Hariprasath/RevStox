package com.revstox.service;

public interface IPriceAnalysisService {
     void calculateDailyVolatility();
     void calculateDailyPriceChange();
     void getPriceGaps();
     void getMovingAverages();
}

