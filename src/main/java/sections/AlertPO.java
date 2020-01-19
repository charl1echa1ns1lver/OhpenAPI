package sections;

import org.openqa.selenium.By;

import framework.base.WebDriverFacade;
import framework.base.WebPageObject;
import framework.report.Log;

public class AlertPO extends WebPageObject {
    
    private By cookiesAcceptAllButton = By.cssSelector("button[title='Accept All']");


	public AlertPO(By locatorContainer) {
		super(locatorContainer);
	}
	
	/**
	 * Click accept all cookies.
	 */
	public void clickAcceptAllCookies() {
		if(WebDriverFacade.isElementVisible(container, cookiesAcceptAllButton, 5)){
			WebDriverFacade.click(WebDriverFacade.findElement(container, cookiesAcceptAllButton, true));
			Log.logger.info("Accepting cookies..");
		}	
	}
	

}
