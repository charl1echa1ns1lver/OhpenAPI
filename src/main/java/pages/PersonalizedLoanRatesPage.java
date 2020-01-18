package pages;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework.base.BasePage;
import framework.base.WebDriverFacade;
import framework.report.Log;
import utils.Constants.EmploymentStatus;
import utils.Constants.LoanFor;
import utils.Constants.ResidentialStatus;
import utils.Constants.Title;

// TODO: Auto-generated Javadoc
/**
 * The Class PersonalizedLoanRatesPage.
 */
public class PersonalizedLoanRatesPage extends BasePage {

	/** The my account button locator. */
	private final String myAccountButtonLocator = "a[href*='zopa.com'][data-automation='ZA.Sign_in.topBar.Menu']";
	
	/** The loans button. */
	@FindBy(css = "a[data-automation='ZA.Sign_in.topBar.Menu']")
	private WebElement loansButton;
	
    /** The what is loan for radio. */
    @FindBy(css = "input[name = 'loanPurpose']")
	private List<WebElement> whatIsLoanForRadio;
    
    /** The email text field. */
    @FindBy(id = "text-id-email")
	private WebElement emailTextField;
	
    /** The title radio. */
    @FindBy(name = "title")
	private List<WebElement> titleRadio;
    
    /** The first name field. */
    @FindBy(name = "firstName")
    private WebElement firstNameField;
    
    /** The last name field. */
    @FindBy(name = "lastName")
    private WebElement lastNameField;
    
    /** The day birth. */
    @FindBy(id = "text-id-day")
    private WebElement dayBirth;
    
    /** The month birth. */
    @FindBy(id = "text-id-month")
    private WebElement monthBirth;
   
    /** The year birth. */
    @FindBy(id = "text-id-year")
    private WebElement yearBirth;
    
    /** The phone field. */
    @FindBy(id = "text-id-phone")
    private WebElement phoneField;
    
    /** The employment status radio. */
    @FindBy(name = "title")
	private List<WebElement> employmentStatusRadio;
    
    /** The annual income field. */
    @FindBy(id = "text-id-annualIncome")
    private WebElement annualIncomeField;
    
    /** The residental status radio. */
    @FindBy(css = "input[name = 'residentialStatus']")
	private List<WebElement> residentalStatusRadio;

    /** The post code field. */
    @FindBy(id = "text-id-postcode")
    private WebElement postCodeField;

    /** The look up addressbutton. */
    @FindBy(css = "button[data-automation = 'ZA.addressLookup']")
    private WebElement lookUpAddressbutton;
        
    /**
     * Instantiates a new loans page.
     */
    public PersonalizedLoanRatesPage() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see framework.base.BasePage#setMainLocator()
	 */
	@Override
	public By setMainLocator() {
		return By.cssSelector(myAccountButtonLocator);
	}
	
	/**
	 * Sets the loan for.
	 *
	 * @param option the new loan for
	 */
	public void setLoanFor(LoanFor option) {
		Optional<WebElement> radio = whatIsLoanForRadio.stream().filter(opt -> opt.getText().equalsIgnoreCase(option.getOptionName())).findFirst();
		if(radio.isPresent())
			WebDriverFacade.click(radio.get());
		else
			throw new NoSuchElementException("No Loan For option of name " + option.getOptionName() + " was found");
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		emailTextField.sendKeys(email);
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(Title title) {
		Optional<WebElement> radio = titleRadio.stream().filter(opt -> opt.getText().equalsIgnoreCase(title.getOptionName())).findFirst();
		if(radio.isPresent())
			WebDriverFacade.click(radio.get());
		else
			throw new NoSuchElementException("No Title of name " + title.getOptionName() + " was found");
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		lastNameField.sendKeys(lastName);
	}
	
	/**
	 * Sets the day of birth.
	 *
	 * @param day the new day of birth
	 */
	public void setDayOfBirth(String day) {
		dayBirth.sendKeys(day);
	}
	
	/**
	 * Sets the month of birth.
	 *
	 * @param month the new month of birth
	 */
	public void setMonthOfBirth(String month) {
		monthBirth.sendKeys(month);
	}
	
	/**
	 * Sets the year of birth.
	 *
	 * @param year the new year of birth
	 */
	public void setYearOfBirth(String year) {
		monthBirth.sendKeys(year);
	}
	
	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		phoneField.sendKeys(phone);
	}
	
	/**
	 * Sets the employment status.
	 *
	 * @param status the new employment status
	 */
	public void setEmploymentStatus(EmploymentStatus status) {
		Optional<WebElement> radio = employmentStatusRadio.stream().filter(opt -> opt.getText().equalsIgnoreCase(status.getOptionName())).findFirst();
		if(radio.isPresent())
			WebDriverFacade.click(radio.get());
		else
			throw new NoSuchElementException("No Employment Status of name " + status.getOptionName() + " was found");
	}
	
	/**
	 * Sets the annual income.
	 *
	 * @param income the new annual income
	 */
	public void setAnnualIncome(String income) {
		annualIncomeField.sendKeys(income);
	}
	
	/**
	 * Sets the home status.
	 *
	 * @param status the new home status
	 */
	public void setHomeStatus(ResidentialStatus status) {
		Optional<WebElement> radio = residentalStatusRadio.stream().filter(opt -> opt.getText().equalsIgnoreCase(status.getOptionName())).findFirst();
		if(radio.isPresent())
			WebDriverFacade.click(radio.get());
		else
			throw new NoSuchElementException("No Residental Status of name " + status.getOptionName() + " was found");
	}

	/**
	 * Sets the postcode.
	 *
	 * @param postCode the new postcode
	 */
	public void setPostcode(String postCode) {
		postCodeField.sendKeys(String.valueOf(postCode));		
	}
	
	/**
	 * Click look up address.
	 */
	public void clickLookUpAddress() {
		WebDriverFacade.click(lookUpAddressbutton);		
	}


	
}
