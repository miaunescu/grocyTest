package Cucumber.Implementation;

import Cucumber.ScenarioContext;
import Enums.DataKeys;
import io.cucumber.java.en.Given;
import com.endava.grocy.client.BaseClient;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

public class RequestGenerator extends BaseClient {
    public ScenarioContext scenarioContext = new ScenarioContext();

    @Given("basic config for the request is created with base path: {word}")
    public void getBasicRestConfigAndSaveToContext(String basePath) {
        scenarioContext
                .setContext(
                        DataKeys.BASIC_REST_CONFIG,
                        getBasicRestConfig(basePath));

    }

    @When("user navigates to {word} endpoint")
    public void createRequestWithGivenEndpoint(String endpoint) {
        RequestSpecification req = (RequestSpecification) scenarioContext.getContext(DataKeys.BASIC_REST_CONFIG);
        Response response = req.get(endpoint);

        scenarioContext
                .setContext(
                        DataKeys.RESPONSE_CODE,
                        response);
    }

    @Then("user receives code {int}")
    public void checkResponseCode(Integer expectedResponseCode) {
        Response actualResponse = (Response) scenarioContext.
                getContext(DataKeys.RESPONSE_CODE);

        Assertions.assertEquals(expectedResponseCode, actualResponse.statusCode());
    }

    @Then("user is on page {string}")
    public void checkCurrentPage() {
        /*TODO: Implement selenium functionality after Alex's merge
            that contains the selenium setup
            Alternatively, we can use the Jsoup library
            to scrape the html from the response*/

    }
}
