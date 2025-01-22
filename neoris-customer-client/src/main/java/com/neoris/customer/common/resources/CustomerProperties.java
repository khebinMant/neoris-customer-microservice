package com.neoris.customer.common.resources;


import com.neoris.customer.common.exceptions.CustomerException;
import com.neoris.customer.common.logs.CustomerLogger;

import java.util.MissingResourceException;

/**
 * Customer properties
 *
 * @author Kevin
 * @version 1.0
 */
public class CustomerProperties {
    public static final ResourceResolver MESSAGE_RESOLVER = new ResourceResolver("com.neoris.customer.client.resources.customer");

    private CustomerProperties() {}

    /**
     * Get string
     *
     * @param key Property key
     * @return Property value
     */
    public static String getString(String key) {
        try {
            return MESSAGE_RESOLVER.getString(key);
        } catch (MissingResourceException e) {
            CustomerLogger.LOG.error("Error getting property with string value", e);
        }
        return "!%s!".formatted(key);
    }

    /**
     * Get integer property
     *
     * @param key Property key
     * @return Property value
     */
    public static Integer getInteger(String key) {
        try {
            return MESSAGE_RESOLVER.getInteger(key);
        } catch (MissingResourceException e) {
            CustomerLogger.LOG.error("Error getting property with integer value", e);
        }
        return -1;
    }

    /**
     * Get Long property
     *
     * @param key Property key
     * @return Property value
     */
    public static Long getLong(String key) {
        try {
            return MESSAGE_RESOLVER.getLong(key);
        } catch (MissingResourceException e) {
            CustomerLogger.LOG.error("Error getting property with long value", e);
        }
        return -1L;
    }

    /**
     * Get Boolean property
     *
     * @param key Property key
     * @return Property value
     * @throws CustomerException Exception
     */
    public static Boolean getBoolean(String key)  throws CustomerException {
        try {
            return MESSAGE_RESOLVER.getBoolean(key);
        } catch (MissingResourceException e) {
            throw new CustomerException("Error getting property with boolean value", e);
        }
    }

    /**
     * Get String
     *
     * @param key Property key
     * @param parameters Object
     * @return Property value
     * @throws CustomerLogger Exception
     */
    public static String getString(String key, String... parameters) {
        try {
            return MESSAGE_RESOLVER.getString(key,parameters);
        } catch (MissingResourceException e) {
            CustomerLogger.LOG.error("Error getting property with string value", e);
        }
        return "!%s!".formatted(key);
    }

}
