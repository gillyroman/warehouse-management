package com.example.warehousemanagement.exception;


import com.example.warehousemanagement.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class WarehouseManagementExceptionHandler extends ResponseEntityExceptionHandler {

    ErrorResponse errorResponse;

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception exception) {
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorResponse> handleArgumentMismatchException(
            MethodArgumentTypeMismatchException exception) {
        errorResponse = new ErrorResponse();
        errorResponse.setMessage("Invalid data received for " + exception.getName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ErrorResponse> handleValidationException(
            ValidationException exception) {
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApplicationServiceException.class)
    public final ResponseEntity<ErrorResponse> handleIOExceptions(ApplicationServiceException exception) {
        errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
