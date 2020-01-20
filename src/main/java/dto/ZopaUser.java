package dto;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;

import utils.ApiUtils;
import utils.Constants.EmploymentStatus;
import utils.Constants.LoanFor;
import utils.Constants.ResidentialStatus;
import utils.Constants.Title;
import utils.TestUtils;

public class ZopaUser {
	
	private String yearsLoan;
	private String firstName;
	private String lastName;
	private String mail;
	private String annualIncome;
	private String postCode;
	private String rent;
	private EmploymentStatus employmentStatus;
	private ResidentialStatus homeStatus;
	private LoanFor loanFor;
	private Title title;
	private String phoneNumber;
	private String dayOfBirth;
	private String monthOfBirth;
	private String yearOfBirth;
	
	private String address;
	private String monthMoveIn;
	private String yearMoveIn;
	
	private String beforeMonthMoveIn;
	private String beforePostCode;
	private String beforeYearMoveIn;
	private String beforeAddress;
	
	
	public ZopaUser() {
		yearsLoan = TestUtils.randomBetween(1,5);
		firstName = RandomStringUtils.randomAlphabetic(10);
		lastName = RandomStringUtils.randomAlphabetic(10);
		loanFor = TestUtils.getRandomElementFromList(Arrays.asList(LoanFor.values()));

		LocalDate dateOfBirth = TestUtils.randomDate(LocalDate.now().minusYears(100), LocalDate.now().minusYears(20));
		
		dayOfBirth = String.valueOf(dateOfBirth.getDayOfMonth());
		monthOfBirth = String.valueOf(dateOfBirth.getMonthValue());
		yearOfBirth = String.valueOf(dateOfBirth.getYear());

		mail = RandomStringUtils.randomAlphabetic(5) + "@randommail.com";
		phoneNumber =  RandomStringUtils.randomNumeric(10);
		annualIncome = RandomStringUtils.randomNumeric(6);
		homeStatus = TestUtils.getRandomElementFromList(Arrays.asList(ResidentialStatus.values()));
		if(!homeStatus.equals(ResidentialStatus.OWNER)) {
			rent = String.valueOf(Math.round((Integer.valueOf(annualIncome) / 12) * 0.3));
		}
		else {
			rent = "";
		}
		employmentStatus = TestUtils.getRandomElementFromList(Arrays.asList(EmploymentStatus.values()));
		title = TestUtils.getRandomElementFromList(Arrays.asList(Title.values()));
		
		LocalDate previousHome = TestUtils.randomDate(dateOfBirth.plusYears(20), LocalDate.now().minusMonths(6));
		LocalDate currentHome = TestUtils.randomDate(previousHome.plusDays(1), LocalDate.now().minusDays(1));

		postCode = ApiUtils.getRandomZipCode();
		beforePostCode = ApiUtils.getRandomZipCode();
		
		monthMoveIn =currentHome.getMonth().getDisplayName(TextStyle.FULL, Locale.UK);
		beforeMonthMoveIn =previousHome.getMonth().getDisplayName(TextStyle.FULL, Locale.UK);
		
		yearMoveIn = String.valueOf(currentHome.getYear());
	    beforeYearMoveIn = String.valueOf(previousHome.getYear());

		address = "";
		beforeAddress = "";
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public EmploymentStatus getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(EmploymentStatus employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public ResidentialStatus getHomeStatus() {
		return homeStatus;
	}

	public void setHomeStatus(ResidentialStatus homeStatus) {
		this.homeStatus = homeStatus;
	}

	public LoanFor getLoanFor() {
		return loanFor;
	}

	public void setLoanFor(LoanFor loanFor) {
		this.loanFor = loanFor;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(String dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public String getMonthOfBirth() {
		return monthOfBirth;
	}

	public void setMonthOfBirth(String monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}

	public String getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(String yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getPostcode() {
		return postCode;
	}

	public void setZipCode(String zipCode) {
		this.postCode = zipCode;
	}

	public String getYearsLoan() {
		return yearsLoan;
	}

	public void setYearsLoan(String yearsLoan) {
		this.yearsLoan = yearsLoan;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMonthMoveIn() {
		return monthMoveIn;
	}

	public void setMonthMoveIn(String monthMoveIn) {
		this.monthMoveIn = monthMoveIn;
	}

	public String getYearMoveIn() {
		return yearMoveIn;
	}

	public void setYearMoveIn(String yearMoveIn) {
		this.yearMoveIn = yearMoveIn;
	}

	public String getBeforeMonthMoveIn() {
		return beforeMonthMoveIn;
	}

	public void setBeforeMonthMoveIn(String beforeMonthMoveIn) {
		this.beforeMonthMoveIn = beforeMonthMoveIn;
	}

	public String getBeforePostCode() {
		return beforePostCode;
	}

	public void setBeforePostCode(String beforePostCode) {
		this.beforePostCode = beforePostCode;
	}

	public String getBeforeYearMoveIn() {
		return beforeYearMoveIn;
	}

	public void setBeforeYearMoveIn(String beforeYearMoveIn) {
		this.beforeYearMoveIn = beforeYearMoveIn;
	}

	public String getBeforeAddress() {
		return beforeAddress;
	}

	public void setBeforeAddress(String beforeAddress) {
		this.beforeAddress = beforeAddress;
	}

	public String getRent() {
		return rent;
	}

	public void setRent(String rent) {
		this.rent = rent;
	}


}
