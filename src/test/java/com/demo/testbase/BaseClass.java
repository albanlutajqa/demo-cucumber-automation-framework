package com.demo.testbase;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.demo.utils.ConfigsReader;
import com.demo.utils.Constants;

public class BaseClass {

	public static WebDriver driver;

	/**
	 * Opens the browser from configuration.properties, goes to the application
	 * URL and initializes all page objects.
	 */
	public static void setUp() {
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

		String browser = ConfigsReader.getProperty("browser");
		boolean headless = Boolean.parseBoolean(ConfigsReader.getProperty("headless"));

		// Selenium Manager downloads the matching driver automatically
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver(chromeOptions(headless));
			break;

		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			if (headless) {
				firefoxOptions.addArguments("-headless");
			}
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			EdgeOptions edgeOptions = new EdgeOptions();
			if (headless) {
				edgeOptions.addArguments("--headless=new");
			}
			driver = new EdgeDriver(edgeOptions);
			break;

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Constants.PAGE_LOAD_TIME));
		driver.manage().window().maximize();
		driver.get(ConfigsReader.getProperty("url"));

		PageInitializer.initialize();
	}

	public static void tearDown() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	private static ChromeOptions chromeOptions(boolean headless) {
		ChromeOptions options = new ChromeOptions();

		// Turn off Chrome's password manager popups, they block the page after login
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--disable-features=PasswordLeakDetection");

		if (headless) {
			options.addArguments("--headless=new", "--window-size=1920,1080", "--no-sandbox",
					"--disable-dev-shm-usage");
		}
		return options;
	}
}
