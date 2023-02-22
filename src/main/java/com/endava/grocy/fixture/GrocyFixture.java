package com.endava.grocy.fixture;

import com.endava.grocy.client.EntityClient;
import com.endava.grocy.data.DataProvider;
import com.endava.grocy.model.*;
import io.restassured.response.Response;
import lombok.Getter;
import org.apache.http.HttpStatus;

public class GrocyFixture {

    private EntityClient entityClient = new EntityClient();
    private DataProvider dataProvider = new DataProvider();

    @Getter
    private Location location;
    @Getter
    private QuantityUnit quantityUnit;
    @Getter
    private Product product;

    public GrocyFixture createEntity(EntityType entityType) {

        Entity entity;
        switch (entityType) {
            case LOCATION: {
                location = dataProvider.getLocation();
                entity = location;
                break;
            }
            case QUANTITY_UNIT: {
                quantityUnit = dataProvider.getQuantityUnit();
                entity = quantityUnit;
                break;
            }
            case PRODUCT: {
                if (location == null) {
                    throw new IllegalArgumentException("Please create a location first!!!");
                }
                product = dataProvider.getProduct(location.getId(), quantityUnit.getId(), quantityUnit.getId());
                entity = product;
                break;
            }
//            case CHORE: {
//                //TODO: Create Chore class
//
//                if (location == null) {
//                    throw new IllegalArgumentException("Please create a location first!!!");
//                }
//                product = dataProvider.getProduct(location.getId(), quantityUnit.getId(), quantityUnit.getId());
//                entity = chore;
//                break;
//            }
            default: {
                throw new IllegalArgumentException("Don't know how to create " + entityType);
            }
        }

        Response response = entityClient.createRequest(entityType, entity);
        response.then().statusCode(HttpStatus.SC_OK);
        long id = response.jsonPath().getLong("created_object_id");
        entity.setId(id);
        return this;
    }
}
