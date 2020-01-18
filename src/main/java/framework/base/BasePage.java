package framework.base;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import framework.report.Log;

public abstract class BasePage {
	
	private By mainLocator;
	
	public abstract By setMainLocator();

	public BasePage() {
		mainLocator = setMainLocator();
		Log.logger = LogManager.getLogger(getClass());
		String pageError = "Page did not load after waiting for " + WebDriverFacade.pageTimeOut
				+ " seconds for main element to be present";
		if (WebDriverFacade.isElementPresent(mainLocator)) {
			PageFactory.initElements(WebDriverFacade.getDriver(), this.getClass());
		} else {
			Log.testFail(pageError);
			throw new NoSuchElementException(pageError);
		}
	}

}
