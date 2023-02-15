package com.endava.grocy.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/endava/grocy/cucumber/features",
        tags = "@TestRunner",
        glue = {"com.endava.grocy.cucumber"})

public class TestRunner {
}
