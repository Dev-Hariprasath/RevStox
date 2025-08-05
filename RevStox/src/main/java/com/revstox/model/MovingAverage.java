package com.revstox.model;

import java.sql.Date;

public class MovingAverage {

    private String symbol;
    private Date tradeDate;
    private double ma7;
    private double ma30;
    private double ma90;

    public MovingAverage() {
    }

    public MovingAverage(String symbol, Date tradeDate, double ma7, double ma30, double ma90) {
        this.symbol = symbol;
        this.tradeDate = tradeDate;
        this.ma7 = ma7;
        this.ma30 = ma30;
        this.ma90 = ma90;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getMa7() {
        return ma7;
    }

    public void setMa7(double ma7) {
        this.ma7 = ma7;
    }

    public double getMa30() {
        return ma30;
    }

    public void setMa30(double ma30) {
        this.ma30 = ma30;
    }

    public double getMa90() {
        return ma90;
    }

    public void setMa90(double ma90) {
        this.ma90 = ma90;
    }

    @Override
    public String toString() {
        return "MovingAverage{" +
                "symbol='" + symbol + '\'' +
                ", tradeDate=" + tradeDate +
                ", ma7=" + ma7 +
                ", ma30=" + ma30 +
                ", ma90=" + ma90 +
                '}';
    }
}
