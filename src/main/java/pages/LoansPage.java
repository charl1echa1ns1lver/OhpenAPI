package pages;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.base.BasePage;
import framework.base.WebDriverFacade;
import framework.report.Log;
import utils.Constants.EmploymentStatus;
import utils.Constants.ResidentialStatus;
import utils.Constants.LoanFor;
import utils.Constants.Title;

/**
 * The Class LoansPage.
 */
public class LoansPage extends BasePage {
	
	/** The radio button years. */
	private final String radioButtonYears = "div input[data-automation='ZA.loan-select-%s']";
	
	/** The loans text. */
	private final String loansText = "(//label[text() = 'I want to get a loan for:'])[1]";	

	/** The button get my personalized rates. */
	@FindBy(xpath = "(//a[@data-automation='ZA.button-personal-rate'])[1]")
	private WebElement buttonGetMyPersonalizedRates;


	/* (non-Javadoc)
	 * @see framework.base.BasePage#setMainLocator()
	 */
	@Override
	public By setMainLocator() {
		return By.xpath(loansText);
	}
	
	/**
	 * Select loan years.
	 *
	 * @param year the year
	 */
	public void selectLoanYears(String year) {
		WebElement yearRadioButton = WebDriverFacade.findElement(By.xpath(String.format(radioButtonYears, year)));
		WebDriverFacade.scrollIntoView(yearRadioButton);
		WebDriverFacade.click(yearRadioButton);
		Log.testStep(String.format("Selecting %s year", year));
	}
	
	/**
	 * Click get my personalized rates.
	 *
	 * @return the loans page
	 */
	public PersonalizedLoanRatesPage clickGetMyPersonalizedRates() {
		WebDriverFacade.scrollIntoView(buttonGetMyPersonalizedRates);
		WebDriverFacade.click(buttonGetMyPersonalizedRates);
		Log.testStep("Clicking on 'Get My Personalized Rates");
		return new PersonalizedLoanRatesPage();
	}
}