package com.endava.grocy.cucumber.implementation.ui;

import com.endava.grocy.SeleniumConfig;
import com.endava.grocy.TestBaseClass;
import com.endava.grocy.cucumber.ScenarioContext;
import com.endava.grocy.pages.StockOverviewPage;
import com.endava.grocy.util.EnvReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class UiSteps extends TestBaseClass {
    SoftAssert softAssert = new SoftAssert();
    StockOverviewPage stockOverviewPage;

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

    @When("User navigates to page {string}")
    public void navigateToPage(String page) {
        String baseUriWithPort = EnvReader.getBaseUri() + ":" + EnvReader.getPort() + "/";
        var driver = seleniumConfig.getDriver();
        driver.navigate().to(baseUriWithPort + page);

    }

    @Then("User is on {string} page")
    public void checkPage(String page) {
        var driver = seleniumConfig.getDriver();
        String expectedUrl = EnvReader.getBaseUri() + ":" + EnvReader.getPort() + "/" + page;
        String actualUrl = driver.getCurrentUrl();


        softAssert.assertEquals(actualUrl, expectedUrl);
        softAssert.assertAll();
    }

    @When("User closes the browser")
    public void closeBrowser() {
        seleniumConfig.tearDown();
    }

//    @When("User waits for {int} second(s)")
//    public void wait(Integer seconds)  {
//        seleniumConfig.getDriver().wait();
//    }

    @Then("Information displayed about the current stock are the same as the ones in db")
        public void checkDisplayedInfoCurrentStock(){
        var driver = seleniumConfig.getDriver();
        stockOverviewPage = new StockOverviewPage(driver);
        String displayedValue = stockOverviewPage.pageTitle.getText();
        //retrieve info about the current stock with corresponding api call http://3.65.154.68:8089/api/stock
        String dbValue = "test";

        softAssert.assertEquals(displayedValue,dbValue);
        softAssert.assertAll();



    }

}
