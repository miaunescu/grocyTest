package com.endava.grocy.entity.shoppinglist;

import com.endava.grocy.util.EnvReader;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.util.concurrent.TimeUnit;
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
        String noteToUpdate = RandomStringUtils.randomAlphanumeric(15);
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
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[normalize-space()='Yes']")));
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

        String textToUpdate = RandomStringUtils.randomAlphanumeric(15);
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
    @Test
    public void testSearchBar(){
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");
        String textToSearch = "fanta";
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//input[@id='search']")))
                .click()
                .sendKeys(textToSearch)
                .build().perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@id='shoppinglistitem-372-row']//td[@class='product-name-cell cursor-link sorting_2']")));
        driver.close();

    }
    @Test
    public void testEditListName(){
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");
        String nameToChange = "shoppingListNewName";
        WebElement selectElement = driver.findElement(By.xpath("//select[@id='selected-shopping-list']"));
        Select select = new Select(selectElement);
        select.selectByIndex(3);
        driver.findElement(By.xpath("//a[normalize-space()='Edit shopping list']")).click();
        driver.switchTo().frame(driver.findElement(By.xpath(EnvReader.getFName())));
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//input[@id='name']")))
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("A")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(nameToChange)
                .build().perform();
        driver.findElement(By.xpath("//button[@id='save-shopping-list-button']")).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();

    }
    @Test
    public void shouldNotDeleteShoppingList(){
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");
        WebElement selectElement = driver.findElement(By.xpath("//select[@id='selected-shopping-list']"));
        Select select = new Select(selectElement);
        select.selectByIndex(4);
        driver.findElement(By.xpath("//a[@id='delete-selected-shopping-list']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='No']")).click();
        driver.navigate().refresh();

    }
    @Test
    public void shouldDeleteShoppingList() throws InterruptedException{
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");
        WebElement selectElement = driver.findElement(By.xpath("//select[@id='selected-shopping-list']"));
        Select select = new Select(selectElement);
        select.selectByIndex(4);
        driver.findElement(By.xpath("//a[@id='delete-selected-shopping-list']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[normalize-space()='Yes']")).click();
        driver.navigate().refresh();

    }
    @Test
    public void markItemDone(){
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");
        driver.findElement(By.xpath("(//a[@class='btn btn-success btn-sm order-listitem-button'])[1]")).click();
        driver.navigate().refresh();
    }
    @Test
    public void deleteItem(){
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");
        driver.findElement(By.xpath("(//i[@class='fa-solid fa-trash'])[1]")).click();
        driver.navigate().refresh();
    }
    @Test
    public void addItemToStock(){
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, "http://3.65.154.68:8089/stockoverview");

        driver.findElement(By.xpath(EnvReader.getShoppingListOverview())).click();
        String urlShoppingListOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlShoppingListOverview, "http://3.65.154.68:8089/shoppinglist");
        driver.findElement(By.xpath("(//i[contains(@class,'fa-solid fa-box')])[4]")).click();
        driver.navigate().refresh();
    }
}
