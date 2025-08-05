# 📊 RevStox - Stock Data Analysis & Import System

**RevStox** is a Java-based console application designed to import, analyze, and manage historical stock market data (daily prices, analytics, and volumes) using a JDBC-compatible relational database as MySQL. 

## 🚀 Features

### 🔄 Data Import
- Import daily stock price data from CSV files
- Import computed analytics like volatility and moving averages
- Optimized batch insertions using JDBC

### 📈 Price Analysis
- Daily price volatility
- Daily price change
- Identify price gaps between trading days
- 7-day and 30-day moving averages (via stored procedure)

### 📊 Volume Analysis
- VWAP (Volume Weighted Average Price)
- Daily turnover
- Volume trend analysis
- Deliverable volume percentage

### 📁 Stock Data Management
- Retrieve historical data
- Filter data by date range
- Fetch data by symbol
- Validate and query imported stock data

---

## 🧱 Project Structure

```
com.revstox
├── controller           # CLI controllers for user interaction
├── load                 # CSV importers for prices and analytics
├── service              # Business logic for analysis
├── util                 # Utility classes (DB, Logger, Parser)
└── Main.java            # Application entry point
```

---

## 🔑 Key Components

| Class                    | Responsibility                                   |
|--------------------------|--------------------------------------------------|
| `MainController`         | Initializes DB and launches CLI menu            |
| `DailyPriceImporter`     | Imports daily prices from CSV                   |
| `StockAnalyticsImporter` | Imports calculated volatility & moving averages |
| `PriceAnalysisService`   | Handles price-related calculations              |
| `VolumeAnalysisService`  | Handles volume-based calculations               |
| `StockService`           | Provides querying and filtering                 |
| `DatabaseManager`        | Manages DB connections                          |
| `ParseUtils`             | Safely parses CSV fields                        |
| `LoggerConfig`           | Logger setup for modular logging                |

---

## 🛠️ Technologies Used

- Java 17+
- JDBC (MySQL)
- OpenCSV (CSV parsing)
- SQL stored procedures/functions
- Maven or Gradle (optional)

---

## 📦 Prerequisites

1. **Java JDK 17+**
2. **PostgreSQL/MySQL** with:
   - Tables: `daily_prices`, `stock_analytics`
   - Procedures:
     - generate_moving_avg(symbol)
     - calc_volatility(high, low, open)
3. **OpenCSV Library**

---

## 📁 Sample CSV Format

Make sure your CSV data has a proper header and this column order:

| trade_date | symbol | ... | open | high | low | ... | close | ... | volume | turnover |
|------------|--------|-----|------|------|-----|-----|-------|-----|--------|----------|
| 2023-08-01 | INFY   | ... | 1540 | 1565 | 1530| ... | 1555  | ... | 1200000| 18600000 |

---

## 🧪 How to Run

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/revstox.git
   cd revstox
   ```

2. **Compile the code**:

   ```bash
   javac -cp "lib/*" -d out src/com/revstox/Main.java
   ```

3. **Run the application**:

   ```bash
   java -cp "out:lib/*" com.revstox.Main
   ```


## 📄 License

This project is licensed under the [MIT License](LICENSE).

---
