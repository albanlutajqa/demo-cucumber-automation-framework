package com.demo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.demo.utils.CommonMethods;

public class ProductsPage extends CommonMethods {

	@FindBy(css = ".title")
	public WebElement pageTitle;

	@FindBy(css = ".inventory_item_name")
	public List<WebElement> productNames;

	@FindBy(css = ".inventory_item_price")
	public List<WebElement> productPrices;

	@FindBy(css = ".product_sort_container")
	public WebElement sortDropdown;

	@FindBy(css = ".shopping_cart_badge")
	public WebElement cartBadge;

	@FindBy(css = ".shopping_cart_link")
	public WebElement cartLink;

	@FindBy(id = "react-burger-menu-btn")
	public WebElement menuButton;

	@FindBy(id = "logout_sidebar_link")
	public WebElement logoutLink;

	public ProductsPage() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Clicks "Add to cart" for a product, e.g. "Sauce Labs Backpack"
	 * -> button id "add-to-cart-sauce-labs-backpack"
	 */
	public void addToCart(String productName) {
		click(driver.findElement(By.id("add-to-cart-" + toId(productName))));
	}

	/**
	 * Clicks "Remove" for a product that is already in the cart
	 */
	public void removeFromCart(String productName) {
		click(driver.findElement(By.id("remove-" + toId(productName))));
	}

	/**
	 * Returns the number on the cart icon, or 0 when the cart is empty
	 */
	public int getCartCount() {
		if (isDisplayed(cartBadge)) {
			return Integer.parseInt(cartBadge.getText().trim());
		}
		return 0;
	}

	public void logout() {
		click(menuButton);
		click(logoutLink);
	}

	private String toId(String productName) {
		return productName.trim().toLowerCase().replace(" ", "-");
	}
}
