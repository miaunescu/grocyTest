package com.endava.grocy.cucumber.implementation;

import com.endava.grocy.TestBaseClass;
import com.endava.grocy.client.EntityClient;
import com.endava.grocy.enums.DataKeys;
import com.endava.grocy.model.EntityType;
import io.cucumber.java.en.When;


public class EntitiesCRUD extends TestBaseClass {


    @When("User creates an entity of type {word}")
    public void createEntity(String entityType) {
        int responseCode;
        switch (entityType) {
            case "LOCATION":
                var entity = grocyFixture.createEntity(EntityType.LOCATION);
                scenarioContext.setScenarioContext(DataKeys.LOCATION_ENTITY, entity.getLocation());
                responseCode = grocyFixture.getResponse().getStatusCode();
                scenarioContext.setScenarioContext(DataKeys.RESPONSE_CODE, responseCode);
                break;
            case "PRODUCT":
                entity = grocyFixture.createEntity(EntityType.PRODUCT);
                scenarioContext.setScenarioContext(DataKeys.PRODUCT_ENTITY, entity);
                responseCode = grocyFixture.getResponse().getStatusCode();
                scenarioContext.setScenarioContext(DataKeys.RESPONSE_CODE, responseCode);
                break;
//            case "CHORE":
//                scenarioContext.setContext(DataKeys.RESPONSE_BODY,
//                        entityClient.createEntity(EntityType.CHORE,
//                                grocyFixture.getProduct()));
//
//                scenarioContext.setContext(DataKeys.CHORE_ENTITY,
//                        grocyFixture.getProduct());
//                break;
            case "QUANTITY_UNIT":
                entity = grocyFixture.createEntity(EntityType.QUANTITY_UNIT);
                scenarioContext.setScenarioContext(DataKeys.QUANTITY_UNIT_ENTITY, entity);
                responseCode = grocyFixture.getResponse().getStatusCode();
                scenarioContext.setScenarioContext(DataKeys.RESPONSE_CODE, responseCode);
                break;
            default:
                throw new IllegalArgumentException("Don't know how to create " + entityType);
        }
    }
}
