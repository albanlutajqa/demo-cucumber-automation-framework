package com.demo.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.utils.CommonMethods;

public class CartPage extends CommonMethods {

	@FindBy(css = ".title")
	public WebElement pageTitle;

	@FindBy(css = ".cart_item .inventory_item_name")
	public List<WebElement> cartItemNames;

	@FindBy(id = "checkout")
	public WebElement checkoutButton;

	@FindBy(id = "continue-shopping")
	public WebElement continueShoppingButton;

	public CartPage() {
		PageFactory.initElements(driver, this);
	}
}
