package basePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.SeleniumUtils;

public class ProductDataPage extends SeleniumUtils {

	public WebDriver driver;
	public SelectPriceOptionPage SelectPriceOptionPage;

	public ProductDataPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "startdate")
	WebElement startDate_textbox;

	@FindBy(id = "insurancesum")
	WebElement insuranceSumDropdown;

	@FindBy(id = "meritrating")
	WebElement meritRatingDropdown;

	@FindBy(id = "damageinsurance")
	WebElement damageInsuranceDropdown;

	@FindBy(xpath = "//input[@id='EuroProtection']/../span")
	WebElement euroProtection_checkbox;

	@FindBy(xpath = "//input[@id='LegalDefenseInsurance']/../span")
	WebElement legalDefenseInsurance_checkbox;

	@FindBy(id = "courtesycar")
	WebElement courtesyCarDropdown;

	@FindBy(id = "preventerinsurancedata")
	WebElement prevButton;

	@FindBy(id = "nextselectpriceoption")
	WebElement nextButton;

	public SelectPriceOptionPage enterProductData() {
		String startDate = getDynamicDate("MM/dd/yyyy", 40);
		startDate_textbox.sendKeys(startDate);
		selectValueFromDropdown(insuranceSumDropdown, "3000000");
		selectValueFromDropdown(meritRatingDropdown, "Bonus 6");
		selectValueFromDropdown(damageInsuranceDropdown, "Partial Coverage");
		euroProtection_checkbox.click();
		selectValueFromDropdown(courtesyCarDropdown, "Yes");
		nextButton.click();
		waitForPageLoad();
		SelectPriceOptionPage = new SelectPriceOptionPage(driver);
		return SelectPriceOptionPage;

	}

}
