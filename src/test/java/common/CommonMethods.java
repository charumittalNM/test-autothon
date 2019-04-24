package common;

import java.net.URL;
import java.util.ArrayList;

import net.sourceforge.htmlunit.corejs.javascript.ast.ErrorCollector;

import org.apache.commons.logging.Log;
import org.apache.poi.ss.formula.DataValidationEvaluator.OperatorEnum;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import util.Xls_Reader;

import com.gargoylesoftware.htmlunit.Cache;

public class CommonMethods {

	public final static String USERNAME = "charu37";
	public final static String AUTOMATE_KEY = "PKpe2CDFmbZiMG4Q9frw";
	public final static String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";
	public static URL browseStackURL;
	public static WebDriver driver;
	private static ArrayList<ITestContext> iTestContextsList;

	public static void contexts() {
		iTestContextsList = new ArrayList<ITestContext>();
	}

	public static ArrayList<ITestContext> getContexts() {
		return iTestContextsList;
	}

	public static void addContextsToList(ITestContext context) {
		iTestContextsList.add(context);
	}

	public static void scrollPageRight() {
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT)
			.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT)
			.perform();
			actions.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT)
			.sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT)
			.perform();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void scrollPageLeft() {
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT)
			.sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT)
			.perform();
			actions.sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT)
			.sendKeys(Keys.ARROW_LEFT).sendKeys(Keys.ARROW_LEFT)
			.perform();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void scrollPageUp() {
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP)
			.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).perform();
			actions.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP)
			.sendKeys(Keys.ARROW_UP).sendKeys(Keys.ARROW_UP).perform();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void scrollPageDown() {
		try {
			Actions actions = new Actions(driver);
			actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
			.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
			.perform();
			actions.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
			.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
			.perform();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void refreshPage() {
		waitForXSeconds(2000);
		driver.navigate().refresh();
		waitForXSeconds(5000);
		waitForElementPresence("page_loading");
	}

	public static void DragWebElement(WebElement element) {
		Actions action = new Actions(driver);
		action.dragAndDropBy(element, 250, 0).build().perform();
	}

	public static void rightClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).build().perform();
	}

	public static void doubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}

	public static void exitFilterBox() {
		Actions actions = new Actions(driver);
		actions.click().build().perform();
	}

	public static void exitFilterDropDown() {
		Actions actions = new Actions(driver);
		actions.moveByOffset(-100, 100).build().perform();
		actions.click().build().perform();
	}

	public static WebElement findElement(String locatorName) {
		String locatorType = Xls_Reader.getLoactorType(locatorName);
		String locatorValue = Xls_Reader.getLoactorValue(locatorName);
		WebElement element = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			try {
				element = driver.findElement(By.xpath(locatorValue));
			} catch (Throwable e) {
				// TODO: handle exception
				/*Log.error("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);
				ErrorCollector
				.VerifyFail("Web driver failed to locate the element whose xpath is:"
						+ locatorValue);*/
			}
		} else if (locatorType.equalsIgnoreCase("id")) {
		} else if (locatorType.equalsIgnoreCase("name")) {
		} else if (locatorType.equalsIgnoreCase("linktest")) {
		} else if (locatorType.equalsIgnoreCase("css")) {
		} else if (locatorType.equalsIgnoreCase("tagname")) {
		} else {
		}
		return element;
	}

	public static void waitForXSeconds(int timeinMillis) {
		long timeNow = System.currentTimeMillis();
		long expectedWait = timeNow + timeinMillis;
		while (expectedWait > timeNow) {
			timeNow = System.currentTimeMillis();
		}
	}

	public static void waitForElementPresence(String locatorName) {
		String locatorType = ""; //Xls_Reader.getLocatorType(locatorName);
		String locatorValue ="";// Xls_Reader.getLocatorValue(locatorName);
		WebDriverWait wait = new WebDriverWait(driver, 120);
		if (locatorType.toUpperCase().equals("CSS")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.cssSelector(locatorValue)));
			} catch (Throwable t) {
				/*Log.error(locatorName + ": was not present");
				ErrorCollector.VerifyFail(locatorName + ": was not present");*/
			}
		} else if (locatorType.toUpperCase().equals("XPATH")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.xpath(locatorValue)));
			} catch (Throwable t) {
				/*Log.error(locatorName + ": was not present");
				ErrorCollector.VerifyFail(locatorName + ": was not present");*/
			}
		} else if (locatorType.toUpperCase().equals("LINKTEXT")) {
			try {
				wait.until(ExpectedConditions.presenceOfElementLocated(By
						.linkText(locatorValue)));
			} catch (Throwable t) {
				/*Log.error(locatorName + ": was not present");
				ErrorCollector.VerifyFail(locatorName + ": was not present");*/
			}
		}
	}

	public static WebDriver launchBrowser(String Platform, String Browser) {
		DesiredCapabilities caps = null;
		String browserType = Util.getConfigData("browser");
		try {
			caps.setCapability("browserstack.local", "false"); 

			switch (Browser) {
			case "Chrome":  
				caps.setCapability("browser", "Chrome");
				caps.setCapability("browser_version", "62.0");
				break;

			case "Edge":  
				caps.setCapability("browser", "Edge");
				caps.setCapability("browser_version", "18.0"); 

				break;

			case "IE":  
				caps.setCapability("browser", "IE");
				caps.setCapability("browser_version", "11.0"); 

				break;

			default: 
				caps.setCapability("browser", "Chrome");
				caps.setCapability("browser_version", "62.0");
				break;
			}

			switch (Platform) {

			case "Windows10":  
				caps.setCapability("os_version", "10"); 
				break;
			case "Android":
				caps.setCapability("os_version", "7.0");
				caps.setCapability("device", "Samsung Galaxy S8");
				caps.setCapability("real_mobile", "true");
				caps.setCapability("browserstack.local", "false"); 
				break;

			default: 
				caps.setCapability("os_version", "10");
				break;
			}
			browseStackURL = new URL(URL);


		} catch (Throwable t) {
			// TODO: handle exception
			// Log.error("Unable to launch browser :" + browserType);
			// ErrorCollector.VerifyFail("unable to launch browser :" +
			// browserType);
		}


		return new RemoteWebDriver(browseStackURL, caps); 
	}


}
