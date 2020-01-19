package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.base.WebDriverFacade;
import framework.report.Log;

/**
 * The Class ZopaHomePage.
 */
public class ZopaHomePage extends ZopaBasePage {

	/** The my account button locator. */
	private final String myAccountButtonLocator = "a[href*='zopa.com'][data-automation='ZA.Sign_in.topBar.Menu']";
	
	/** The loans button. */
    @FindBy(css = "a[data-automation='ZA.button-Loans'] span")
	private WebElement loansButton;
    
    
	/**
	 * Instantiates a new zopa home page.
	 */
	public ZopaHomePage() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see framework.base.BasePage#setMainLocator()
	 */
	@Override
	public By setMainLocator() {
		return By.cssSelector(myAccountButtonLocator);
	}
	
	/**
	 * Click get personalized rates.
	 *
	 * @return the loans page
	 */
	public LoansPage clickGetPersonalizedRates() {
		WebDriverFacade.click(loansButton);
		Log.testStep("Navigating to Loans Page");
		return new LoansPage();
	}



}