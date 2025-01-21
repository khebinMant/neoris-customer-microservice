package com.neoris.customer.common.exceptions;

/**
 * Exception for when the entity not exist
 *
 * @author Kevin
 * @version 1.0
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructor
     *
     * @param message Message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
