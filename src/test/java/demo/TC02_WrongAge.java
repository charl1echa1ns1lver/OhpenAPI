package demo;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import dto.ZopaUser;
import framework.base.FrameworkProperties;
import framework.base.WebDriverFacade;
import framework.report.Log;
import framework.test.TestBase;
import pages.LoansPage;
import pages.PersonalizedLoanRatesPage;
import pages.ZopaHomePage;
import utils.TestUtils;

public class TC02_WrongAge extends TestBase {
	
	  @Test( 	groups = {"demo"},
	            priority = 1,
	            testName="TC02_wrong_age",
	            description="Borrower date should be more over 20 years old" )
	    public void wrongDate() throws InstantiationException, IllegalAccessException{
	    	Log.testStep("Given a user with age under 20 for a loan");
	    	ZopaUser borrower = getBorrower();
	    	LocalDate date20 = TestUtils.randomDate(LocalDate.now().minusYears(20), LocalDate.now());
	    	borrower.setDayOfBirth(String.valueOf(date20.getDayOfMonth()));
	    	borrower.setMonthOfBirth(String.valueOf(date20.getMonthValue()));
	    	borrower.setYearOfBirth(String.valueOf(date20.getYear()));
	    	ZopaHomePage home = WebDriverFacade.navigateTo(ZopaHomePage.class,FrameworkProperties.getBaseUrl());
	    	home.clickAcceptAllCookies();
	    	Log.testStep("When navigating to Loans Page");
	    	LoansPage loans = home.clickGetPersonalizedRates();
	    	loans.selectLoanYears(borrower.getYearsLoan());
	    	Log.testStep("And navigating to Personalized Rates Page");
	    	PersonalizedLoanRatesPage loanRates = loans.clickGetMyPersonalizedRates();
	    	loanRates.clickAcceptAllCookies();
	    	Log.testStep("And entering all info on fields until reaching birth information");
	    	loanRates.setLoanFor(borrower.getLoanFor());
	    	loanRates.setEmail(borrower.getMail());
	    	loanRates.setTitle(borrower.getTitle());
	    	loanRates.setFirstName(borrower.getFirstName());
	    	loanRates.setLastName(borrower.getLastName());
	    	Log.testStep("When entering a day of birth for a borrower under 20 years");
	    	loanRates.setDayOfBirth(borrower.getDayOfBirth());
	    	loanRates.setMonthOfBirth(borrower.getMonthOfBirth());
	    	loanRates.setYearOfBirth(borrower.getYearOfBirth());
	    	loanRates.setPhone(borrower.getPhoneNumber());
	    	setBorrower(borrower);
	    	Log.testStep("Then an error message is displayed > 'You need to be at least 20 years old to apply for a Zopa loan'");
	    	Assert.assertTrue(loanRates.isDateErrorDisplayed());
	    	Assert.assertEquals("You need to be at least 20 years old to apply for a Zopa loan", loanRates.getDateErrorText());
	    	
	    }

}
