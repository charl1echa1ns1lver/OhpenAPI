package framework.base;

import com.google.common.base.Function;

import framework.base.FrameworkProperties;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.List;

public class Utils {

	public static final int pageTimeOut = Integer.valueOf(FrameworkProperties.getTimeout()).intValue();

	public static WebElement findElement(WebDriver driver, By selector, int timeOut, boolean visibility) {
		if (visibility) {
			waitForElementVisibility(driver, selector, timeOut);
		} else {
			waitForElementPresence(driver, selector, timeOut);
		}
		return driver.findElement(selector);
	}

	public static List<WebElement> findElements(WebDriver driver, By selector, int timeOut, boolean visibility) {
		if (visibility) {
			waitForAllElementsVisibility(driver, selector, timeOut);
		} else {
			waitForAllElementsPresence(driver, selector, timeOut);
		}
		return driver.findElements(selector);
	}

	public static void waitForElementVisibility(WebDriver driver, MobileElement element) {
		waitForElementVisibility(driver, element, pageTimeOut);
	}

	public static void waitForElementVisibility(WebDriver driver, MobileElement element, int timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotVisibleException.class).ignoring(WebDriverException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForElementVisibility(WebDriver driver, List<MobileElement> elements) {
		waitForElementVisibility(driver, elements, pageTimeOut);
	}

	public static void waitForElementVisibility(WebDriver driver, List<MobileElement> elements, int timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotVisibleException.class).ignoring(WebDriverException.class)
				.ignoring(IndexOutOfBoundsException.class);
		for (MobileElement element : elements) {
			wait.until(ExpectedConditions.visibilityOf(element));
		}
	}

	public static void waitForAllElementsVisibility(WebDriver driver, By locator, int timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotVisibleException.class).ignoring(WebDriverException.class);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public static void waitForElementVisibilityByIndex(WebDriver driver, List<MobileElement> elements, int index) {
		waitForElementVisibilityByIndex(driver, elements, index, pageTimeOut);
	}

	public static void waitForElementVisibilityByIndex(WebDriver driver, List<MobileElement> elements, int index,
			int timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotVisibleException.class).ignoring(WebDriverException.class)
				.ignoring(IndexOutOfBoundsException.class);
		wait.until(ExpectedConditions.visibilityOf(elements.get(index)));
	}

	public static void waitForElementVisibility(WebDriver driver, By locator) {
		waitForElementVisibility(driver, locator, pageTimeOut);
	}

	public static void waitForElementVisibility(WebDriver driver, By locator, int timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotVisibleException.class).ignoring(WebDriverException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitForElementEnabled(WebDriver driver, MobileElement element) {
		waitForElementEnabled(driver, element, pageTimeOut);
	}

	public static void waitForElementEnabled(WebDriver driver, MobileElement element, int timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotVisibleException.class).ignoring(WebDriverException.class);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitForElementPresence(WebDriver driver, By locator) {
		waitForElementPresence(driver, locator, pageTimeOut);
	}

	public static void waitForElementPresence(WebDriver driver, By locator, int timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotVisibleException.class).ignoring(WebDriverException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static void waitForAllElementsPresence(WebDriver driver, By locator, int timeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotVisibleException.class).ignoring(WebDriverException.class);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	/**
	 * Is Element Visible
	 * @param driver
	 * @param element
	 * @param timeOut
	 * @return boolean
	 * @author carlos.cadena
	 */
	public static boolean isElementVisible(WebDriver driver, WebElement element, int timeOut) {
		try {
			return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class)
					.until(new Function<WebDriver, Boolean>() {
						public Boolean apply(WebDriver arg) {
							{
								return element.isDisplayed();
							}
						}
					});
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Is Element visible
	 * @param driver
	 * @param elements
	 * @param index
	 * @param timeOut
	 * @return boolean
	 * @author carlos.cadena
	 */
	public static boolean isElementVisible(WebDriver driver, List<MobileElement> elements, int index, int timeOut) {
		try {
			return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class)
					.until(new Function<WebDriver, Boolean>() {
						public Boolean apply(WebDriver arg) {
							{
								return elements.get(index).isDisplayed();
							}
						}
					});
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Is Element Enabled
	 * @param driver
	 * @param element
	 * @param timeOut
	 * @return boolean
	 * @author carlos.cadena
	 */
	public static boolean isElementEnabled(WebDriver driver, WebElement element, int timeOut) {
		try {
			return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class)
					.ignoring(InvocationTargetException.class).ignoring(IndexOutOfBoundsException.class)
					.until(new Function<WebDriver, Boolean>() {
						public Boolean apply(WebDriver arg) {
							return element.isEnabled();
						}
					});
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Is Element Enabled
	 * @param driver
	 * @param elements
	 * @param index
	 * @param timeOut
	 * @return boolean
	 */
	public static boolean isElementEnabled(WebDriver driver, List<WebElement> elements, int index, int timeOut) {
		try {
			return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class)
					.ignoring(InvocationTargetException.class).ignoring(IndexOutOfBoundsException.class)
					.until(new Function<WebDriver, Boolean>() {
						public Boolean apply(WebDriver arg) {
							return elements.get(index).isEnabled();
						}
					});
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Fluent wait method to check for an element to be visible explicitly
	 * 
	 * @param driver
	 * @param locator
	 * @param timeout
	 * @return true if element is present or false otherwise
	 * @author carlos.cadena
	 */
	public static boolean isElementVisible(WebDriver driver, By locator, int timeOut) {
		try {
			return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class).until(new Function<WebDriver, Boolean>() {
						public Boolean apply(WebDriver arg) {
							WebElement element = arg.findElement(locator);
							if (element != null) {
								return element.isDisplayed();
							}
							return false;
						}
					});
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Fluent wait method to check for an element to be enabled explicitly
	 * 
	 * @param driver
	 * @param locator
	 * @param timeout
	 * @return true if element is present or false otherwise
	 * @author carlos.cadena
	 */
	public static boolean isElementEnabled(WebDriver driver, By locator, int timeout) {
		try {
			return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
					.ignoring(NoSuchElementException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(StaleElementReferenceException.class).ignoring(WebDriverException.class)
					.until(new Function<WebDriver, Boolean>() {
						public Boolean apply(WebDriver arg) {
							WebElement element = arg.findElement(locator);
							if (element != null) {
								return element.isEnabled();
							}
							return false;
						}
					});
		} catch (TimeoutException e) {
			return false;
		}
	}

	/**
	 * Fluent wait method to check for a presence of an element explicitly
	 * 
	 * @param driver
	 * @param locator
	 * @param timeout
	 * @return true if element is present or false otherwise
	 * @author carlos.cadena
	 */
	public static boolean isElementPresent(WebDriver driver, By locator, int timeout) {
		try {
			return new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
					.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
					.ignoring(WebDriverException.class).until(new Function<WebDriver, Boolean>() {
						public Boolean apply(WebDriver arg) {
							WebElement element = arg.findElement(locator);
							if (element != null) {
								return true;
							}
							return false;
						}
					});
		} catch (TimeoutException e) {
			return false;
		}
	}
}
