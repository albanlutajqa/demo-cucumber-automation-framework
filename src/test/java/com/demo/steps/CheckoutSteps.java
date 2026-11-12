package com.demo.steps;

import org.junit.Assert;

import com.demo.utils.CommonMethods;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps extends CommonMethods {

	@When("I go to the cart and click checkout")
	public void i_go_to_the_cart_and_click_checkout() {
		click(productsPage.cartLink);
		click(cartPage.checkoutButton);
	}

	@When("I enter checkout information {string}, {string} and {string}")
	public void i_enter_checkout_information(String firstName, String lastName, String postalCode) {
		checkoutPage.fillInformation(firstName, lastName, postalCode);
	}

	@Then("I should see the checkout error message {string}")
	public void i_should_see_the_checkout_error_message(String expectedMessage) {
		Assert.assertEquals(expectedMessage, getText(checkoutPage.errorMessage));
	}

	@Then("the item total should equal the sum of the item prices")
	public void the_item_total_should_equal_the_sum_of_the_item_prices() {
		double sumOfPrices = 0;
		for (String price : getTexts(checkoutPage.itemPrices)) {
			sumOfPrices += parsePrice(price);
		}
		double itemTotal = parsePrice(getText(checkoutPage.subtotalLabel));

		Assert.assertEquals(toCents(sumOfPrices), toCents(itemTotal));
	}

	@Then("the total should equal the item total plus tax")
	public void the_total_should_equal_the_item_total_plus_tax() {
		double itemTotal = parsePrice(getText(checkoutPage.subtotalLabel));
		double tax = parsePrice(getText(checkoutPage.taxLabel));
		double total = parsePrice(getText(checkoutPage.totalLabel));

		Assert.assertEquals(toCents(itemTotal + tax), toCents(total));
	}

	@When("I finish the order")
	public void i_finish_the_order() {
		click(checkoutPage.finishButton);
	}

	@Then("I should see the confirmation message {string}")
	public void i_should_see_the_confirmation_message(String expectedMessage) {
		Assert.assertEquals(expectedMessage, getText(checkoutPage.completeHeader));
	}

	// Compare money in cents to avoid decimal rounding problems
	private long toCents(double amount) {
		return Math.round(amount * 100);
	}
}
