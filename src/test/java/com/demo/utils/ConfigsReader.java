package com.demo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {

	private static Properties prop;

	/**
	 * Reads the properties file
	 *
	 * @param filePath
	 */
	public static void readProperties(String filePath) {
		try (FileInputStream fis = new FileInputStream(filePath)) {
			prop = new Properties();
			prop.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Could not read configuration file: " + filePath, e);
		}
	}

	/**
	 * Returns the value for a key. A JVM system property (-Dkey=value) wins over
	 * the properties file, so settings can be changed from the command line or CI.
	 *
	 * @param key
	 * @return value
	 */
	public static String getProperty(String key) {
		String value = System.getProperty(key);
		if (value == null || value.isBlank()) {
			value = prop.getProperty(key);
		}
		return value;
	}
}
