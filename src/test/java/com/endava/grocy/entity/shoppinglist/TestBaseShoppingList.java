package com.endava.grocy.entity.shoppinglist;

import com.endava.grocy.util.EnvReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBaseShoppingList {

    protected WebDriver driver;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(EnvReader.geBaseUrl());
        driver.manage().window().maximize();

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"username\"]")))
                .click()
                .sendKeys("admin").build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"password\"]")))
                .click()
                .sendKeys("admin").build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"login-button\"]")))
                .click().build().perform();
    }

    @AfterTest
    public void tearDown() {driver.quit();};

}
