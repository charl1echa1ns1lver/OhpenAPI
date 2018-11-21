package framework.base;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import framework.report.Log;

public class BasePage {

	
	 protected BasePage(){
	        PageFactory.initElements(WebDriverFacade.getDriver(), this);
	        Log.logger = LogManager.getLogger(getClass());
	    }
	    
		protected BasePage(By locator) {
			Log.logger = LogManager.getLogger(getClass());
			String pageError = "Page did not load after waiting for " + WebDriverFacade.pageTimeOut
					+ " seconds for main element to be present";
			if (WebDriverFacade.isElementPresent(locator)) {
		        PageFactory.initElements(WebDriverFacade.getDriver(), this);
			} else {
				Log.testFail(pageError);
				throw new NoSuchElementException(pageError);
			}
		}

}
