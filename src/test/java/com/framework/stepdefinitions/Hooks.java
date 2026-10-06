package com.framework.stepdefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.framework.util.ConfigReader;
import com.framework.util.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	 @Before
	    public void setUp() {
	        String browser = System.getProperty("browser", "chrome");
	        DriverFactory.initDriver(browser);
	        DriverFactory.getDriver().get(ConfigReader.get("base.url"));
	    }

	    @After
	    public void tearDown(Scenario scenario) {
	        if (scenario.isFailed()) {
	            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver())
	                    .getScreenshotAs(OutputType.BYTES);
	            scenario.attach(screenshot, "image/png", scenario.getName());
	        }
	        DriverFactory.quitDriver();
	    }
}
