package basePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.SeleniumUtils;

public class VehicleDataPage extends SeleniumUtils{
	public WebDriver driver;
	public InsurantDataPage InsurantDataPage;
	
	public VehicleDataPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="make")
	WebElement makeDropdown;
	
	@FindBy(id="engineperformance")
	WebElement enginePerformance;
	
	@FindBy(id="dateofmanufacture")
	WebElement dateofmanufacture;
	
	@FindBy(id="numberofseats")
	WebElement numberOfSeats;
	
	@FindBy(id="fuel")
	WebElement fuelType;
	
	@FindBy(id="listprice")
	WebElement listPrice;
	
	@FindBy(id="licenseplatenumber")
	WebElement licensePlateNumber;
	
	@FindBy(id="annualmileage")
	WebElement annualMileage;
	
	@FindBy(id="nextenterinsurantdata")
	WebElement nextButton;
	
	
	public InsurantDataPage enterVehicleData(String make, String seats, String fuel) {
		selectValueFromDropdown(makeDropdown, make);
		enginePerformance.sendKeys("1000");
		dateofmanufacture.sendKeys("11/16/2020");
		selectValueFromDropdown(numberOfSeats, seats);
		selectValueFromDropdown(fuelType,fuel);
		listPrice.sendKeys("100000");
		licensePlateNumber.sendKeys("123456");
		annualMileage.sendKeys("5000");
		nextButton.click();
		waitForPageLoad();		
		InsurantDataPage = new InsurantDataPage(driver);
		return InsurantDataPage;
	}


	


}
