package com.endava.grocy.pages;

import com.endava.grocy.client.BaseClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StockOverviewPage {

    WebDriver driver;

    @FindBy(xpath = "//h2[contains(text(),\"Stock overview\")]")
    public WebElement pageTitle;

    @FindBy(id = "info-current-stock")
    public WebElement infoCurrentStock;

    @FindBy(xpath = "//div//a[contains(text(),'Journal')]")
    public WebElement buttonJournalButton;

    @FindBy(xpath = "//div//a[contains(text(),'Stock entries')]")
    public WebElement buttonStockEntries;

    @FindBy(xpath = "//div//a[contains(text(),'Location Content Sheet')]")
    public WebElement buttonLocationContentSheet;

    public StockOverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

}
