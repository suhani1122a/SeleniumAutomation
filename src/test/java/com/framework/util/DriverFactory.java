package com.framework.util;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
	 private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    public static WebDriver getDriver() {
	        return driver.get();
	    }

	    public static void initDriver(String browser) {
	        WebDriver webDriver;

	        switch (browser.toLowerCase()) {
	            case "firefox":
	                WebDriverManager.firefoxdriver().setup();
	                FirefoxOptions firefoxOptions = new FirefoxOptions();
	                if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
	                    firefoxOptions.addArguments("--headless");
	                }
	                webDriver = new FirefoxDriver(firefoxOptions);
	                break;

	            case "chrome":
	            default:
	                WebDriverManager.chromedriver().setup();
	                ChromeOptions chromeOptions = new ChromeOptions();
	                if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
	                    chromeOptions.addArguments("--headless=new");
	                }
	                chromeOptions.addArguments("--remote-allow-origins=*");
	                webDriver = new ChromeDriver(chromeOptions);
	                break;
	        }

	        webDriver.manage().window().maximize();
	        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	        driver.set(webDriver);
	    }

	    public static void quitDriver() {
	        if (driver.get() != null) {
	            driver.get().quit();
	            driver.remove();
	        }
	    }
}
