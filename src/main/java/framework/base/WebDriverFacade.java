package framework.base;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.report.Log;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFacade {

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	public static final int pageTimeOut = Integer.valueOf(FrameworkProperties.getTimeout()).intValue();
	private static final String URL_DOCKER = "http://localhost:4444/wd/hub";
	
    public static WebDriver getDriver() {
    	return webDriver.get();
    }

    public static void createDriver() throws MalformedURLException{
			switch (FrameworkProperties.getBrowser().toUpperCase()){
	            case "FIREFOX":
	                firefoxDriverInitialize(FrameworkProperties.getLocal().equalsIgnoreCase("true"));
	                break;
	            case "CHROME":
	                chromeDriverInitialize(FrameworkProperties.getLocal().equalsIgnoreCase("true"));
	                break;
	            case "EDGE":
	                edgeDriverInitialize(FrameworkProperties.getLocal().equalsIgnoreCase("true"));
	                break;
	            default:
	                throw new IllegalArgumentException(String.format("The selected driver %s is not supported", FrameworkProperties.getBrowser()));
	        }
	        maximize();
	       }
    
    /**
     *  initialize the Firefox driver.
     * @param isLocal 
     * @throws MalformedURLException 
     */
    public static void firefoxDriverInitialize(boolean isLocal) throws MalformedURLException{
        if(isLocal) {
        	WebDriverManager.firefoxdriver().setup();
        }
        else {
        	webDriver.set(new RemoteWebDriver(new URL(URL_DOCKER), new FirefoxOptions()));
        }
    }

    /**
     *  initialize the Chrome driver.
     * @param isLocal 
     * @throws MalformedURLException 
     */
    public static void chromeDriverInitialize(boolean isLocal) throws MalformedURLException{
        ChromeOptions chromeOptions = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("test-type");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("no-sandbox");
        if(isLocal) {
        WebDriverManager.chromedriver().setup();
        }
        else {
        	webDriver.set(new RemoteWebDriver(new URL(URL_DOCKER), chromeOptions));
        }
    }

    /**
     *  initialize the Microsoft EDGE driver.
     * @param isLocal 
     * @throws MalformedURLException 
     */
    public static void edgeDriverInitialize(boolean isLocal) throws MalformedURLException{
        if(isLocal) {
        	WebDriverManager.edgedriver().setup();
        }
        else {
        	webDriver.set(new RemoteWebDriver(new URL(URL_DOCKER), new EdgeOptions()));
        }
    }

    //endregion

 
    /**
     *  maximize the browser windows.
     */
    public static void maximize(){
    	getDriver().manage().window().maximize();
    }

    /**
     *  resize the browser windows.
     */
    public static void resizeWindows(int width, int height){
        Dimension resolution = new Dimension(width, height);
        getDriver().manage().window().setSize(resolution);
    }

    /**
     *  refresh the current windows.
     */
    public static void refreshCurrentWindow(int secondsToWait){
    	getDriver().navigate().refresh();
    }

    /**
     *  navigate to the previous windows.
     */
    public static void clickNavigateBackButton(int secondsToWait){
    	getDriver().navigate().back();
    }
    
    
    /**
     * Navigate to url
     * @param url
     * @return Page instance
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public static <T extends BasePage> T navigateTo(Class<T> page, String url) throws InstantiationException, IllegalAccessException{
    	getDriver().get(url);
    	return page.newInstance();
    }
    
    /**
     *  take a screenshot.
     */
    public static void takeScreenshot(String screenshotName, String saveDirectory){
        File screenshot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
        screenshot.renameTo(new File(saveDirectory + screenshotName + ".png"));
    }
    
	/**
	 * Takes screenshot
	 * 
	 * @param screenshotTitle
	 * @param saveDirectory
	 * @param extension       > provide a valid format e.g .png or .jpeg
	 * @author carlos.cadena
	 */
	public static void takeScreenshot(String screenshotTitle, String saveDirectory,
			String extension) {
		String pathString = saveDirectory + screenshotTitle + extension;
		takeScreenshot(pathString);
	}

	/**
	 * Takes screenshot using an specific absolute path
	 * @param screenshotPath
	 * @author carlos.cadena
	 */
	public static void takeScreenshot(String screenshotPath) {
		File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		File picture = new File(screenshotPath);
		picture.setReadable(true);
		if (picture.exists()) {
			picture.delete();
		}
		screenshot.renameTo(picture);
	}

    /**
     *  close the current windows.
     */
    public static void closeCurrentWindow(){
        getDriver().close();
    }

    /**
     *  close the entire driver.
     */
    public static void shutdown(){
    	try {
    	getDriver().close();
        getDriver().quit();
    	}
    	catch(Exception e)
    	{
    		Log.logger.debug(" Issue closing driver, trying re retry..." +  e.getMessage());
            getDriver().quit();
    	}
    }

    //endregion

    //region Driver Get Methods

    /**
     *  get the current page URL.
	 * @return String
     */
    public static String getPageURL() { return getDriver().getCurrentUrl();}

    /**
     *  get the current page title.
	 * @return String
     */
    public static String getPageTitle() { return getDriver().getTitle();}
    
    /**
	 * Find an element first evaluating visibility
	 * @param locator
	 * @return MobileElement
	 * @author carlos.cadena
	 */
	public static WebElement findElementIfVisible(By locator) {
		return findElement(locator, pageTimeOut, true);
	}
	
	/**
	 * Find elements if all are visible
	 * @param locator
	 * @return A List of MobileElement (with zero elements if condition was not met)
	 * @author carlos.cadena
	 */
	public static List<WebElement> findElementsAllVisible(By locator) {
		return findElements(locator, pageTimeOut, true);
	}
	
	/**
	 * Find an element if is present
	 * @param locator
	 * @return WebElement
	 * @author carlos.cadena
	 */
	public static WebElement findElement(By locator) {
		return findElement(locator, pageTimeOut, false);
	}
	
	/**
	 * Find elements (since at least one is present that one will be returned )
	 * @param locator
	 * @return WebElement
	 * @author carlos.cadena
	 */
	public static List<WebElement> findElements(By locator) {
		return findElements(locator, pageTimeOut, false);
	}
	
	/**
	 * Find Element
	 * @param locator 
	 * @param timeOut 
	 * @param visibility
	 * @return MobileElement
	 * @author carlos.cadena
	 */
	public static WebElement findElement(By locator, int timeOut, boolean visibility) {
		return Utils.findElement(getDriver(), locator, timeOut, visibility);
	}
	
	/**
	 * Find Element on a element container
	 * @param locator 
	 * @param timeOut 
	 * @param visibility
	 * @return WebElement
	 * @author carlos.cadena
	 */
	public static WebElement findElement(WebElement container, By locator, boolean visibility) {
		return findElement(container, locator, pageTimeOut , visibility);
	}
	
	
	/**
	 * Find Element on a element container
	 * @param locator 
	 * @param timeOut 
	 * @param visibility
	 * @return WebElement
	 * @author carlos.cadena
	 */
	public static WebElement findElement(WebElement container, By locator, int timeOut, boolean visibility) {
		return Utils.findElement(container, locator, timeOut, visibility);
	}
	
	/**
	 * Find Elements
	 * @param locator 
	 * @param timeOut 
	 * @param visibility
	 * @return A List of MobileElement
	 * @author carlos.cadena
	 */
	public static List<WebElement> findElements(By locator, int timeOut, boolean visibility) {
		return Utils.findElements(getDriver(), locator, pageTimeOut, visibility);
    }
	
	public static boolean isElementPresent(By locator) {
		return Utils.isElementPresent(getDriver(), locator, pageTimeOut);
	}

	public static boolean isElementPresent(By locator, int timeout) {
		return Utils.isElementPresent(getDriver(), locator, timeout);
	}

	public static boolean isElementEnabled(WebElement element, int timeout) {
		return Utils.isElementEnabled(getDriver(), element, timeout);
	}
	
	public static boolean isElementEnabled(By locator) {
		return isElementEnabled(locator, pageTimeOut);
	}
	
	public static boolean isElementEnabled(By locator, int timeout) {
		return Utils.isElementEnabled(getDriver(), locator, timeout);
	}


	public static boolean isElementEnabled(WebElement element) {
		return isElementEnabled(element, pageTimeOut);
	}

	public static boolean isElementEnabled(List<WebElement> elements, int index, int timeout) {
		return Utils.isElementEnabled(getDriver(), elements, index, timeout);
	}

	public static boolean isElementEnabled(List<WebElement> elements, int index) {
		return isElementEnabled(elements, index, pageTimeOut);
	}
	
	public static void waitForElementVisibility(By locator) {
		waitForElementVisibility(locator, pageTimeOut);
	}
	
	public static void waitForElementVisibility(By locator, int timeout) {
		Utils.waitForElementVisibility(getDriver(), locator, timeout);
	}
	
	public static void waitForElementPresence(By locator) {
		waitForElementPresence(locator, pageTimeOut);
	}
	
	public static void waitForElementPresence(By locator, int timeout) {
		Utils.waitForElementPresence(getDriver(), locator, timeout);
	}
	
	public static void waitForElementVisibility(WebElement element, int timeout) {
		Utils.waitForElementVisibility(getDriver(), element, timeout);
	}
	
	public static void waitForElementVisibility(WebElement element) {
		waitForElementVisibility(element, pageTimeOut);
	}
	
	public static boolean isAttributePresent(By locator, String attribute, String value, boolean contains) {
		return Utils.isAttributePresentOnElement(getDriver(), locator, attribute, value, contains, pageTimeOut);
	}
	
	
	public static boolean isElementVisible(WebElement element) {
		return isElementVisible(element, pageTimeOut);
	}

	public static boolean isElementVisible(By locator) {
		return isElementVisible(locator, pageTimeOut);
	}
	
	public static boolean isElementVisible(WebElement container, By locator, int timeOut) {
		return Utils.isElementVisible(container, locator, timeOut);	
	}
	
	
	public static boolean isElementVisible(WebElement element, int timeOut) {
		return Utils.isElementVisible(getDriver(), element, timeOut);
	}

	public static boolean isElementVisible(By locator, int timeOut) {
		return Utils.isElementVisible(getDriver(), locator, timeOut);
	}
	
	
	public static void acceptAlert() {
		WebDriverWait wait = new WebDriverWait(getDriver(), 5000);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = getDriver().switchTo().alert();
		alert.accept();
	}
	
	public static void click(WebElement element) {
		click(element, pageTimeOut);
	}
	
	public static void click(WebElement element, int timeOut) {
		try {
			element.click();
		} catch (StaleElementReferenceException | NoSuchElementException | ElementNotVisibleException | ElementClickInterceptedException e) {
			Utils.waitForElementEnabled(getDriver(), element, timeOut);
			element.click();
		}
	}
	
	public static void clickJavascript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static void scrollToElementView(WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void scrollIntoView(WebElement element) {
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}

}