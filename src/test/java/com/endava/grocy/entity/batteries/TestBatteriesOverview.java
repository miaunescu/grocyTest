package com.endava.grocy.entity.batteries;


import com.endava.grocy.util.EnvReader;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;


public class TestBatteriesOverview extends TestBaseBatteriesOverview {


    @Test
    public void shouldJournalRedirect() {

        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, EnvReader.getStockOverviewLink());

        driver.findElement(By.xpath(EnvReader.getBatteriesOverview())).click();
        String urlBatteriesOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlBatteriesOverview, EnvReader.getBatteriesOverviewLink());

        driver.findElement(By.xpath(EnvReader.getJournalButton())).click();
        String urlJournal = driver.getCurrentUrl();
        Assert.assertEquals(urlJournal, EnvReader.getJournalRedirect());
    }

    @Test
    public void shouldEditNameOfBattery() {

        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, EnvReader.getStockOverviewLink());

        driver.findElement(By.xpath(EnvReader.getBatteriesOverview())).click();
        String urlBatteriesOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlBatteriesOverview, EnvReader.getBatteriesOverviewLink());

        driver.findElement(By.xpath(EnvReader.getThreeDots())).click();
        driver.findElement(By.cssSelector(EnvReader.getEditBattery())).click();

        driver.switchTo().frame(driver.findElement(By.className(EnvReader.getFrameName())));
        String title = "Edit battery";
        Assert.assertEquals(title, driver.findElement(By.cssSelector(EnvReader.getTile())).getText());

        driver.findElement(By.xpath(EnvReader.getEditNameBattery())).click();

        String textToUpdate = RandomStringUtils.randomAlphanumeric(8);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getEditNameBattery())))
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("A")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(textToUpdate)
                .build().perform();

        driver.findElement(By.xpath(EnvReader.getSaveButton())).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();

        String nameAfterUpdate = driver.findElement(By.xpath(EnvReader.getNameBattery())).getText();
        Assert.assertEquals(textToUpdate, nameAfterUpdate);

        //Renaming in Baterie_auto

        String nameInitial = "Baterie_auto";
        driver.findElement(By.xpath(EnvReader.getThreeDots())).click();
        driver.findElement(By.cssSelector(EnvReader.getEditBattery())).click();

        driver.switchTo().frame(driver.findElement(By.className(EnvReader.getFrameName())));
        driver.findElement(By.xpath(EnvReader.getEditNameBattery())).click();
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getEditNameBattery())))
                .keyDown(Keys.CONTROL)
                .sendKeys("A")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(nameInitial)
                .build().perform();

        driver.findElement(By.xpath(EnvReader.getSaveButton())).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();

        String nameAfterUpdate2 = driver.findElement(By.xpath(EnvReader.getNameBattery())).getText();
        Assert.assertEquals(nameAfterUpdate2, nameInitial);

    }

    @Test
    public void shouldDisableAndEnableBattery() {

        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, EnvReader.getStockOverviewLink());

        driver.findElement(By.xpath(EnvReader.getBatteriesOverview())).click();
        String urlBatteriesOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlBatteriesOverview, EnvReader.getBatteriesOverviewLink());

        driver.findElement(By.xpath(EnvReader.getThreeDots())).click();
        driver.findElement(By.cssSelector(EnvReader.getEditBattery())).click();

        driver.switchTo().frame(driver.findElement(By.className(EnvReader.getFrameName())));
        String title = "Edit battery";
        Assert.assertEquals(title, driver.findElement(By.cssSelector(EnvReader.getTile())).getText());

        driver.findElement(By.xpath(EnvReader.getActiveCheck())).click();
        driver.findElement(By.xpath(EnvReader.getSaveButton())).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();

        int nameBattery = driver.findElements(By.xpath(EnvReader.getNameBattery())).size();
        Assert.assertEquals(nameBattery, 0);

        driver.navigate().to(EnvReader.getBatteriesPageLink());
        Assert.assertEquals(driver.findElements(By.xpath(EnvReader.getBatteryDisable())).size(), 0);
        driver.findElement(By.xpath(EnvReader.getShowDisableBatteriesButton())).click();
        boolean nameBatteryEnable = driver.findElement(By.xpath(EnvReader.getBatteryDisable())).isDisplayed();
        Assert.assertTrue(nameBatteryEnable);

        //Make enable the battery

        driver.findElement(By.xpath(EnvReader.getEditBatteryDisable())).click();
        driver.switchTo().frame(driver.findElement(By.className(EnvReader.getFrameName())));
        Assert.assertEquals(title, driver.findElement(By.cssSelector(EnvReader.getTile())).getText());

        driver.findElement(By.xpath(EnvReader.getActiveCheck())).click();
        driver.findElement(By.xpath(EnvReader.getSaveButton())).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();

        driver.findElement(By.xpath(EnvReader.getBatteriesOverview())).click();
        boolean nameBatteryDisable = driver.findElement(By.xpath(EnvReader.getBatteryDisable())).isDisplayed();
        Assert.assertTrue(nameBatteryDisable);

    }

    @Test
    public void shouldAddBattery() {

        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, EnvReader.getStockOverviewLink());

        driver.navigate().to(EnvReader.getBatteriesPageLink());
        driver.findElement(By.xpath(EnvReader.getAddButton())).click();
        driver.switchTo().frame(driver.findElement(By.className(EnvReader.getFrameName())));
        String title = "Create battery";
        Assert.assertEquals(title, driver.findElement(By.cssSelector(EnvReader.getTile())).getText());

        driver.findElement(By.xpath(EnvReader.getEditNameBattery())).click();
        String nameOfTheBattery = RandomStringUtils.randomAlphanumeric(51);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getEditNameBattery())))
                .click()
                .sendKeys(nameOfTheBattery)
                .build().perform();

        String descriptionOfTheBattery = RandomStringUtils.randomAlphanumeric(52);
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getDescriptionBattery())))
                .click()
                .sendKeys(descriptionOfTheBattery)
                .build().perform();

        String usedInOfTheBattery = RandomStringUtils.randomAlphanumeric(52);
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getUsedInBattery())))
                .click()
                .sendKeys(usedInOfTheBattery)
                .build().perform();

        Random random = new Random();
        int number = random.nextInt(10);
        String cycleInterval = String.format("%d", number);

        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getChargeInterval())))
                .click()
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(cycleInterval)
                .build().perform();

        driver.findElement(By.xpath(EnvReader.getSaveButton())).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
    }

    @Test
    public void shouldDeleteBattery() {

        //Add the battery
        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, EnvReader.getStockOverviewLink());

        driver.navigate().to(EnvReader.getBatteriesPageLink());
        driver.findElement(By.xpath(EnvReader.getAddButton())).click();
        driver.switchTo().frame(driver.findElement(By.className(EnvReader.getFrameName())));
        String title = "Create battery";
        Assert.assertEquals(title, driver.findElement(By.cssSelector(EnvReader.getTile())).getText());

        driver.findElement(By.xpath(EnvReader.getEditNameBattery())).click();
        String nameOfTheBattery = RandomStringUtils.randomAlphanumeric(15);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getEditNameBattery())))
                .click()
                .sendKeys(nameOfTheBattery)
                .build().perform();

        String descriptionOfTheBattery = RandomStringUtils.randomAlphanumeric(8);
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getDescriptionBattery())))
                .click()
                .sendKeys(descriptionOfTheBattery)
                .build().perform();

        String usedInOfTheBattery = RandomStringUtils.randomAlphanumeric(8);
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getUsedInBattery())))
                .click()
                .sendKeys(usedInOfTheBattery)
                .build().perform();

        Random random = new Random();
        int number = random.nextInt(10);
        String cycleInterval = String.format("%d", number);

        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getChargeInterval())))
                .click()
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(cycleInterval)
                .build().perform();

        driver.findElement(By.xpath(EnvReader.getSaveButton())).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();

        //Delete the battery

        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getSearchBar())))
                .click()
                .sendKeys(nameOfTheBattery)
                .build().perform();

        driver.findElement(By.xpath(EnvReader.getDeleteButton())).click();
    }

    @Test
    public void shouldEditUsedInOfBattery() {

        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, EnvReader.getStockOverviewLink());

        driver.findElement(By.xpath(EnvReader.getBatteriesOverview())).click();
        String urlBatteriesOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlBatteriesOverview, EnvReader.getBatteriesOverviewLink());

        driver.findElement(By.xpath(EnvReader.getThreeDots())).click();
        driver.findElement(By.cssSelector(EnvReader.getEditBattery())).click();

        driver.switchTo().frame(driver.findElement(By.className(EnvReader.getFrameName())));
        String title = "Edit battery";
        Assert.assertEquals(title, driver.findElement(By.cssSelector(EnvReader.getTile())).getText());

        driver.findElement(By.xpath(EnvReader.getUsedInBattery())).click();

        String usedInUpdate = RandomStringUtils.randomAlphanumeric(8);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getUsedInBattery())))
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("A")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(usedInUpdate)
                .build().perform();

        driver.findElement(By.xpath(EnvReader.getSaveButton())).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();

        String usedInAfterUpdate = driver.findElement(By.xpath(EnvReader.getUsedIn())).getText();
        Assert.assertEquals(usedInUpdate, usedInAfterUpdate);

        //Renaming usedIn in test_auto

        String usedInInitial = "test_auto";
        driver.findElement(By.xpath(EnvReader.getThreeDots())).click();
        driver.findElement(By.cssSelector(EnvReader.getEditBattery())).click();

        driver.switchTo().frame(driver.findElement(By.className(EnvReader.getFrameName())));
        driver.findElement(By.xpath(EnvReader.getUsedInBattery())).click();
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getUsedInBattery())))
                .keyDown(Keys.CONTROL)
                .sendKeys("A")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(usedInInitial)
                .build().perform();

        driver.findElement(By.xpath(EnvReader.getSaveButton())).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();

        String usedInAfterUpdate2 = driver.findElement(By.xpath(EnvReader.getUsedIn())).getText();
        Assert.assertEquals(usedInAfterUpdate2, usedInInitial);

    }

    @Test
    public void shouldAddBatteryFiftyCharacters() {

        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, EnvReader.getStockOverviewLink());

        driver.navigate().to(EnvReader.getBatteriesPageLink());
        driver.findElement(By.xpath(EnvReader.getAddButton())).click();
        driver.switchTo().frame(driver.findElement(By.className(EnvReader.getFrameName())));
        String title = "Create battery";
        Assert.assertEquals(title, driver.findElement(By.cssSelector(EnvReader.getTile())).getText());

        driver.findElement(By.xpath(EnvReader.getEditNameBattery())).click();
        String nameOfTheBattery = RandomStringUtils.randomAlphanumeric(50);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getEditNameBattery())))
                .click()
                .sendKeys(nameOfTheBattery)
                .build().perform();

        String descriptionOfTheBattery = RandomStringUtils.randomAlphanumeric(50);
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getDescriptionBattery())))
                .click()
                .sendKeys(descriptionOfTheBattery)
                .build().perform();

        String usedInOfTheBattery = RandomStringUtils.randomAlphanumeric(50);
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getUsedInBattery())))
                .click()
                .sendKeys(usedInOfTheBattery)
                .build().perform();

        Random random = new Random();
        int number = random.nextInt(10);
        String cycleInterval = String.format("%d", number);

        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getChargeInterval())))
                .click()
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(cycleInterval)
                .build().perform();

        driver.findElement(By.xpath(EnvReader.getSaveButton())).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();
    }

    @Test
    public void shouldEditDescriptionBattery() {

        String urlStockOverview = driver.getCurrentUrl();
        Assert.assertEquals(urlStockOverview, EnvReader.getStockOverviewLink());

        driver.navigate().to(EnvReader.getBatteriesPageLink());

        driver.findElement(By.xpath(EnvReader.getEditBatterySection())).click();
        driver.switchTo().frame(driver.findElement(By.className(EnvReader.getFrameName())));
        String title = "Edit battery";
        Assert.assertEquals(title, driver.findElement(By.cssSelector(EnvReader.getTile())).getText());

        Actions actions = new Actions(driver);
        String newDescriptionOfTheBattery = RandomStringUtils.randomAlphanumeric(8);
        driver.findElement(By.xpath(EnvReader.getDescriptionBattery())).click();
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getDescriptionBattery())))
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("A")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(newDescriptionOfTheBattery)
                .build().perform();

        driver.findElement(By.xpath(EnvReader.getSaveButton())).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();

        String descriptionAfterUpdate = driver.findElement(By.xpath(EnvReader.getDescriptionBatterySection())).getText();
        Assert.assertEquals(descriptionAfterUpdate, newDescriptionOfTheBattery);

        //Renaming description in descriere_baterie_auto

        String descriptionInitial = "descriere_auto";

        driver.findElement(By.xpath(EnvReader.getEditBatterySection())).click();
        driver.switchTo().frame(driver.findElement(By.className(EnvReader.getFrameName())));

        driver.findElement(By.xpath(EnvReader.getDescriptionBattery())).click();
        actions.moveToElement(driver.findElement(By.xpath(EnvReader.getDescriptionBattery())))
                .click()
                .keyDown(Keys.CONTROL)
                .sendKeys("A")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .sendKeys(descriptionInitial)
                .build().perform();

        driver.findElement(By.xpath(EnvReader.getSaveButton())).click();
        driver.switchTo().defaultContent();
        driver.navigate().refresh();

        String descriptionAfterUpdate2 = driver.findElement(By.xpath(EnvReader.getDescriptionBatterySection())).getText();
        Assert.assertEquals(descriptionAfterUpdate2, descriptionInitial);
    }

}