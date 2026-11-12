package com.demo.steps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;

import com.demo.utils.CommonMethods;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductsSteps extends CommonMethods {

	@Then("I should see {int} products")
	public void i_should_see_products(int expectedCount) {
		Assert.assertEquals(expectedCount, productsPage.productNames.size());
	}

	@Then("I should see the following products")
	public void i_should_see_the_following_products(List<String> expectedProducts) {
		List<String> actualProducts = getTexts(productsPage.productNames);
		Assert.assertEquals(expectedProducts, actualProducts);
	}

	@When("I sort the products by {string}")
	public void i_sort_the_products_by(String sortOption) {
		selectDropdownByText(productsPage.sortDropdown, sortOption);
	}

	@Then("the products should be sorted by price from low to high")
	public void the_products_should_be_sorted_by_price_from_low_to_high() {
		List<Double> actualPrices = getPrices();
		List<Double> expectedPrices = new ArrayList<>(actualPrices);
		Collections.sort(expectedPrices);

		Assert.assertEquals(expectedPrices, actualPrices);
	}

	@Then("the products should be sorted by price from high to low")
	public void the_products_should_be_sorted_by_price_from_high_to_low() {
		List<Double> actualPrices = getPrices();
		List<Double> expectedPrices = new ArrayList<>(actualPrices);
		expectedPrices.sort(Collections.reverseOrder());

		Assert.assertEquals(expectedPrices, actualPrices);
	}

	@Then("the products should be sorted by name from Z to A")
	public void the_products_should_be_sorted_by_name_from_z_to_a() {
		List<String> actualNames = getTexts(productsPage.productNames);
		List<String> expectedNames = new ArrayList<>(actualNames);
		expectedNames.sort(Collections.reverseOrder());

		Assert.assertEquals(expectedNames, actualNames);
	}

	private List<Double> getPrices() {
		List<Double> prices = new ArrayList<>();
		for (String price : getTexts(productsPage.productPrices)) {
			prices.add(parsePrice(price));
		}
		return prices;
	}
}
