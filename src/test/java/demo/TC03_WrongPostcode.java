package demo;

import org.apache.commons.lang3.RandomStringUtils;
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

public class TC03_WrongPostcode extends TestBase {
	
    @Test( 	groups = {"demo"},
            priority = 1,
            testName="TC03_wrong_postcode",
            description="Borrower date should be more over 20 years old" )
    public void wrongPostCode() throws InstantiationException, IllegalAccessException{
    	ZopaUser borrower = getBorrower();
    	String randomPostcode = "XXX" + RandomStringUtils.randomAlphanumeric(3);
    	borrower.setPostCode(randomPostcode);
       	Log.testStep("Given a user with an incorrect postcode like > " + randomPostcode);
    	ZopaHomePage home = WebDriverFacade.navigateTo(ZopaHomePage.class,FrameworkProperties.getBaseUrl());
    	home.clickAcceptAllCookies();
    	Log.testStep("When navigating to Loans Page");
    	LoansPage loans = home.clickGetPersonalizedRates();
    	loans.selectLoanYears(borrower.getYearsLoan());
    	Log.testStep("And navigating to Personalized Rates Page");
    	PersonalizedLoanRatesPage loanRates = loans.clickGetMyPersonalizedRates();
    	loanRates.clickAcceptAllCookies();
    	Log.testStep("And entering all info on fields until reaching postcode");
    	loanRates.setLoanFor(borrower.getLoanFor());
    	loanRates.setEmail(borrower.getMail());
    	loanRates.setTitle(borrower.getTitle());
    	loanRates.setFirstName(borrower.getFirstName());
    	loanRates.setLastName(borrower.getLastName());
    	loanRates.setDayOfBirth(borrower.getDayOfBirth());
    	loanRates.setMonthOfBirth(borrower.getMonthOfBirth());
    	loanRates.setYearOfBirth(borrower.getYearOfBirth());
    	loanRates.setPhone(borrower.getPhoneNumber());
    	loanRates.setEmploymentStatus(borrower.getEmploymentStatus());
    	loanRates.setAnnualIncome(borrower.getAnnualIncome());
    	loanRates.setHomeStatus(borrower.getHomeStatus());
    	Log.testStep("When entering an invalid postcode");
    	loanRates.setPostcode(borrower.getPostcode());
    	loanRates.clickLookUpAddress();
    	setBorrower(borrower);
    	Log.testStep("Then an error message is displayed > 'Oops, this postcode doesn't look quite right");
    	Assert.assertTrue(loanRates.isPostCodeErrorDisplayed());
    	Assert.assertEquals("Oops, this postcode doesn't look quite right", loanRates.getPostCodeErrorText());
    }

}