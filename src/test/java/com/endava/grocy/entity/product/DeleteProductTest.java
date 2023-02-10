package com.endava.grocy.entity.product;

import com.endava.grocy.TestBaseClass;
import com.endava.grocy.model.EntityType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class DeleteProductTest extends TestBaseClass {

    @Test
    public void shouldDeleteProduct() {
        //GIVEN
//        Location location = dataProvider.getLocation();
//        Response createLocationResponse = entityClient.createEntity(EntityType.LOCATION, location);
//        createLocationResponse.then().statusCode(HttpStatus.SC_OK);
//        Integer locationId = createLocationResponse.jsonPath().getInt("created_object_id");
//
//        QuantityUnit quantityUnit = dataProvider.getQuantityUnit();
//        Response createQuantityUnitResponse = entityClient.createEntity(EntityType.QUANTITY_UNIT, quantityUnit);
//        createQuantityUnitResponse.then().statusCode(HttpStatus.SC_OK);
//        Integer quantityUnitId = createQuantityUnitResponse.jsonPath().getInt("created_object_id");
//
//        Product product = dataProvider.getProduct(locationId, quantityUnitId, quantityUnitId);
//
//        Response createProductResponse = entityClient.createEntity(EntityType.PRODUCT, product);
//        createProductResponse.then().statusCode(HttpStatus.SC_OK);
//        Integer id = createProductResponse.body().jsonPath().getInt("created_object_id");

        grocyFixture.createEntity(EntityType.LOCATION)
                .createEntity(EntityType.QUANTITY_UNIT)
                .createEntity(EntityType.PRODUCT);
        Integer id = grocyFixture.getProduct().getId();
        //WHEN
        Response response = entityClient.deleteRequest(EntityType.PRODUCT, id);

        //THEN
        response.then().statusCode(HttpStatus.SC_NO_CONTENT);

    }
}
