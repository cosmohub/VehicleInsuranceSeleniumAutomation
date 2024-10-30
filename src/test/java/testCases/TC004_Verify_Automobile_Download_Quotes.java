package testCases;

import baseTest.BaseTest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basePages.HomeMenuPage;
import basePages.InsurantDataPage;
import basePages.ProductDataPage;
import basePages.SelectPriceOptionPage;
import basePages.SendQuotesPage;
import basePages.VehicleDataPage;
import utilities.ExcelDataImporter;

public class TC004_Verify_Automobile_Download_Quotes extends BaseTest {

	@Test(dataProvider = "getTestData")
	public void sendQuotesForAutomobile(String TESTCASE, String MAKE, String SEATS, String FUEL, String FIRSTNAME,
			String LASTNAME, String HOBBY, String OCCUPATION, String PRICEOPTION, String PRICE, String ONLINECLAIM,
			String DISCOUNT, String COVERAGE) throws Exception {
		HomeMenuPage HomeMenuPage = launchApplication();
		VehicleDataPage VehicleDataPage = HomeMenuPage.goToAutomobile();
		InsurantDataPage InsurantDataPage = VehicleDataPage.enterVehicleData(MAKE, SEATS, FUEL);
		ProductDataPage ProductDataPage = InsurantDataPage.enterInsurantData(FIRSTNAME, LASTNAME, HOBBY, OCCUPATION);
		SelectPriceOptionPage SelectPriceOptionPage = ProductDataPage.enterProductData();
		SelectPriceOptionPage.verifyPrice(PRICEOPTION, PRICE);
		SelectPriceOptionPage.verifyOnlineClaim(PRICEOPTION, ONLINECLAIM);
		SelectPriceOptionPage.verifyClaimDiscounts(PRICEOPTION, DISCOUNT);
		SelectPriceOptionPage.verifyWorldWideCover(PRICEOPTION, COVERAGE);
		SelectPriceOptionPage.selectPriceOption(PRICEOPTION);
		SelectPriceOptionPage.downloadQuote();
		SelectPriceOptionPage.verifyPdfFileContent(FIRSTNAME, LASTNAME, MAKE);

	}

	@DataProvider
	public String[][] getTestData() throws FileNotFoundException, IOException {
		String[][] data = ExcelDataImporter.excelDataReader("TC001_Verify_Automobile_Send_Quotes");
		return data;

	}

}
