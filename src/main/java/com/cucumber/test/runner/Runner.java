package com.cucumber.test.runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/main/resources/features", // Path to your feature files
        glue = "com.cucumber.test.stepDefinitions", // Package where your step definitions are located
        tags = "@EcommerceWebApp", // Optional: Specify tags to run specific scenarios
        plugin = {"pretty", "html:target/cucumber-reports/cucumber-pretty", "json:target/cucumber-reports/cucumber.json"} // Optional: Specify reporting plugins
)
public class Runner {
}
