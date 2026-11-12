package com.demo.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Re-runs only the scenarios that failed in the last RegressionRunner run
 * (they are listed in target/failed.txt).
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "@target/failed.txt",
		glue = "com.demo.steps",
		monochrome = true,
		plugin = {
				"pretty",
				"html:target/failed-report.html"
		}
)
public class FailedRunner {

}
