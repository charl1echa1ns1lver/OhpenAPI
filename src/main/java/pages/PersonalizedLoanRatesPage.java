package pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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
public class PersonalizedLoanRatesPage extends ZopaBasePage {

	/** The my account button locator. */
	private final String loanAmount = "span[data-automation='ZA.title.loanAmount']";
	
	/** The locator what is loan. */
	private final String locatorWhatIsLoan = "//input[@name='loanPurpose']/following-sibling::label[text() = '%s']";
	
	/** The locator title. */
	private final String locatorTitle = "//input[@name='title']/following-sibling::label[text() = '%s']";
	
	/** The locator residencial status. */
	private final String locatorResidencialStatus = "//input[@name='residentialStatus']/following-sibling::label[text() = '%s']";
	
	/** The locator employment status. */
	private final String locatorEmploymentStatus = "//input[@name='employmentStatus']/following-sibling::label[text() = '%s']";
	
	/** The locator address added. */
	private final String locatorAddressAdded = "//div[@id='address-history']//div[contains(text() , '%s')]";
	
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
    
	/**  error Date Of Birth. */
    @FindBy(css = "span[data-automation='ZA.error-dateOfBirth']")
	private WebElement errorDateOfBirth;
     
    /** The phone field. */
    @FindBy(id = "text-id-phone")
    private WebElement phoneField;
    
    /** The annual income field. */
    @FindBy(id = "text-id-annualIncome")
    private WebElement annualIncomeField;

    /** The post code field. */
    @FindBy(name = "postCode")
    private WebElement postCodeField;
    
    /** The post code field. */
    @FindBy(name = "rent")
    private WebElement rentField;

    /** The look up addressbutton. */
    @FindBy(css = "button[data-automation = 'ZA.addressLookup']")
    private WebElement lookUpAddressbutton;
    
    
	/**  error Date Of Birth. */
    @FindBy(css = "span[data-automation='ZA.PostcodeErrorMessage']")
	private WebElement errorPostcodeLabel;
    
	/**  Select Address List. */
    @FindBy(css = "select[data-automation='ZA.selectAddress']")
	private WebElement selectAddress;
    
	/**  Select Address List. */
    @FindBy(css = "select[data-automation='ZA.MoveInDateSelector.Month']")
	private WebElement moveInMonthSelect;
    
    /** The move in year select. */
    @FindBy(css = "select[data-automation='ZA.MoveInDateSelector.Year']")
	private WebElement moveInYearSelect;
    
