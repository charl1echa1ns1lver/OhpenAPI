package demo;
import org.testng.annotations.Test;

import dto.ZopaUser;
import framework.base.FrameworkProperties;
import framework.base.WebDriverFacade;
import framework.test.TestBase;
import pages.ZopaHomePage;
import pages.PersonalizedLoanRatesPage;
import pages.LoansPage;

public class GetPersonalizedRatesTests extends TestBase {
	
    @Test( 	groups = {"demo"},
            priority = 1,
            testName="happy path",
            description="borrower happy path" )
    public void testHappyPath() throws InstantiationException, IllegalAccessException{
    	ZopaUser borrower = new ZopaUser();
    	ZopaHomePage home = WebDriverFacade.navigateTo(ZopaHomePage.class,FrameworkProperties.getBaseUrl());
    	LoansPage loans = home.clickGetPersonalizedRates();
    	loans.selectLoanYears(borrower.getYearsLoan());
    	PersonalizedLoanRatesPage loanRates = loans.clickGetMyPersonalizedRates();
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
    	loanRates.setPostcode(borrower.getPostcode());
    	loanRates.clickLookUpAddress();
    }

}
