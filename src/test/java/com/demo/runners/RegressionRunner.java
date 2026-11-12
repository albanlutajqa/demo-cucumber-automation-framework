package com.demo.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Runs the full regression suite. This is the runner used by "mvn test" and CI.
 * Run a different tag with: mvn test -Dcucumber.filter.tags="@smoke"
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/",
		glue = "com.demo.steps",
		tags = "@regression",
		monochrome = true,
		plugin = {
				"pretty",
				"html:target/cucumber-default-report.html",
				"json:target/cucumber.json",
				"rerun:target/failed.txt"
		}
)
public class RegressionRunner {

}
