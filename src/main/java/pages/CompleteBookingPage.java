package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import framework.base.BasePage;
import framework.base.WebDriverFacade;
import framework.report.Log;

public class CompleteBookingPage extends BasePage {
	
	@FindBy(name = "firstname")
	private WebElement textboxFirstName;
	
	@FindBy(name = "lastname")
	private WebElement textboxLastName;
	
	@FindBy(name = "email")
	private WebElement textboxEmail;
	
	@FindBy(name = "confirmemail")
	private WebElement textboxConfirmEmail;
	
	@FindBy(name = "phone")
	private WebElement textboxMobile;
	
	@FindBy(name = "address")
	private WebElement textboxAddress;
	
	@FindBy(css = "div[class*='select2-container']")
	private WebElement inputCountryContainer;
	
	@FindBy(css = "input[class='select2-input']")
	private WebElement inputCountry;
	
	@FindBy(css = "select[class*='chosen']")
	private WebElement selectCountry;
	
	@FindBy(css = "div.form-group button[type='submit']")
	private WebElement buttonConfirmThisBooking;
	
	@FindBy(xpath = "(//td[contains(@class,'booking-deposit')])[3]/following-sibling::td/strong")
	private WebElement spanFlightCost;


	@Override
	public By setMainLocator() {
		return By.name("address");
	}
	
	public String getFlightCost() {
		WebDriverFacade.waitForElementVisibility(spanFlightCost);
		return spanFlightCost.getText().replace("USD", "").replaceAll("$", "").trim();
	}
	
	public void enterLastName(String lastName) {
		WebDriverFacade.waitForElementVisibility(textboxLastName);
		Log.logger.info("Entering Last Name '" + lastName + "' on Booking information");
		textboxLastName.sendKeys(lastName);
	}
	
	public void enterFirstName(String firstName) {
		WebDriverFacade.waitForElementVisibility(textboxFirstName);
		Log.logger.info("Entering First Name '" + firstName + "' on Booking information");
		textboxFirstName.sendKeys(firstName);
	}
	
	public void enterPhone(String mobile) {
		WebDriverFacade.waitForElementVisibility(textboxMobile);
		Log.logger.info("Entering Phone '" + mobile + "' on Booking information");
		textboxMobile.sendKeys(mobile);
	}
	
	
	public void enterEmail(String email) {
		WebDriverFacade.waitForElementVisibility(textboxEmail);
		Log.logger.info("Entering Email '" + email + "' on Booking information");
		textboxEmail.sendKeys(email);
		WebDriverFacade.waitForElementVisibility(textboxConfirmEmail);
		Log.logger.info("Entering Email confirmation '" + email + "' on Booking information");
		textboxConfirmEmail.sendKeys(email);
	}
	
	public void enterAddress(String address) {
		WebDriverFacade.waitForElementVisibility(textboxAddress);
		textboxAddress.sendKeys(address);
	}
	
	public void enterCountry(String country) {
		WebDriverFacade.waitForElementVisibility(inputCountryContainer);
		inputCountryContainer.click();
		WebDriverFacade.waitForElementVisibility(selectCountry);
		Select element = new Select(selectCountry);
		element.selectByVisibleText(country);
	}
	
	public InvoicePage clickConfirmThisBooking() {
		WebDriverFacade.clickJavascript(buttonConfirmThisBooking);
		return new InvoicePage();
	}


}
