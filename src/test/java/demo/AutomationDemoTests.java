package demo;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

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
    	SoftAssert assertions = new SoftAssert();
    	Booking booking = new Booking();
    	HomePage home = WebDriverFacade.navigateTo(HomePage.class,FrameworkProperties.getBaseUrl());
    	home.selectProduct(Product.FLIGHTS);
    	Assert.assertTrue(home.isProductSelected(Product.FLIGHTS), "Checking that Flights section is selected on Home page");
    	report.get().log(Status.PASS, "Checking that Flights section is selected on Home page");
    	home.enterDepature(departure);
    	home.enterDestination(destination);
    	Calendar travelDate = Calendar.getInstance();
    	travelDate.add(Calendar.DAY_OF_MONTH, 20);
    	home.enterDate(travelDate.getTime());
    	SearchFlightPage flight = home.clickSearch();
    	CompleteBookingPage bookPage = flight.selectFirstAvailableFlight();
    	booking.setTicketPrice(bookPage.getFlightCost());
    	bookPage.enterFirstName(booking.getFirstName());
    	bookPage.enterLastName(booking.getLastName());
    	bookPage.enterEmail(booking.getMail());
    	bookPage.enterAddress(booking.getAddress());
    	bookPage.enterPhone(booking.getMobile());
    	bookPage.enterCountry(booking.getCountry());
    	InvoicePage invoice = bookPage.clickConfirmThisBooking();
    	invoice.clickPayOnArrival();
    	String total = invoice.getTotalAmountPrice();
    	assertions.assertEquals("Checking First And Last Name are the same on Customer Details Invoice Section vs Booking Info entered", booking.getFirstName() + " " + booking.getLastName(), invoice.getFirstAndLastName());
    	report.get().log(Status.INFO, "Checking First And Last Name are the same on Customer Details Invoice Section vs Booking Info entered");
    	assertions.assertEquals("Checking Address is the same on Customer Details Invoice Section vs Booking Info entered", booking.getAddress(), invoice.getAddress());
    	report.get().log(Status.INFO, "Checking Address is the same on Customer Details Invoice Section vs Booking Info entered");
    	assertions.assertEquals("Checking Phone is the same on Customer Details Invoice Section vs Booking Info entered", booking.getMobile(), invoice.getPhone());
    	report.get().log(Status.INFO, "Checking Phone is the same on Customer Details Invoice Section vs Booking Info entered");
    	assertions.assertEquals("Checking Total Amount on Invoice should be the same that was shown when completing Booking Info", booking.getTicketPrice(), total);
    	report.get().log(Status.INFO, "Checking Total Amount on Invoice should be the same that was shown when completing booking info");
    	assertions.assertAll();
    }

}
