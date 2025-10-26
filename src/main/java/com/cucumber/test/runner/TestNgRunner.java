package com.cucumber.test.runner;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/main/resources/features",
        glue = "com.cucumber.test.stepDefinitions",
        tags = "@EcommerceWebApp",
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-reports/reports.html", "json:target/cucumber-reports/cucumber.json"}
)
public class TestNgRunner extends AbstractTestNGCucumberTests {
    // As cucumber only supports the Junit and it does not support the TestNg
    // We extend AbstractTestNGCucumberTests to integrate Cucumber with TestNG, allowing Cucumber feature files to run as TestNG tests. It provides TestNG-compatible hooks, manages scenarios execution, supports parallel runs, and ensures proper reporting, enabling Cucumber’s BDD features to work seamlessly within the TestNG framework.
    // Note: To use this TestNgRunner version compatibility between Cucumber and Cucumber-TestNG dependency is required
}
