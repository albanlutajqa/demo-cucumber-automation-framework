package com.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.utils.CommonMethods;

public class LoginPage extends CommonMethods {

	@FindBy(id = "user-name")
	public WebElement usernameField;

	@FindBy(id = "password")
	public WebElement passwordField;

	@FindBy(id = "login-button")
	public WebElement loginButton;

	@FindBy(css = "[data-test='error']")
	public WebElement errorMessage;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void login(String username, String password) {
		sendText(usernameField, username);
		sendText(passwordField, password);
		click(loginButton);
	}
}
