package com.endava.grocy.cucumber.implementation;

import com.endava.grocy.TestBaseClass;
import com.endava.grocy.client.BaseClient;
import com.endava.grocy.enums.DataKeys;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

public class RequestGenerator extends TestBaseClass {
    private BaseClient baseClient = new BaseClient();

    @Given("Basic config for the request is created with base path: {word}")
    public void getBasicRestConfigAndSaveToContext(String basePath) {
        scenarioContext.setScenarioContext(
                DataKeys.BASIC_REST_CONFIG,
                baseClient.getBasicRestConfig(basePath));
    }

    @When("User navigates to {word} endpoint")
    public void createRequestWithGivenEndpoint(String endpoint) {
        RequestSpecification req = (RequestSpecification) scenarioContext.getScenarioContext(DataKeys.BASIC_REST_CONFIG);
        Response response = req.get(endpoint);

        scenarioContext
                .setScenarioContext(
                        DataKeys.RESPONSE_CODE,
                        response.getStatusCode());
    }

    @Then("User receives code {int}")
    public void checkResponseCode(Integer expectedResponseCode) {
        Integer actualResponse = (Integer) scenarioContext.
                getScenarioContext(DataKeys.RESPONSE_CODE);

        Assertions.assertEquals(expectedResponseCode, actualResponse);
    }

    @Then("User is on page {string}")
    public void checkCurrentPage() {
        /*TODO: Implement selenium functionality after Alex's merge
            that contains the selenium setup
            Alternatively, we can use the Jsoup library
            to scrape the html from the response*/

    }
}
