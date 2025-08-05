package com.revstox.dao;

public interface IVolumeAnalysisDAO  {
    void calculateVWAP(String company_name);
    void calculateDailyTurnover(String company_name);
    void analyzeVolumeTrends(String company_name);
    void calculateDeliverableVolumePercentage(String company_name);
}
