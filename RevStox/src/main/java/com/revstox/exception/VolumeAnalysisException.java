package com.revstox.exception;

public class VolumeAnalysisException extends RuntimeException {
    public VolumeAnalysisException(String message) {
        super(message);
    }

    public VolumeAnalysisException(String message, Throwable cause) {
        super(message, cause);
    }
}
