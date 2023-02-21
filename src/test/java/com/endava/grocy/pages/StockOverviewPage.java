package com.endava.grocy.pages;

import com.endava.grocy.client.BaseClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StockOverviewPage {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"page-content\"]/div[1]/div/div[1]/h2[1]")
    public WebElement pageTitle;

    @FindBy(id = "info-current-stock")
    public WebElement infoCurrentStock;

    @FindBy(xpath = "//*[@id=\"related-links\"]/a[1]")
    public WebElement journalButton;

    @FindBy(xpath = "//*[@id=\"related-links\"]/a[2]")
    public WebElement stockEntries;

    public StockOverviewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

}
