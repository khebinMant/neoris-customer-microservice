package com.neoris.customer.common.web;

import lombok.Builder;
import lombok.Getter;

/**
 * Vo for response api
 *
 * @author bcueva
 * @version 1.0
 * @param <T> Type data response
 */
@Builder
@Getter
public class Response<T> {
    /**
     * Data to response
     */
    private T data;

    /**
     * Message to response
     */
    private String message;
}
