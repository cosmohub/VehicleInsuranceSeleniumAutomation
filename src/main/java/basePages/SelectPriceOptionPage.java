package basePages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.SeleniumUtils;

public class SelectPriceOptionPage extends SeleniumUtils {
	public WebDriver driver;
	public SendQuotesPage SendQuotesPage;
	SeleniumUtils SeleniumWrapper;

	public SelectPriceOptionPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		this.SeleniumWrapper = new SeleniumUtils(driver);
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "viewquote")
	WebElement viewQuote;

	@FindBy(id = "downloadquote")
	WebElement downloadQuote;

	@FindBy(xpath = "//label[@title='Loading PDF']")
	WebElement loader;

	@FindBy(id = "nextsendquote")
	WebElement nextButton;

	public void verifyPrice(String priceOption, String expectedPrice) {
		String actualPrice = driver
				.findElement(By
						.xpath("//td[contains(text(),'Price per Year')]/../td[@data-label='" + priceOption + "']/span"))
				.getText();
		Assert.assertEquals(actualPrice, expectedPrice);
	}

	public void verifyOnlineClaim(String priceOption, String expectedClaim) {
		String actualClaim = driver
				.findElement(By.xpath("//td[contains(text(),'Online Claim')]/../td[@data-label='" + priceOption + "']"))
				.getText();
		Assert.assertEquals(actualClaim, expectedClaim);
	}

	public void verifyClaimDiscounts(String priceOption, String expectedDiscount) {
		String actualDiscount = driver
				.findElement(
						By.xpath("//td[contains(text(),'Claims Discount')]/../td[@data-label='" + priceOption + "']"))
				.getText();
		Assert.assertEquals(actualDiscount, expectedDiscount);
	}

	public void verifyWorldWideCover(String priceOption, String expectedCoverage) {
		String actualCoverage = driver
				.findElement(
						By.xpath("//td[contains(text(),'Worldwide Cover')]/../td[@data-label='" + priceOption + "']"))
				.getText();
		Assert.assertEquals(actualCoverage, expectedCoverage);
	}

	public void selectPriceOption(String priceOption) {
		driver.findElement(By.xpath("//input[@Value='" + priceOption + "']/../span")).click();
		waitForPageLoad();
	}

	public SendQuotesPage clickNextToSendQuotes() {
		waitForElementToAppear(nextButton);
		nextButton.click();
		SendQuotesPage = new SendQuotesPage(driver);
		return SendQuotesPage;
	}

	public void viewQuote() {
		waitForElementToAppear(viewQuote);
		viewQuote.click();
		waitForElementToDisappear(loader);

	}
	
	public void downloadQuote() throws InterruptedException {
		waitForElementToAppear(downloadQuote);
		downloadQuote.click();
		waitForElementToDisappear(loader);
		Thread.sleep(Duration.ofSeconds(5));
	}
	
	public void verifyPdfFileContent(String FIRSTNAME,String LASTNAME,String MAKE) throws Exception {
		PdfReaderVerifyPageCount("Tricentis_Insurance_Quote.pdf",1);
		PdfReaderVerifyURLPDFContent("First Name: "+FIRSTNAME);
		PdfReaderVerifyURLPDFContent("Last Name: "+LASTNAME);
		PdfReaderVerifyURLPDFContent("Make: "+MAKE);
		
	}

	

}
