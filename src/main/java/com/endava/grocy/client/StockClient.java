package com.endava.grocy.client;

import com.endava.grocy.model.Stock;
import com.endava.grocy.util.EnvReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StockClient extends BaseClient {

    public Response addStock(Long productId, Stock stock) {
        return getBasicRestConfig(EnvReader.getBasePath())
                .contentType(ContentType.JSON)
                .body(stock)
                .pathParam("productId", productId)
                .post("/stock/products/{productId}/add");
    }
}
