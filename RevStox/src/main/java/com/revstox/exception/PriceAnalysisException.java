package com.revstox.exception;

public class PriceAnalysisException extends RuntimeException {
    public PriceAnalysisException(String message) {
        super(message);
    }

    public PriceAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }
}

