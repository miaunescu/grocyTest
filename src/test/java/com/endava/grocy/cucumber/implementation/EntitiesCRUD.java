package com.endava.grocy.cucumber.implementation;

import com.endava.grocy.enums.DataKeys;
import com.endava.grocy.TestBaseClass;
import com.endava.grocy.client.EntityClient;
import com.endava.grocy.model.EntityType;
import io.cucumber.java.en.When;


public class EntitiesCRUD extends TestBaseClass {

    EntityClient entityClient = new EntityClient();

    @When("User creates an entity of type {word}")
    public void createEntity(String entityType) {
        switch (entityType) {
            case "LOCATION":
                var entity = grocyFixture.createEntity(EntityType.LOCATION).getLocation();
                scenarioContext.setScenarioContext(DataKeys.RESPONSE_CODE,
                        entityClient.createRequest(EntityType.LOCATION, entity).getStatusCode());

                scenarioContext.setScenarioContext(DataKeys.LOCATION_ENTITY, entity);
                break;
            case "PRODUCT":

                scenarioContext.setScenarioContext(DataKeys.PRODUCT_ENTITY,
                        grocyFixture.createEntity(EntityType.PRODUCT).getProduct());
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

                scenarioContext.setScenarioContext(DataKeys.QUANTITY_UNIT_ENTITY,
                        grocyFixture.createEntity(EntityType.QUANTITY_UNIT).getQuantityUnit());
                break;
            default:
                throw new IllegalArgumentException("Don't know how to create " + entityType);
        }
    }
}
