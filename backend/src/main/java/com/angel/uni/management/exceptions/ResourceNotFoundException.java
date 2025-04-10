package com.angel.uni.management.exceptions;

/**
 *
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable e) {
        super(message, e);
    }

    public ResourceNotFoundException(Throwable e) {
        super(e);
    }
}
