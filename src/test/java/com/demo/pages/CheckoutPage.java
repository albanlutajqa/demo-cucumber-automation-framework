package com.demo.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.utils.CommonMethods;

/**
 * Covers the three checkout steps: Your Information, Overview and Complete
 */
public class CheckoutPage extends CommonMethods {

	@FindBy(css = ".title")
	public WebElement pageTitle;

	// Step 1 - Your Information
	@FindBy(id = "first-name")
	public WebElement firstNameField;

	@FindBy(id = "last-name")
	public WebElement lastNameField;

	@FindBy(id = "postal-code")
	public WebElement postalCodeField;

	@FindBy(id = "continue")
	public WebElement continueButton;

	@FindBy(css = "[data-test='error']")
	public WebElement errorMessage;

	// Step 2 - Overview
	@FindBy(css = ".cart_item .inventory_item_price")
	public List<WebElement> itemPrices;

	@FindBy(css = ".summary_subtotal_label")
	public WebElement subtotalLabel;

	@FindBy(css = ".summary_tax_label")
	public WebElement taxLabel;

	@FindBy(css = ".summary_total_label")
	public WebElement totalLabel;

	@FindBy(id = "finish")
	public WebElement finishButton;

	// Step 3 - Complete
	@FindBy(css = ".complete-header")
	public WebElement completeHeader;

	@FindBy(id = "back-to-products")
	public WebElement backHomeButton;

	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}

	public void fillInformation(String firstName, String lastName, String postalCode) {
		sendText(firstNameField, firstName);
		sendText(lastNameField, lastName);
		sendText(postalCodeField, postalCode);
		click(continueButton);
	}
}
