package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.base.BasePage;
import framework.base.WebDriverFacade;

public class InvoicePage extends BasePage {
	
	private final String buttonPayNowLocator = "button[data-target='#pay']";

	@FindBy(css = "button[class*='arrival']")
	private WebElement buttonPayOnArrival;
	
	@FindBy(xpath = "//table[contains(@class,'bordered')]//td[contains(text(),'USD')][3]")
	private WebElement textTotalAmountPrice;
	
	@FindBy(xpath = "//div[contains(text(),'Customer Details')]//parent::td//div[2]")
	private WebElement textFirstAndLastName;
	
	@FindBy(xpath = "//div[contains(text(),'Customer Details')]//parent::td//div[3]")
	private WebElement textAddress;
	
	@FindBy(xpath = "//div[contains(text(),'Customer Details')]//parent::td//div[4]")
	private WebElement textPhone;
	
	
	@Override
	public By setMainLocator() {
		return By.cssSelector(buttonPayNowLocator);
	}
	
	public void clickPayOnArrival() {
		WebDriverFacade.waitForElementVisibility(buttonPayOnArrival);
		buttonPayOnArrival.click();
		WebDriverFacade.acceptAlert();
	}
	
	public String getTotalAmountPrice() {
		WebDriverFacade.waitForElementVisibility(textFirstAndLastName);
		return textTotalAmountPrice.getText().replace("USD", "").replace("$", "").trim();
	}
	
	public String getFirstAndLastName() {
		WebDriverFacade.waitForElementVisibility(textFirstAndLastName);
		return textFirstAndLastName.getText().trim();
	}
	
	public String getAddress() {
		WebDriverFacade.waitForElementVisibility(textAddress);
		return textAddress.getText().trim();
	}
	
	public String getPhone() {
		WebDriverFacade.waitForElementVisibility(textPhone);
		return textPhone.getText().trim();
	}

}
