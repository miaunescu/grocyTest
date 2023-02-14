package com.endava.grocy.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Chore {
    //TODO: change the properties to match the CHORE object form json
    @JsonProperty("location_id")
    private Integer locationId;
    @JsonProperty("min_stock_amount")
    private Integer minStockAmount;
    @JsonProperty("qu_id_purchase")
    private Integer quantityPurchaseId;
    @JsonProperty("qu_id_stock")
    private Integer quantityStockId;
    @JsonProperty("qu_factor_purchase_to_stock")
    private Double quantityFactorPurchaseToStock;
}
