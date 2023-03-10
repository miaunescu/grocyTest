package com.endava.grocy.client;

import com.endava.grocy.filters.AuthenticationFilter;
import com.endava.grocy.filters.LogFilter;
import com.endava.grocy.util.EnvReader;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseClient {

    public RequestSpecification getBasicRestConfig(String basePath) {
        return given().filters(new AuthenticationFilter(), new LogFilter())
                .baseUri(EnvReader.getBaseUri())
                .port(EnvReader.getPort())
                .basePath(basePath);
    }
}
