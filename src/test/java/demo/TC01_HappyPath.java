package demo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import dto.ZopaUser;
import framework.base.FrameworkProperties;
import framework.base.WebDriverFacade;
import framework.test.TestBase;
import pages.LoansPage;
import pages.PersonalizedLoanRatesPage;
import pages.ZopaHomePage;
import utils.Constants.ResidentialStatus;

public class TC01_HappyPath extends TestBase {
	
    @Test( 	groups = {"demo"},
            priority = 1,
            testName="TC01_Happy_Path",
            description="borrower happy path" )
    public void testHappyPath() throws InstantiationException, IllegalAccessException{
        report.get().log(Status.INFO, "Given a user with random valid set of information for a loan");
        ZopaUser borrower = getBorrower();
    	ZopaHomePage home = WebDriverFacade.navigateTo(ZopaHomePage.class,FrameworkProperties.getBaseUrl());
    	home.clickAcceptAllCookies();
        report.get().log(Status.INFO, "When navigating to Loans Page");
    	LoansPage loans = home.clickGetPersonalizedRates();
    	loans.selectLoanYears(borrower.getYearsLoan());
        report.get().log(Status.INFO, "And navigating to Personalized Rates Page");
    	PersonalizedLoanRatesPage loanRates = loans.clickGetMyPersonalizedRates();
    	loanRates.clickAcceptAllCookies();
        report.get().log(Status.INFO, "And entering borrower personal information");
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
		if (!borrower.getHomeStatus().equals(ResidentialStatus.OWNER)) {
			loanRates.setRent(borrower.getRent());
		}
		String address2 = "";
		String address1 = loanRates.enterAddress(borrower.getPostcode(), borrower.getMonthMoveIn(),
				borrower.getYearMoveIn());
    	borrower.setAddress(address1);
    	setBorrower(borrower);
        report.get().log(Status.INFO, "Then a valid 'Current address' is selected successfully");
    	Assert.assertTrue(loanRates.validateAddress(address1), "'Current Address' was not valid since does not appear on 'Accepted' list");
		if (loanRates.isPreviousAddress()) {
			address2 = loanRates.enterAddress(borrower.getBeforePostCode(), borrower.getBeforeMonthMoveIn(),
					borrower.getBeforeYearMoveIn());
			report.get().log(Status.INFO, "And a valid 'Previous address' is selected successfully");
			borrower.setBeforeAddress(address2);
			setBorrower(borrower);
			Assert.assertTrue(loanRates.validateAddress(address2),
					"'Previous Address' was not valid since does not appear on 'Accepted' list");
		}
        report.get().log(Status.INFO, "And all values are entered with no errors shown on process");
    }
}
