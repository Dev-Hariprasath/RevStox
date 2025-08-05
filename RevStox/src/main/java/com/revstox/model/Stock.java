package com.revstox.model;

public class Stock {

    private String symbol;
    private String companyName;
    private String sector;
    private long marketCap;

    public Stock() {
    }

    public Stock(String sector, String companyName, String symbol, long marketCap) {
        this.sector = sector;
        this.companyName = companyName;
        this.symbol = symbol;
        this.marketCap = marketCap;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", sector='" + sector + '\'' +
                ", marketCap=" + marketCap +
                '}';
    }
}
