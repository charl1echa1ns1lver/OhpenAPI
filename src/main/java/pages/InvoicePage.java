package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.base.BasePage;
import framework.base.WebDriverFacade;

public class InvoicePage extends BasePage {
	
	private final String buttonPayNowLocator = "button[data-target='#pay']";

	@FindBy(css = "button[class*='arrival']")
	private WebElement buttonPayOnArrival;


	@Override
	public By setMainLocator() {
		return By.cssSelector(buttonPayNowLocator);
	}
	
	public void clickPayOnArrival() {
		WebDriverFacade.waitForElementVisibility(buttonPayOnArrival);
		buttonPayOnArrival.click();
		WebDriverFacade.acceptAlert();
	}

}
