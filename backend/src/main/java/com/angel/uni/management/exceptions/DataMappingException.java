package com.angel.uni.management.exceptions;

import org.apache.coyote.BadRequestException;

public class DataMappingException extends BadRequestException {
    public DataMappingException(String message) {
        super(message);
    }
}
