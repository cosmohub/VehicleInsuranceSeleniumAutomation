package basePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.SeleniumUtils;

public class HomeMenuPage extends SeleniumUtils{
	public WebDriver driver;
	public VehicleDataPage VehicleDataPage;
	
	public HomeMenuPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(xpath="//h1[text()='Vehicle Insurance Application']")
    WebElement appTitle;
	
	@FindBy(className="menu-toggle")
	WebElement toggleMenuButton;
	
	@FindBy(xpath="(//a[@id='nav_automobile'])[1]")
	WebElement automobileMenu;
	
	@FindBy(xpath="(//a[@id='nav_truck'])[1]")
	WebElement truckMenu;
	
	@FindBy(xpath="(//a[@id='nav_motorcycle'])[1]")
	WebElement motorcycleMenu;
	
	@FindBy(xpath="(//a[@id='nav_camper'])[1]")
	WebElement camperMenu;
	
	public VehicleDataPage goToAutomobile() {
		if(appTitle.isDisplayed()) {
			appTitle.click();
		}		
		waitForElementToAppear(automobileMenu);
		automobileMenu.click();
		waitForPageLoad();
		VehicleDataPage = new VehicleDataPage(driver);
		return VehicleDataPage;
	}
	
	public VehicleDataPage goToTruck() {
		waitForElementToAppear(automobileMenu);
		automobileMenu.click();
		waitForPageLoad();
		VehicleDataPage = new VehicleDataPage(driver);
		return VehicleDataPage;
	}
	
	public VehicleDataPage goToMotorcycle() {
		waitForElementToAppear(motorcycleMenu);
		motorcycleMenu.click();
		waitForPageLoad();
		VehicleDataPage = new VehicleDataPage(driver);
		return VehicleDataPage;
	}
	
	public VehicleDataPage goToCamper() {
		waitForElementToAppear(camperMenu);
		camperMenu.click();
		waitForPageLoad();
		VehicleDataPage = new VehicleDataPage(driver);
		return VehicleDataPage;
	}
	

}
