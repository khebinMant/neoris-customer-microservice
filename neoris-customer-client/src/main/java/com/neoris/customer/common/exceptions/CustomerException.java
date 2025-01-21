package com.neoris.customer.common.exceptions;

import java.util.List;

/**
 * Exception for bussiness logic exceptions
 *
 * @author Kevin
 * @version 1.0
 */
public class CustomerException extends RuntimeException {


    /**
     * Constructor
     */
    public CustomerException() {
        super();
    }

    /**
     * Constructor
     *
     * @param message Message
     * @param cause Cause
     */
    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor
     *
     * @param message Message
     */
    public CustomerException(String message) {
        super(message);
    }

}
