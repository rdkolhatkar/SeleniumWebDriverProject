package com.cucumber.test.runner;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/main/resources/features",
        glue = "com.cucumber.test.stepDefinitions",
        tags = "@ErrorValidations",
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-reports/reports.html", "json:target/cucumber-reports/cucumber.json"}
)
public class ErrorTestRunner {
}
