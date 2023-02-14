package com.endava.grocy.cucumber.implementation;

import com.endava.grocy.TestBaseClass;
import com.endava.grocy.client.BaseClient;
import com.endava.grocy.enums.DataKeys;
import com.endava.grocy.util.EnvReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.util.Map;

public class RequestGenerator extends TestBaseClass {
    private BaseClient baseClient = new BaseClient();

    @Given("basic config for the request is created with base path: {word}")
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
                        response);
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

    @Given("User logs in with the following credentials")
    public void loginToGrocy(Map<String, String> credentials) {
        String user = credentials.keySet().toString().replaceAll("\\[|\\]", "");
        String password = credentials.values().toString().replaceAll("\\[|\\]", "");
        var driver = seleniumConfig.getDriver();
        var actions = seleniumConfig.getActions();

        driver.get(EnvReader.geBaseUrl());
        driver.manage().window().maximize();
        actions = new Actions(seleniumConfig.getDriver());
        actions.moveToElement(seleniumConfig.getDriver().findElement(By.xpath("//*[@id=\"username\"]")))
                .click()
                .sendKeys(user).build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"password\"]")))
                .click()
                .sendKeys(password).build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"login-button\"]")))
                .click().build().perform();
    }
}
