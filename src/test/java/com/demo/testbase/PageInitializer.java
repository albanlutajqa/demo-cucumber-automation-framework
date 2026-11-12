package com.demo.testbase;

import com.demo.pages.CartPage;
import com.demo.pages.CheckoutPage;
import com.demo.pages.LoginPage;
import com.demo.pages.ProductsPage;

public class PageInitializer extends BaseClass {

	public static LoginPage loginPage;
	public static ProductsPage productsPage;
	public static CartPage cartPage;
	public static CheckoutPage checkoutPage;

	public static void initialize() {
		loginPage = new LoginPage();
		productsPage = new ProductsPage();
		cartPage = new CartPage();
		checkoutPage = new CheckoutPage();
	}
}
