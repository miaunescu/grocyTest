package com.endava.grocy.entity.shoppinglist;

import com.endava.grocy.util.EnvReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestShoppingList extends TestBaseShoppingList {

    @Test
    public void shouldShoppingListRedirect(){
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");
        driver.navigate().to("http://3.65.154.68:8089/stockoverview");
    }

    @Test
    public void shouldAddItem(){
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");

        driver.findElement(By.xpath("//a[normalize-space()='Add item']")).click();

        driver.switchTo().frame(driver.findElement(By.xpath(EnvReader.getFName())));
        String textToUpdate = "Cookies123";
        String quantityToUpdate = "2";
        String noteToUpdate = "Test123";
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(" //input[@id='product_id_text_input']")))
                .click()
                .sendKeys(textToUpdate)
                .build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//input[@id='display_amount']")))
                .click()
                .sendKeys(quantityToUpdate)
                .build().perform();
        actions.moveToElement(driver.findElement(By.xpath("//textarea[@id='note']")))
                .click()
                .sendKeys(noteToUpdate)
                .build().perform();
        //actions.moveToElement(driver.findElement(By.xpath("//button[@id='save-shoppinglist-button']"))).click();
        driver.findElement(By.xpath("//button[@id='save-shoppinglist-button']")).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
        driver.navigate().to("http://3.65.154.68:8089/stockoverview");

    }
    @Test
    public void shouldNotClearList() throws InterruptedException {
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");

        driver.findElement(By.xpath("//a[@id='clear-shopping-list']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[normalize-space()='No']")).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
        driver.navigate().to("http://3.65.154.68:8089/stockoverview");

    }
    @Test
    public void shouldClearList() throws InterruptedException {
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");
        //Switching the shopping list
        WebElement selectElement = driver.findElement(By.xpath("//select[@id='selected-shopping-list']"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("test1234");
        //Clearing the products in the changed shopping list
        driver.findElement(By.xpath("//a[@id='clear-shopping-list']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
        driver.navigate().to("http://3.65.154.68:8089/stockoverview");

    }
    @Test
    public void shouldCreateNewShoppingList() {
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");
        driver.findElement(By.xpath("//a[normalize-space()='New shopping list']")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='bootbox-body']//iframe[@class='embed-responsive']")));

        String textToUpdate = "Test_NewList";
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//input[@id='name']")))
                .click()
                .sendKeys(textToUpdate)
                .build().perform();

        driver.findElement(By.xpath("//*[@id='save-shopping-list-button']")).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
        driver.navigate().to("http://3.65.154.68:8089/stockoverview");


    }
    @Test
    public void testShoppingListChange() {
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");

        WebElement selectElement = driver.findElement(By.xpath("//select[@id='selected-shopping-list']"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("test1234");
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
        driver.navigate().to("http://3.65.154.68:8089/stockoverview");
    }

}
