package basePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.SeleniumUtils;

public class InsurantDataPage extends SeleniumUtils {
	public WebDriver driver;
	public ProductDataPage ProductDataPage;
	
	public InsurantDataPage(WebDriver driver) {
		super(driver);
		this.driver =  driver;
		PageFactory.initElements(driver, this);
		
	}
	

	@FindBy(id="firstname")
	WebElement firstName_txtbox;
	
	@FindBy(id="lastname")
	WebElement lastName_txtbox;
	
	@FindBy(id="birthdate")
	WebElement birthdate_txtbox;
	
	@FindBy(xpath="//input[@id='gendermale']/..//span")
	WebElement genderMale_checkbox;
	
	@FindBy(xpath="//input[@id='genderfemale']/..//span")
	WebElement genderFemale_checkbox;
	
	@FindBy(id="streetaddress")
	WebElement streetAddress_txtbox;
	
	@FindBy(id="city")
	WebElement city_txtbox;
	
	@FindBy(id="country")
	WebElement country_dropdown;
	
	@FindBy(id="zipcode")
	WebElement zipcode_txtbox;
	
	@FindBy(id="occupation")
	WebElement occupationDropdown;
	
	@FindBy(id="website")
	WebElement website_txtbox;
	
	@FindBy(id="picture")
	WebElement pictureUpload;
	
	@FindBy(id="preventervehicledata")
	WebElement prevButton;
	
	@FindBy(id="nextenterproductdata")
	WebElement nextButton;

	String pictureUploadPath = ".\\src\\main\\resources\\testData\\Picture_upload.PNG";
	
	public ProductDataPage enterInsurantData(String firstName, String lastName, String hobby, String occupation) {
		firstName_txtbox.sendKeys(firstName);
		lastName_txtbox.sendKeys(lastName);
		birthdate_txtbox.sendKeys("11/16/1961");
		genderMale_checkbox.click();
		streetAddress_txtbox.sendKeys("Chennai");
		selectValueFromDropdown(country_dropdown,"India");
		zipcode_txtbox.sendKeys("12345");		
		city_txtbox.sendKeys("Chennai");
		selectValueFromDropdown(occupationDropdown, occupation);
		driver.findElement(By.xpath("//input[@id='"+hobby+"']/../span")).click();
		website_txtbox.sendKeys("https://www.google.com");
		pictureUpload.sendKeys(pictureUploadPath);
		nextButton.click();
		waitForPageLoad();
		ProductDataPage = new ProductDataPage(driver);
		return ProductDataPage;
		
	}

}
