package com.demo.steps;

import org.junit.Assert;

import com.demo.utils.CommonMethods;

import io.cucumber.java.en.Then;

public class CommonSteps extends CommonMethods {

	// Every page in SauceDemo shows its name in the same title element
	@Then("I should see the {string} page")
	public void i_should_see_the_page(String expectedTitle) {
		Assert.assertEquals("Wrong page title", expectedTitle, getText(productsPage.pageTitle));
	}
}
