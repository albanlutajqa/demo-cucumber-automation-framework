package com.demo.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Runner for local development: change the tag to run only what you are working on.
 * Set dryRun = true to check that every step has a step definition without opening a browser.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/",
		glue = "com.demo.steps",
		tags = "@smoke",
		dryRun = false,
		monochrome = true,
		plugin = {
				"pretty",
				"html:target/cucumber-default-report.html"
		}
)
public class TestRunner {

}
