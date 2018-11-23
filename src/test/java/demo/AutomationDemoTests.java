package demo;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import dto.Booking;
import framework.base.FrameworkProperties;
import framework.base.WebDriverFacade;
import framework.test.TestBase;
import pages.CompleteBookingPage;
import pages.HomePage;
import pages.HomePage.Product;
import pages.InvoicePage;
import pages.SearchFlightPage;

public class AutomationDemoTests extends TestBase {
	

    @Parameters({"departure","destination"})
    @Test( 	groups = {"demo"},
            priority = 1,
            testName="Purchase Article Test",
            description=" Purchase article test with different articles" )
    public void testFlightBooking(String departure, String destination) throws InstantiationException, IllegalAccessException{
    	Booking booking = new Booking();
    	HomePage home = WebDriverFacade.navigateTo(HomePage.class,FrameworkProperties.getBaseUrl());
    	home.selectProduct(Product.FLIGHTS);
    	Assert.assertTrue(home.isProductSelected(Product.FLIGHTS));
    	home.enterDepature(departure);
    	home.enterDestination(destination);
    	Calendar travelDate = Calendar.getInstance();
    	travelDate.add(Calendar.DAY_OF_MONTH, 20);
    	home.enterDate(travelDate.getTime());
    	SearchFlightPage flight = home.clickSearch();
    	booking.setTicketPrice(flight.getFirstFlightPrice());
    	CompleteBookingPage bookPage = flight.selectFirstAvailableFlight();
    	booking.setTicketPrice(bookPage.getFlightCost(lastName));
    	bookPage.enterFirstName(booking.getFirstName());
    	bookPage.enterLastName(booking.getLastName());
    	bookPage.enterEmail(booking.getMail());
    	bookPage.enterAddress(booking.getAddress());
    	bookPage.enterPhone(booking.getMobile());
    	bookPage.enterCountry(booking.getCountry());
    	InvoicePage invoice = bookPage.clickConfirmThisBooking();
    	invoice.clickPayOnArrival();
    	Assert.assertNotNull(invoice);
    }

}
