package com.demo.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.testbase.PageInitializer;

public class CommonMethods extends PageInitializer {

	/**
	 * Waits until the element is visible, clears it and types the text
	 */
	public static void sendText(WebElement element, String text) {
		waitForVisibility(element);
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * Waits until the element is clickable, then clicks it
	 */
	public static void click(WebElement element) {
		waitForClickability(element).click();
	}

	/**
	 * Waits until the element is visible and returns its trimmed text
	 */
	public static String getText(WebElement element) {
		return waitForVisibility(element).getText().trim();
	}

	/**
	 * Selects a dropdown option by its value attribute
	 */
	public static void selectDropdownByValue(WebElement element, String value) {
		new Select(waitForVisibility(element)).selectByValue(value);
	}

	/**
	 * Selects a dropdown option by its visible text
	 */
	public static void selectDropdownByText(WebElement element, String visibleText) {
		new Select(waitForVisibility(element)).selectByVisibleText(visibleText);
	}

	/**
	 * Returns the trimmed text of every element in the list
	 */
	public static List<String> getTexts(List<WebElement> elements) {
		List<String> texts = new ArrayList<>();
		for (WebElement element : elements) {
			texts.add(element.getText().trim());
		}
		return texts;
	}

	/**
	 * Converts a price like "$29.99" or "Total: $32.39" into a double
	 */
	public static double parsePrice(String text) {
		return Double.parseDouble(text.substring(text.indexOf('$') + 1).trim());
	}

	public static WebDriverWait getWaitObject() {
		return new WebDriverWait(driver, Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));
	}

	public static WebElement waitForVisibility(WebElement element) {
		return getWaitObject().until(ExpectedConditions.visibilityOf(element));
	}

	public static WebElement waitForClickability(WebElement element) {
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Returns true if the element is displayed, false if it is not on the page
	 */
	public static boolean isDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Takes a screenshot, saves it under target/screenshots and returns the
	 * bytes so it can be attached to the Cucumber report
	 */
	public static byte[] takeScreenshot(String fileName) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] picture = ts.getScreenshotAs(OutputType.BYTES);
		File file = ts.getScreenshotAs(OutputType.FILE);

		String safeName = fileName.replaceAll("[^a-zA-Z0-9-_/]", "_");
		try {
			FileUtils.copyFile(file, new File(Constants.SCREENSHOT_FILEPATH + safeName + getTimeStamp() + ".png"));
		} catch (IOException e) {
			System.out.println("Could not save screenshot: " + e.getMessage());
		}
		return picture;
	}

	public static String getTimeStamp() {
		return new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss").format(new Date());
	}
}
