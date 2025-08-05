package com.revstox.model;

public class ComparativeMetrics {

    private String symbol;
    private double avgVolume;
    private double avgVolatility;
    private double avgReturn;

    public ComparativeMetrics() {
    }

    public ComparativeMetrics(String symbol, double avgVolume, double avgVolatility, double avgReturn) {
        this.symbol = symbol;
        this.avgVolume = avgVolume;
        this.avgVolatility = avgVolatility;
        this.avgReturn = avgReturn;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getAvgVolume() {
        return avgVolume;
    }

    public void setAvgVolume(double avgVolume) {
        this.avgVolume = avgVolume;
    }

    public double getAvgVolatility() {
        return avgVolatility;
    }

    public void setAvgVolatility(double avgVolatility) {
        this.avgVolatility = avgVolatility;
    }

    public double getAvgReturn() {
        return avgReturn;
    }

    public void setAvgReturn(double avgReturn) {
        this.avgReturn = avgReturn;
    }

    @Override
    public String toString() {
        return "ComparativeMetrics{" +
                "symbol='" + symbol + '\'' +
                ", avgVolume=" + avgVolume +
                ", avgVolatility=" + avgVolatility +
                ", avgReturn=" + avgReturn +
                '}';
    }
}
