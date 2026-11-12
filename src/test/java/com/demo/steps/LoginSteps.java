package com.demo.steps;

import org.junit.Assert;

import com.demo.utils.CommonMethods;
import com.demo.utils.ConfigsReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends CommonMethods {

	@Given("I am logged in")
	public void i_am_logged_in() {
		loginPage.login(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
		Assert.assertEquals("Login did not open the Products page", "Products", getText(productsPage.pageTitle));
	}

	@When("I log in with valid credentials")
	public void i_log_in_with_valid_credentials() {
		loginPage.login(ConfigsReader.getProperty("username"), ConfigsReader.getProperty("password"));
	}

	@When("I log in with username {string} and password {string}")
	public void i_log_in_with_username_and_password(String username, String password) {
		loginPage.login(username, password);
	}

	@Then("I should see the error message {string}")
	public void i_should_see_the_error_message(String expectedMessage) {
		Assert.assertEquals(expectedMessage, getText(loginPage.errorMessage));
	}

	@When("I log out")
	public void i_log_out() {
		productsPage.logout();
	}

	@Then("I should be on the login page")
	public void i_should_be_on_the_login_page() {
		Assert.assertTrue("Login button is not displayed", waitForVisibility(loginPage.loginButton).isDisplayed());
	}
}
