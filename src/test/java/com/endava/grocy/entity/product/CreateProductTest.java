package com.endava.grocy.entity.product;

import com.endava.grocy.TestBaseClass;
import com.endava.grocy.model.EntityType;
import com.endava.grocy.model.Product;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.notNullValue;

public class CreateProductTest extends TestBaseClass {

    @Test
    public void shouldCreateProduct() {
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

        grocyFixture.createEntity(EntityType.LOCATION)
                .createEntity(EntityType.QUANTITY_UNIT);
        Integer locationId = grocyFixture.getLocation().getId();
        Integer quantityUnitId = grocyFixture.getQuantityUnit().getId();

        Product product = dataProvider.getProduct(locationId, quantityUnitId, quantityUnitId);
        //WHEN
        Response response = entityClient.createRequest(EntityType.PRODUCT, product);
        //THEN
        //System.out.println(response.print());
        response.then().statusCode(HttpStatus.SC_OK)
                .body("created_object_id", notNullValue());
    }
}
