package basePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.SeleniumUtils;


public class SendQuotesPage extends SeleniumUtils {

	public SendQuotesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="email")
	WebElement email_txtbox;
	
	@FindBy(id="phone")
	WebElement phone_txtbox;
	
	@FindBy(id="username")
	WebElement username_txtbox;
	
	@FindBy(id="password")
	WebElement password_txtbox;
	
	@FindBy(id="confirmpassword")
	WebElement confirmPassword_txtbox;
	
	@FindBy(id="Comments")
	WebElement comments_txtbox;
	
	@FindBy(id="sendemail")
	WebElement sendEmail_button;
	
	@FindBy(id="prevselectpriceoption")
	WebElement prevButton;
	
	@FindBy(tagName="h2")
	WebElement successMessage;
	
	@FindBy(className="confirm")
	WebElement confirm_button;
	
	@FindBy(xpath="//label[@title='Loading PDF']")
	WebElement loader;
	
	@FindBy(xpath="//i[@class='fa fa-home']")
	WebElement mainPageMenu;
	
	@FindBy(xpath="(//a[@id='nav_automobile'])[1]")
	WebElement automobileMenu;
	
	public void sendQuotes() {
		String randomNum = String.valueOf(randomNumGenerator());
		email_txtbox.sendKeys("testuser"+randomNum+"@gmail.com");
		phone_txtbox.sendKeys("1111111111");
		username_txtbox.sendKeys("testuser"+randomNum);
		password_txtbox.sendKeys("Password"+randomNum);
		confirmPassword_txtbox.sendKeys("Password"+randomNum);
		comments_txtbox.sendKeys("Testing is done as part of Automation");
		sendEmail_button.click();
		waitForElementToAppear(loader);
		waitForElementToDisappear(loader);
	}
	
	public void verifyEmailSuccessMessage() {
		waitForElementToAppear(successMessage);
		String actualMessage = successMessage.getText();
		String expectedMessage = "Sending e-mail success!";
		Assert.assertEquals(actualMessage,expectedMessage);
		confirm_button.click();
		
	}
	
	public void goToMainPage() {
		waitForElementToAppear(mainPageMenu);
		mainPageMenu.click();
		waitForElementToAppear(automobileMenu);
		
		
	}

}
