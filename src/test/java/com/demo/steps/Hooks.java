package com.demo.steps;

import com.demo.testbase.BaseClass;
import com.demo.utils.CommonMethods;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	@Before
	public void start() {
		BaseClass.setUp();
	}

	@After
	public void end(Scenario scenario) {
		if (BaseClass.driver != null) {
			// Every scenario gets a screenshot in the report.
			// The image file is saved in target/screenshots/passed or /failed.
			String folder = scenario.isFailed() ? "failed/" : "passed/";
			byte[] picture = CommonMethods.takeScreenshot(folder + scenario.getName());
			scenario.attach(picture, "image/png", scenario.getName());
		}
		BaseClass.tearDown();
	}
}
