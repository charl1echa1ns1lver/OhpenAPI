package dto;

import java.util.Arrays;

import org.apache.commons.lang3.RandomStringUtils;

import utils.ApiUtils;
import utils.TestUtils;
import utils.Constants.EmploymentStatus;
import utils.Constants.LoanFor;
import utils.Constants.ResidentialStatus;
import utils.Constants.Title;

public class ZopaUser {
	
	private String yearsLoan;
	private String firstName;
	private String lastName;
	private String mail;
	private String annualIncome;
	private String postCode;
	private EmploymentStatus employmentStatus;
	private ResidentialStatus homeStatus;
	private LoanFor loanFor;
	private Title title;
	private String phoneNumber;
	private String dayOfBirth;
	private String monthOfBirth;
	private String yearOfBirth;
	private String zipCode;
	
	
	public ZopaUser() {
		yearsLoan = RandomStringUtils.randomNumeric(1, 5);
		firstName = RandomStringUtils.randomAlphabetic(10);
		lastName = RandomStringUtils.randomAlphabetic(10);

		dayOfBirth = RandomStringUtils.randomAlphabetic(10);
		monthOfBirth = RandomStringUtils.randomAlphabetic(10);
		yearOfBirth = RandomStringUtils.randomAlphabetic(10);

		mail = RandomStringUtils.randomAlphabetic(5) + "@randommail.com";
		phoneNumber = RandomStringUtils.randomNumeric(8);
		annualIncome = RandomStringUtils.randomNumeric(8);
		homeStatus = TestUtils.getRandomElementFromList(Arrays.asList(ResidentialStatus.values()));
		employmentStatus = TestUtils.getRandomElementFromList(Arrays.asList(EmploymentStatus.values()));
		title = TestUtils.getRandomElementFromList(Arrays.asList(Title.values()));
		zipCode = ApiUtils.getRandomZipCode();
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
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getYearsLoan() {
		return yearsLoan;
	}

	public void setYearsLoan(String yearsLoan) {
		this.yearsLoan = yearsLoan;
	}


}
