package com.revstox.model;

import java.time.LocalDate;

public class DailyPrice {

    private long id;
    private String symbol;
    private LocalDate tradeDate;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private double closePrice;
    private long volume;
    private double turnover;

    public DailyPrice() {
    }

    public DailyPrice(String symbol, LocalDate tradeDate, double highPrice, double openPrice, double lowPrice, double closePrice, long volume, double turnover) {
        this.symbol = symbol;
        this.tradeDate = tradeDate;
        this.highPrice = highPrice;
        this.openPrice = openPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
        this.turnover = turnover;
    }

    public DailyPrice(long id, String symbol, LocalDate tradeDate, double openPrice, double highPrice, double lowPrice, double closePrice, long volume, double turnover) {
        this.id = id;
        this.symbol = symbol;
        this.tradeDate = tradeDate;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
        this.turnover = turnover;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    @Override
    public String toString() {
        return "DailyPrice{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", tradeDate=" + tradeDate +
                ", openPrice=" + openPrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", closePrice=" + closePrice +
                ", volume=" + volume +
                ", turnover=" + turnover +
                '}';
    }
}
