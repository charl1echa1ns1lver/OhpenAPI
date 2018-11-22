package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.base.BasePage;
import framework.base.WebDriverFacade;

public class SearchFlightPage extends BasePage {
	
	private final String buttonSearchFlightLocator = "div.search_head button[type='submit']";
	private final String containerFlights = "table[class*='flight-listing'] tr";
	private final String buttonBookFlight = "button#bookbtn";

	
	@FindBy(css = buttonSearchFlightLocator)
	private WebElement buttonSearchFlight;
	
	private List<WebElement> allFlights() {
		return WebDriverFacade.findElements(By.cssSelector(containerFlights));
	}
	

	protected SearchFlightPage() {
		super();
	}

	@Override
	public By setMainLocator() {
		// TODO Auto-generated method stub
		return By.cssSelector(buttonSearchFlightLocator);
	}
	
	public CompleteBookingPage selectFirstAvailableFlight() {
		WebDriverFacade.findElement(allFlights().get(0), By.cssSelector(buttonBookFlight), true).click();
		return new CompleteBookingPage();
	}

}
