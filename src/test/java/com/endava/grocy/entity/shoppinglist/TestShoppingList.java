package com.endava.grocy.entity.shoppinglist;

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

public class TestShoppingList extends TestBaseShoppingList {

    @Test
    public void shouldShoppingListRedirect(){
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath("(//a[@class='nav-link discrete-link'])[2]")).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");

    }

    @Test
    public void shouldAddItem(){
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath("(//a[@class='nav-link discrete-link'])[2]")).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");

        driver.findElement(By.xpath("//*[contains(text(), 'Add item')]")).click();

        driver.findElement(By.xpath("//*[@id=\"product_id_text_input\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"product_id_text_input\"]")).getText();
        String textToUpdate = "Cheese";
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"product_id_text_input\"]")))
                .click()
                .sendKeys(textToUpdate)
                .build().perform();
        driver.findElement(By.xpath("//*[@id=\"shoppinglist-form\"]/div[2]/div/div[1]/div/ul/li[1]/a")).click();
    }
    @Test
    public void shouldClearList() {
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath("(//a[@class='nav-link discrete-link'])[2]")).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");

        driver.findElement(By.xpath("//a[@id='clear-shopping-list']")).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
    }
    @Test
    public void shouldCreateNewShoppingList() {
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath("(//a[@class='nav-link discrete-link'])[2]")).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");
        //driver.findElement(By.xpath("//*[@id=\"related-links\"]/a[1]")).click();
        //driver.findElement(By.xpath("//*[@id=\"name\"]")).click();

       // String textToUpdate = "Test_NewList";
       // Actions actions = new Actions(driver);
       // actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"name\"]")))
      //          .sendKeys(textToUpdate)
        //        .build().perform();

        //driver.findElement(By.xpath("//*[@id=\"save-shopping-list-button\"]")).click();
        //driver.switchTo().defaultContent();
        //driver.navigate().refresh();
        driver.findElement(By.cssSelector("a[href='/shoppinglist/new']")).click();
        driver.findElement(By.id("name")).sendKeys("My New Shopping List");
        driver.findElement(By.xpath("//button[text()='Save']")).click();
    }
    @Test
    public void testShoppingListChange() {
//        // Wait for the page to load
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping-list-table")));
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath("(//a[@class='nav-link discrete-link'])[2]")).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");

        // Find the input field to add a new item to the shopping list
        WebElement inputField = driver.findElement(By.id("add-to-shopping-list-input"));

        // Enter the item name
        String itemName = "Test item";
        inputField.sendKeys(itemName);

        // Click the Add button
        WebElement addButton = driver.findElement(By.id("add-to-shopping-list-button"));
        addButton.click();

//        // Wait for the item to appear in the shopping list
//        wait.until(ExpectedConditions.textToBe(By.xpath("//table[@id='shopping-list-table']/tbody/tr[last()]/td[2]"), itemName));

        // Verify that the item was added to the shopping list
        WebElement addedItem = driver.findElement(By.xpath("//table[@id='shopping-list-table']/tbody/tr[last()]/td[2]"));
        Assert.assertEquals(addedItem.getText(), itemName);
    }

}
