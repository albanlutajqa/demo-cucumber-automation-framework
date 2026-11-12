package com.demo.steps;

import java.util.List;

import org.junit.Assert;

import com.demo.utils.CommonMethods;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartSteps extends CommonMethods {

	@When("I add {string} to the cart")
	public void i_add_to_the_cart(String productName) {
		productsPage.addToCart(productName);
	}

	@When("I add the following products to the cart")
	public void i_add_the_following_products_to_the_cart(List<String> productNames) {
		for (String productName : productNames) {
			productsPage.addToCart(productName);
		}
	}

	@When("I remove {string} from the cart")
	public void i_remove_from_the_cart(String productName) {
		productsPage.removeFromCart(productName);
	}

	@Then("the cart icon should show {int}")
	public void the_cart_icon_should_show(int expectedCount) {
		Assert.assertEquals(expectedCount, productsPage.getCartCount());
	}

	@Then("the cart should contain {string}")
	public void the_cart_should_contain(String productName) {
		click(productsPage.cartLink);
		Assert.assertEquals("Your Cart", getText(cartPage.pageTitle));

		List<String> itemsInCart = getTexts(cartPage.cartItemNames);
		Assert.assertTrue(productName + " is not in the cart: " + itemsInCart, itemsInCart.contains(productName));
	}
}
