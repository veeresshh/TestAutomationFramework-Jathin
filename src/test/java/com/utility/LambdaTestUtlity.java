package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtlity {
	private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();;
	private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>();

	public static WebDriver intializeLambdaTestSession(String browser, String testName) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browser);
		capabilities.setCapability("browserVersion", "143");
		Map<String, Object> ltOptions = new HashMap();
		ltOptions.put("username", "veeresh0270");
        ltOptions.put("accessKey", "LT_702AA77ZpY7i6l4Uvhz9DKyUR44czpyjhn1mnzdNBlSL1Tu");
        ltOptions.put("platformName", "Windows 11");
        ltOptions.put("project", "Selenium 4");
        ltOptions.put("build", "Build 1");
        ltOptions.put("name", testName);
        ltOptions.put("w3c", true);
		ltOptions.put("selenium_version", "4.38.0");
		capabilities.setCapability("LT:Options", ltOptions);
		capabilitiesLocal.set(capabilities);
		
		
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driverLocal.set(driver);

		return driverLocal.get();
	}

	public static void quitSession() {
		if (driverLocal.get() != null) {
			driverLocal.get().quit();
		}
	}
}