    /** The use this address button. */
    @FindBy(xpath = "//button[text()='Use this address']")
	private WebElement useThisAddressButton;
 
     
     
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
		return By.cssSelector(loanAmount);
	}
	
	/**
	 * Sets the loan for.
	 *
	 * @param option the new loan for
	 */
	public void setLoanFor(LoanFor option) {
		WebDriverFacade.click(WebDriverFacade.findElement(By.xpath(String.format(locatorWhatIsLoan, option.getOptionName()))));
		Log.testStep("Selecting 'Loan For' option '" + option.getOptionName() + "'");
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		emailTextField.sendKeys(email);
		Log.testStep("Entering 'Email' > '" + email + "'");		
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(Title title) {
		WebDriverFacade.click(WebDriverFacade.findElement(By.xpath(String.format(locatorTitle, title.getOptionName()))));
		Log.testStep("Selecting 'Title' option '" + title.getOptionName() + "'");

	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		firstNameField.sendKeys(firstName);
		Log.testStep("Entering 'First Name' > '" + firstName + "'");		
	}
	
	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		lastNameField.sendKeys(lastName);
		Log.testStep("Entering 'Last Name' > '" + lastName + "'");		
	}
	
	/**
	 * Sets the day of birth.
	 *
	 * @param day the new day of birth
	 */
	public void setDayOfBirth(String day) {
		dayBirth.click();
		dayBirth.sendKeys(day);
		Log.testStep("Entering 'Day birth' > '" + day + "'");		
	}
	
	/**
	 * Sets the month of birth.
	 *
	 * @param month the new month of birth
	 */
	public void setMonthOfBirth(String month) {
		monthBirth.click();
		monthBirth.sendKeys(month);
		Log.testStep("Entering 'Month birth' > '" + month + "'");		

	}
	
	/**
	 * Sets the year of birth.
	 *
	 * @param year the new year of birth
	 */
	public void setYearOfBirth(String year) {
		yearBirth.click();
		yearBirth.sendKeys(year);
		Log.testStep("Entering 'Year birth' > '" + year + "'");		
	}
	
	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		phoneField.sendKeys(phone);
		Log.testStep("Entering 'Phone' > '" + phone + "'");		
	}
	
	/**
	 * Sets the employment status.
	 *
	 * @param status the new employment status
	 */
	public void setEmploymentStatus(EmploymentStatus status) {
		WebDriverFacade.click(WebDriverFacade.findElement(By.xpath(String.format(locatorEmploymentStatus, status.getOptionName()))));
		Log.testStep("Selecting 'Employemnt Status' option '" + status.getOptionName() + "'");

	}
	
	/**
	 * Sets the annual income.
	 *
	 * @param income the new annual income
	 */
	public void setAnnualIncome(String income) {
		annualIncomeField.sendKeys(income);
		Log.testStep("Entering 'Annual Income' > '" + income + "'");		
	}
	
	/**
	 * Sets the home status.
	 *
	 * @param status the new home status
	 */
	public void setHomeStatus(ResidentialStatus status) {
		WebDriverFacade.click(WebDriverFacade.findElement(By.xpath(String.format(locatorResidencialStatus, status.getOptionName()))));
		Log.testStep("Selecting 'Residential Status' option '" + status.getOptionName() + "'");

	}
	
	/**
	 * Sets the rent.
	 *
	 * @param rent the new rent
	 */
	public void setRent(String rent) {
		rentField.click();
		rentField.sendKeys(rent);
		Log.testStep("Entering 'Rent' > '" + rent + "'");
	}

	/**
	 * Sets the postcode.
	 *
	 * @param postCode the new postcode
	 */
	public void setPostcode(String postCode) {
		postCodeField.click();
		postCodeField.sendKeys(postCode);
		Log.testStep("Entering 'Post Code' > '" + postCode + "'");		
	}
	
	/**
	 * Click look up address.
	 */
	public void clickLookUpAddress() {
		WebDriverFacade.click(lookUpAddressbutton);	
	}
	
	/**
	 * Checks if is date error displayed.
	 *
	 * @return true, if is date error displayed
	 */
	public boolean isDateErrorDisplayed() {
		return WebDriverFacade.isElementVisible(errorDateOfBirth);
	}
	
	/**
	 * Gets the date error text.
	 *
	 * @return the date error text
	 */
	public String getDateErrorText() {
		if(isDateErrorDisplayed()) {
			return errorDateOfBirth.getText().trim();
		}
		throw new ElementNotVisibleException("Error Date label was not displayed");
	}
	
	
	/**
	 * Checks if is date error displayed.
	 *
	 * @return true, if is date error displayed
	 */
	public boolean isPostCodeErrorDisplayed() {
		return WebDriverFacade.isElementVisible(errorPostcodeLabel);
	}
	
	/**
	 * Gets the date error text.
	 *
	 * @return the date error text
	 */
	public String getPostCodeErrorText() {
		if(isPostCodeErrorDisplayed()) {
			return errorPostcodeLabel.getText().trim();
		}
		throw new ElementNotVisibleException("Post code error label was not displayed");
	}
	
	/**
	 * Select random address.
	 *
	 * @return the string
	 */
	public String selectRandomAddress() {
		WebDriverFacade.waitForElementVisibility(selectAddress);
		Select select = new Select(selectAddress);
		Random rand = new Random();
	    WebElement element = select.getOptions().get(rand.nextInt(select.getOptions().size()));
	    String address = element.getText().trim();
	    select.selectByVisibleText(address);
		Log.testStep("Selecting random address..");
	    return address;
	}

	/**
	 * Select month move in.
	 *
	 * @param month the month
	 */
	public void selectMonthMoveIn(String month) {
		WebDriverFacade.waitForElementVisibility(moveInMonthSelect);
		new Select(moveInMonthSelect).selectByVisibleText(month);
		Log.testStep("Entering 'Move In Month' > '" + month + "'");		
	}

	/**
	 * Select year move in.
	 *
	 * @param year the year
	 */
	public void selectYearMoveIn(String year) {
		WebDriverFacade.waitForElementVisibility(moveInMonthSelect);
		new Select(moveInYearSelect).selectByVisibleText(year);
		Log.testStep("Entering 'Move In Year' > '" + year + "'");		
	}

	/**
	 * Click use this address.
	 */
	public void clickUseThisAddress() {
		useThisAddressButton.click();
		Log.testStep("Clicking 'Use this address'");
	}
	

	/**
	 * Enter address.
	 *
	 * @param postCode the post code
	 * @param monthMoveIn the month move in
	 * @param yearMoveIn the year move in
	 * @return the string
	 */
	public String enterAddress(String postCode, String monthMoveIn, String yearMoveIn) {
    	setPostcode(postCode);
    	clickLookUpAddress();
		String address = selectRandomAddress();
		selectMonthMoveIn(monthMoveIn);
		selectYearMoveIn(yearMoveIn);
		clickUseThisAddress();
		return address;
	}
	
	/**
	 * Checks if is previous address.
	 *
	 * @return true, if is previous address
	 */
	public boolean isPreviousAddress() {
		return WebDriverFacade.isElementVisible(By.xpath("//h4[text() = 'Previous address'])"), 5);
	}
	
	/**
	 * Validate address.
	 *
	 * @param address the address
	 * @return true, if successful
	 */
	public boolean validateAddress(String address) {
		if(!WebDriverFacade.isElementVisible(errorPostcodeLabel, 5)) {
		String actualAddress = address.substring(0,address.indexOf(","));
		return WebDriverFacade.isElementVisible(By.xpath(String.format(locatorAddressAdded, actualAddress)));
		}
		else {
			Log.logger.error("Error message for address was > '" + errorPostcodeLabel.getText() + "'");
			return false;
		}
	}

	
}
