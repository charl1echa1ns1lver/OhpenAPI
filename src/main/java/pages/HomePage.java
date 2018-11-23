package pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.base.BasePage;
import framework.base.WebDriverFacade;

public class HomePage extends BasePage {

	private final String productLocator = "li[role='presentation'][data-title='%s']";
	private final String titlePromoLocator = "section[class*='promo-section']";
	private final String searchResultLocator = "//ul[@class='select2-results']//span[contains(text(),'%s')]";
	private final String dayActiveLocator = "//td[contains(@class,'active') and text() = '%s']";
	
	@FindBy(css = "input[name='departure']")
	private WebElement dateElement;
	
	@FindBy(css = "div[id='s2id_location_from'] input")
	private WebElement textDeparture;
	
	@FindBy(css = "div[id='s2id_location_to'] input")
	private WebElement textDestination;
	
	@FindBy(css = "form[name*='flight'] button[type*='submit']")
	private WebElement buttonSearch;
		
	public enum Product {
		HOTELS, FLIGHTS, TOURS, CARS, VISA;
	}
	
	public HomePage() {
		super();
	}
	
	@Override
	public By setMainLocator() {
		return By.cssSelector(titlePromoLocator);
	}
	
	public boolean isProductSelected(Product prod) {
		return WebDriverFacade.isAttributePresent(By.cssSelector(String.format(productLocator, prod.toString().toLowerCase())), "class" , "active", true);
	}
	
	public void selectProduct(Product prod) {
		String locatorProd = String.format(productLocator, prod.toString().toLowerCase()) + " a";
		WebDriverFacade.findElementIfVisible(By.cssSelector(locatorProd)).click();
	}
	
	public void enterDate(Date date) {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	 	Calendar travelDate = Calendar.getInstance();
    	travelDate.setTime(date);
    	dateElement.sendKeys(formatDate.format(date));
		WebDriverFacade.findElementIfVisible(By.xpath(String.format(dayActiveLocator, travelDate.get(Calendar.DAY_OF_MONTH)))).click();
	}
	
	public void enterDepature(String departure) {
		WebDriverFacade.waitForElementVisibility(textDeparture);
		textDeparture.sendKeys(departure);
		WebDriverFacade.findElementIfVisible(By.xpath(String.format(searchResultLocator, departure))).click();
	}
	
	public void enterDestination(String destination) {
		WebDriverFacade.waitForElementVisibility(textDestination);
		textDestination.sendKeys(destination);
		WebDriverFacade.findElementIfVisible(By.xpath(String.format(searchResultLocator, destination))).click();
	}
	
	public SearchFlightPage clickSearch() {
		buttonSearch.click();
		return new SearchFlightPage();
	}

		
		
		


}