package pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.base.BasePage;
import framework.base.WebDriverFacade;

public class HomePage extends BasePage {

	private final String productLocator = "li[role='presentation'][data-title='%s'] a";
	private final String titlePromoLocator = "section[class*='promo-section']";
	
	@FindBy(css = "input[name='departure']")
	private WebElement dateElement;
	
	@FindBy(css = "div[id='s2id_location_from'] input")
	private WebElement textDeparture;
	
	@FindBy(css = "div[id='s2id_location_to'] input")
	private WebElement textDestination;
	
	@FindBy(xpath = "//span/parent::button[contains(text() , 'Search')]")
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
		String locatorContainer = String.format(productLocator, StringUtils.capitalize(prod.toString().toLowerCase())).replace("a", "").trim();
		return WebDriverFacade.isAttributePresent(By.cssSelector(locatorContainer), "class" , "active", true);
	}
	
	public void selectProduct(Product prod) {
		String locatorProd = String.format(productLocator, StringUtils.capitalize(prod.toString().toLowerCase()));
		WebDriverFacade.findElementIfVisible(By.cssSelector(locatorProd)).click();
	}
	
	public void enterDate(Date date) {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		dateElement.sendKeys(formatDate.format(date));
	}
	
	public void enterDepature(String departure) {
		textDeparture.sendKeys(departure);
		textDeparture.sendKeys(Keys.TAB);
	}
	
	public void enterDestination(String destination) {
		textDestination.sendKeys(destination);
		textDestination.sendKeys(Keys.TAB);
	}
	
	public SearchFlightPage clickSearch() {
		buttonSearch.click();
		return new SearchFlightPage();
	}

		
		
		


}