package com.neoris.customer.config;



import com.neoris.customer.common.web.Response;
import com.neoris.customer.common.exceptions.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<Response<Object>> handleCustomerException(CustomerException ex) {
        return new ResponseEntity<>(Response.<Object>builder()
                .data(null)
                .message(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    // Manejo de excepciones genéricas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleGenericException(Exception ex) {
        return new ResponseEntity<>(Response.<Object>builder()
                .data(null)
                .message("Ocurrió un error inesperado: " + ex.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}