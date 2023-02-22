package com.endava.grocy.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends Entity {

    @JsonProperty("id")
    private Integer productId;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("product_group_id")
    private String productGroupId;

    @JsonProperty("active")
    private Integer isProductActive;

    @JsonProperty("location_id")
    private Long locationId;

    @JsonProperty("qu_id_purchase")
    private Long quantityPurchaseId;

    @JsonProperty("qu_id_stock")
    private Long quantityStockId;

    @JsonProperty("qu_factor_purchase_to_stock")
    private Double quantityFactorPurchaseToStock;

    @JsonProperty("min_stock_amount")
    private Integer minStockAmount;

    @JsonProperty("default_best_before_days")
    private Integer defaultBestBeforeDays;

    @JsonProperty("default_best_before_days_after_open")
    private Integer defaultBestBeforeDaysAfterOpe;

    @JsonProperty("default_best_before_days_after_freezing")
    private Integer defaultBestBeforeDaysAfterFreezing;

    @JsonProperty("default_best_before_days_after_thawing")
    private Integer defaultBestBeforeDaysAfterThawing;

    @JsonProperty("picture_file_name")
    private String productPictureFileNme;

    @JsonProperty("enable_tare_weight_handling")
    private Integer enableTareWeightHandling;

    @JsonProperty("tare_weight")
    private Float tareWeight;

    @JsonProperty("not_check_stock_fulfillment_for_recipes")
    private Integer notCheckStockFulfillmentForRecipes;

    @JsonProperty("parent_product_id")
    private String parentProductId;

    @JsonProperty("calories")
    private String productCalories;

    @JsonProperty("cumulate_min_stock_amount_of_sub_products")
    private Integer cumulateMinStockAmountOfSubProducts;

    @JsonProperty("due_type")
    private Integer dueType;

    @JsonProperty("quick_consume_amount")
    private Float quickConsumeAmount;

    @JsonProperty("hide_on_stock_overview")
    private Integer hideOnStockOverview;

    @JsonProperty("row_created_timestamp")
    private String rowCreatedTimestamp;
}