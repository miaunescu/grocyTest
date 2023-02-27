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
    @Getter
    private Response response;

    public GrocyFixture createEntity(EntityType entityType) {

        Entity entity;
        switch (entityType) {
            case LOCATION: {
                location = dataProvider.getLocation();
                entity = location;
                response = entityClient.createRequest(entityType, entity);
                entity.setId(response.jsonPath().getLong("created_object_id"));
                break;
            }
            case QUANTITY_UNIT: {
                quantityUnit = dataProvider.getQuantityUnit();
                entity = quantityUnit;
                entity.setId(entityClient.createRequest(entityType, entity)
                        .jsonPath().getLong("created_object_id"));
                break;
            }
            case PRODUCT: {
                if (location == null) {
                    this.createEntity(EntityType.LOCATION);
                }
                if (quantityUnit == null) {
                    this.createEntity(EntityType.QUANTITY_UNIT);
                }

                product = dataProvider.getProduct(location.getId(), quantityUnit.getId(), quantityUnit.getId());
                entity = product;
                entity.setId(entityClient.createRequest(entityType, entity)
                        .jsonPath().getLong("created_object_id"));
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
        return this;
    }
}
