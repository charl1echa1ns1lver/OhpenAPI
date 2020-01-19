package pages;

import org.openqa.selenium.By;

import framework.base.BasePage;
import framework.base.WebDriverFacade;
import sections.AlertPO;

public class ZopaBasePage extends BasePage {
	
	private final String zopaLogo = "a[data-automation='Zopa.topBar.Icon']";
	private final By alertFooter = By.cssSelector("div[role ='alertdialog']");
	

	public AlertPO getAlert() {
		return new AlertPO(alertFooter);
	}

	@Override
	public By setMainLocator() {
		return By.cssSelector(zopaLogo);
	}
	
	
	/**
	 * Click accept cookies.
	 */
	public void clickAcceptAllCookies() {
		if (WebDriverFacade.isElementVisible(alertFooter, 5)) {
			getAlert().clickAcceptAllCookies();
		}
	}
	

}
