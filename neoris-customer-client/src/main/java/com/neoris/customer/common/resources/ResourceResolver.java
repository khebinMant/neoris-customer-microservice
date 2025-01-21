package com.neoris.customer.common.resources;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Gets the properties of a resource
 *
 * @author Kevin
 * @version 1.0
 */
public class ResourceResolver {

    protected final ResourceBundle resourceBundle;

    /**
     * Constructor
     * @param bundleRoute Resource
     */
    public ResourceResolver(String bundleRoute) {
        String currentEnvironment = (System.getProperty("env") == null) ? "develop" : System.getProperty("env");
        resourceBundle = ResourceBundle.getBundle(bundleRoute, new Locale("dev", ""));
    }

    /**
     * Get String value
     *
     * @param key Property key
     * @param parameters Parameters
     * @return Value
     */
    public String getString(String key, String... parameters) {
        MessageFormat messageFormat = new MessageFormat(resourceBundle.getString(key));
        return messageFormat.format(parameters);
    }

    /**
     * Get Integer value
     *
     * @param key Property key
     * @return Integer value
     */
    public Integer getInteger(String key) {
        String value = getString(key);
        return Integer.valueOf(value);
    }

    /**
     * Get Long value
     *
     * @param key Property key
     * @return Long value
     */
    public Long getLong(String key) {
        String value = getString(key);
        return Long.valueOf(value);
    }

    /**
     * Get Boolean value
     *
     * @param key Property key
     * @return Boolean value
     */
    public Boolean getBoolean(String key) {
        String value = getString(key);
        return Boolean.valueOf(value);
    }
}
