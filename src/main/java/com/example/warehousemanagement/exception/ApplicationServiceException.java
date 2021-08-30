package com.example.warehousemanagement.exception;

import java.util.List;

public class ApplicationServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ApplicationServiceException(String message) {
        super(message);
    }

    public ApplicationServiceException(List<String> messages) {
        super(messages.toString());
    }
}
