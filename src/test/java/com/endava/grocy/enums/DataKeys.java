package com.endava.grocy.enums;

/**
 * This enum is required in order to save the necessary data
 * that we share across the context.
 * Feel free to add the desired keys as per the examples below.
 * You can use the defaultValue as away of describing what should be the value of the key in the scenario context
 */
public enum DataKeys {
    RESPONSE_CODE("200"),
    RESPONSE_BODY("DEFAULT"),
    BASIC_REST_CONFIG("DEFAULT"),
    LOCATION_ENTITY("LOCATION"),
    PRODUCT_ENTITY("PRODUCT"),
    CHORE_ENTITY("CHORE"),
    QUANTITY_UNIT_ENTITY("QUANTITY_UNIT");


    DataKeys(String defaultValue) {
    }
}
