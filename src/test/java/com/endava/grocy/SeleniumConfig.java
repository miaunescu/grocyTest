package com.endava.grocy;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SeleniumConfig {
    private static SeleniumConfig seleniumConfig;
    @Getter
    private WebDriver driver;
    @Getter
    private Actions actions;

    private SeleniumConfig() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);
    }

    public static SeleniumConfig getInstance() {
        if (seleniumConfig == null) {
            seleniumConfig = new SeleniumConfig();
        }
        return seleniumConfig;
    }

}
