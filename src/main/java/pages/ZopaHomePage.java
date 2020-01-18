package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import framework.base.BasePage;
import framework.base.WebDriverFacade;

/**
 * The Class ZopaHomePage.
 */
public class ZopaHomePage extends BasePage {

	/** The my account button locator. */
	private final By myAccountButtonLocator = By.cssSelector("a[href*='zopa.com'][data-automation='ZA.Sign_in.topBar.Menu']");
	
	/** The loans button. */
    @FindBy(how = How.CSS, using = "a[data-automation='ZA.button-Loans'] span")
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
		return myAccountButtonLocator;
	}
	
	
	/**
	 * Click get personalized rates.
	 *
	 * @return the loans page
	 */
	public LoansPage clickGetPersonalizedRates() {
		WebDriverFacade.click(loansButton);
		return new LoansPage();
	}
	


}