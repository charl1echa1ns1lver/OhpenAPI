package demo;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import framework.base.FrameworkProperties;
import framework.base.WebDriverFacade;
import framework.test.TestBase;
import pages.CompleteBookingPage;
import pages.HomePage;
import pages.SearchFlightPage;

public class AutomationDemoTests extends TestBase {
	

    @Parameters({"departure","destination"})
    @Test( 	groups = {"demo"},
            priority = 1,
            testName="Purchase Article Test",
            description=" Purchase article test with different articles" )
    public void testArticlePurchase(String departure, String destination) throws InstantiationException, IllegalAccessException{
    	HomePage home = WebDriverFacade.navigateTo(HomePage.class,FrameworkProperties.getBaseUrl());
    	home.enterDepature(departure);
    	home.enterDestination(destination);
    	Calendar travelDate = Calendar.getInstance();
    	travelDate.add(Calendar.DAY_OF_MONTH, 20);
    	home.enterDate(travelDate.getTime());
    	SearchFlightPage flight = home.clickSearch();
    	CompleteBookingPage bookPage = flight.selectFirstAvailableFlight();
    	Assert.assertNotNull(bookPage);
    }

}
