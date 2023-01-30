package com.endava.grocy.client;

import com.endava.grocy.model.Entity;
import com.endava.grocy.model.EntityType;
import com.endava.grocy.util.EnvReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EntityClient extends BaseClient {

    public Response createEntity(EntityType entityType, Entity entity) {
        return
                //given().filters(new RequestLoggingFilter(), new ResponseLoggingFilter())
                getBasicRestConfig(EnvReader.getBasePath())
                        .contentType(ContentType.JSON)
                        .body(entity)
                        .pathParam("entity", entityType)
                        .post("/objects/{entity}");
    }

    public Response deleteEntityById(EntityType entityType, Integer productId) {
        return getBasicRestConfig(EnvReader.getBasePath())
                .pathParam("entity", entityType)
                .pathParam("objectId", productId)
                .delete("/objects/{entity}/{objectId}");
    }
}
