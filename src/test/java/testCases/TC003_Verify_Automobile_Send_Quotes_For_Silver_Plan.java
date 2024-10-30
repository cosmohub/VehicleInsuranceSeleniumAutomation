package testCases;

import baseTest.BaseTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basePages.HomeMenuPage;
import basePages.InsurantDataPage;
import basePages.ProductDataPage;
import basePages.SelectPriceOptionPage;
import basePages.SendQuotesPage;
import basePages.VehicleDataPage;
import utilities.ExcelDataReader;

public class TC003_Verify_Automobile_Send_Quotes_For_Silver_Plan extends BaseTest{
	
	@Test(dataProvider="getTestData")
	public void sendQuotesForAutomobile(HashMap<String, String> input) throws FileNotFoundException, IOException {
	String MAKE = input.get("Make");
	String SEATS = input.get("Seats");
	String FUEL = input.get("Fuel");
	String FIRSTNAME = input.get("FirstName");
	String LASTNAME = input.get("LastName");
	String HOBBY = input.get("Hobby");
	String OCCUPATION = input.get("Occupation");
	String PRICEOPTION = input.get("PriceOption");
	String PRICE = input.get("Price");
	String ONLINECLAIM =input.get("OnlineClaim");
	String CLAIMDISCOUNT = input.get("ClaimDiscount");
	String COVERAGE = input.get("WorldWideCover");
	HomeMenuPage HomeMenuPage = launchApplication();
	VehicleDataPage VehicleDataPage = HomeMenuPage.goToAutomobile();
	InsurantDataPage InsurantDataPage = VehicleDataPage.enterVehicleData(MAKE, SEATS, FUEL);
	ProductDataPage ProductDataPage = InsurantDataPage.enterInsurantData(FIRSTNAME, LASTNAME, HOBBY, OCCUPATION);
	SelectPriceOptionPage SelectPriceOptionPage = ProductDataPage.enterProductData();
	SelectPriceOptionPage.verifyPrice(PRICEOPTION, PRICE);
	SelectPriceOptionPage.verifyOnlineClaim(PRICEOPTION, ONLINECLAIM);
	SelectPriceOptionPage.verifyClaimDiscounts(PRICEOPTION, CLAIMDISCOUNT);	
	SelectPriceOptionPage.verifyWorldWideCover(PRICEOPTION, COVERAGE);
	SelectPriceOptionPage.selectPriceOption(PRICEOPTION);
	SendQuotesPage SendQuotesPage = SelectPriceOptionPage.clickNextToSendQuotes();
	SendQuotesPage.sendQuotes();
	SendQuotesPage.verifyEmailSuccessMessage();
	SendQuotesPage.goToMainPage();
	}
	
	
	
	@DataProvider
	public Object[][] getTestData() throws FileNotFoundException, IOException {
		List<HashMap<String, String>> data = ExcelDataReader.testDataImporter("TC003_Verify_Automobile_Send_Quotes_For_Silver_Plan");
		return new Object[][] {{data.get(0)}};
		
	}
	

}
