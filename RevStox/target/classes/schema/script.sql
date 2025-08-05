-- Create stocks table
CREATE TABLE IF NOT EXISTS stocks (
    symbol VARCHAR(20) PRIMARY KEY,
    company_name VARCHAR(100),
    sector VARCHAR(50),
    market_cap BIGINT
);


-- Create daily_prices table
CREATE TABLE IF NOT EXISTS daily_prices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    symbol VARCHAR(50),
    trade_date DATE,
    open_price DOUBLE,
    high_price DOUBLE,
    low_price DOUBLE,
    close_price DOUBLE,
    volume BIGINT,
    turnover DOUBLE
);

-- Create stock_analytics table
CREATE TABLE IF NOT EXISTS stock_analytics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    symbol VARCHAR(20),
    date DATE,
    volatility DOUBLE,
    moving_avg_7 DOUBLE,
    moving_avg_30 DOUBLE
)