package framework.base;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import framework.base.FrameworkProperties;
import framework.base.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class WebDriverFacade {

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
	public static final int pageTimeOut = Integer.valueOf(FrameworkProperties.getTimeout()).intValue();
	
    public static WebDriver getDriver() {
    	return webDriver.get();
    }

    //region Driver Definition


    /**
     * This method is used to initialize the driver.
     */
    public static void createDriver(String size){
    	final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        try {
            URL url = new URL(URL_STRING);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        switch (FrameworkProperties.getBrowser().toUpperCase()){
            case "FIREFOX":
                firefoxDriverInitialize();
                break;
            case "CHROME":
                chromeDriverInitialize();
                break;
            case "EDGE":
                edgeDriverInitialize();
                break;
            default:
                throw new IllegalArgumentException(String.format("The selected driver %s is not supported", FrameworkProperties.getBrowser()));
        }
        switch (size.toUpperCase()){
                case "SMALL":
                    resizeWindows(400, 600);
                    break;
                case "MEDIUM":
                    resizeWindows(768, 1024);
                    break;
                case "LARGE":
                    resizeWindows(1280, 1024);
                    break;
                case "FULL":
                    maximizeWindows();
                    break;
                default:
                    throw new IllegalArgumentException(String.format("The size %s is not supported", size));
        }
    }

    /**
     * This method is used to initialize the Firefox driver.
     */
    public static void firefoxDriverInitialize(){
        WebDriverManager.firefoxdriver().setup();
        webDriver.set(new FirefoxDriver());
    }

    /**
     * This method is used to initialize the Chrome driver.
     */
    public static void chromeDriverInitialize(){
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("test-type");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("no-sandbox");
        webDriver.set(new ChromeDriver(chromeOptions));
    }

    /**
     * This method is used to initialize the Microsoft EDGE driver.
     */
    public static void edgeDriverInitialize(){
    	WebDriverManager.edgedriver().setup();
        webDriver.set(new EdgeDriver());
    }

    //endregion

 
    /**
     * This method is used to maximize the browser windows.
     */
    public static void maximizeWindows(){
    	getDriver().manage().window().maximize();
    }

    /**
     * This method is used to resize the browser windows.
     */
    public static void resizeWindows(int width, int height){
        Dimension resolution = new Dimension(width, height);
        getDriver().manage().window().setSize(resolution);
    }

    /**
     * This method is used to refresh the current windows.
     */
    public static void refreshCurrentWindow(int secondsToWait){
    	getDriver().navigate().refresh();
    }

    /**
     * This method is used to navigate to the previous windows.
     */
    public static void clickNavigateBackButton(int secondsToWait){
    	getDriver().navigate().back();
    }
    
    /**
     * This method is used to take a screenshot.
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
     * This method is used to close the current windows.
     */
    public static void closeCurrentWindow(){
        getDriver().close();
    }

    /**
     * This method is used to close the entire driver.
     */
    public static void shutDown(){
        getDriver().manage().deleteAllCookies();
        getDriver().quit();
    }

    //endregion

    //region Driver Get Methods

    /**
     * This method is used to get the current page URL.
	 * @return String
     */
    public static String getPageURL() { return getDriver().getCurrentUrl();}

    /**
     * This method is used to get the current page title.
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
	 * Find Element for Mobile
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
	 * Find Elements for Mobile
	 * @param locator 
	 * @param timeOut 
	 * @param visibility
	 * @return A List of MobileElement
	 * @author carlos.cadena
	 */
	public static List<WebElement> findElements(By locator, int timeOut, boolean visibility) {
		return Utils.findElements(getDriver(), locator, pageTimeOut, visibility);
    }
	
	/**
	 * This method is used to check if an element is present with timeout
	 */
	public static boolean isElementPresent(By locator) {
		return Utils.isElementPresent(getDriver(), locator, pageTimeOut);
	}

	public static boolean isElementPresent(By locator, int timeout) {
		return Utils.isElementPresent(getDriver(), locator, timeout);
	}

	public static boolean isElementEnabled(WebElement element, int timeout) {
		return Utils.isElementEnabled(getDriver(), element, timeout);
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
}