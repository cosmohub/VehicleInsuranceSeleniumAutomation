package utilities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class SeleniumUtils {
	public WebDriver driver;
	WebDriverWait wait;

	public SeleniumUtils(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void waitForElementToAppear(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForElementToDisappear(WebElement element) {
		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	public void selectValueFromDropdown(WebElement element, String value) {
		Select dropDown = new Select(element);
		dropDown.selectByValue(value);
	}

	public void waitForPageLoad() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	public void selectValueFromUserInput(By element, String value) {
		driver.findElement(element).click();

	}

	public String getCurrentDate(String format) {
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		String date = dateformat.format(new Date());
		return date;
	}

	public String getDynamicDate(String format, int noOfDays) {
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, noOfDays);
		String date = dateformat.format(c.getTime());
		return date;
	}

	public int randomNumGenerator() {
		Random rand = new Random();
		int num = rand.nextInt(100);
		return num;

	}

	public void waitUntilUrlLaunched(String url) {
		wait.until(ExpectedConditions.urlToBe(url));
	}

	public void PdfReaderVerifyPageCount(String expectedFileName,int pageCountExpected) throws Exception {
//		waitUntilUrlLaunched(url);
//		URL pdfUrl = new URL(url);
//		InputStream is = pdfUrl.openStream();
//		BufferedInputStream bf = new BufferedInputStream(is);

		File file = getLatestFileFromDownloads();
		String fileName = file.getName();
		Assert.assertTrue(fileName.contains(fileName));
		
		PDDocument pdfDocument = PDDocument.load(file);
		int pageCount = pdfDocument.getNumberOfPages();
		Assert.assertEquals(pageCount, pageCountExpected);
	}

	public void PdfReaderVerifyURLPDFContent(String textContent) throws IOException {
//		waitUntilUrlLaunched(url);
//		URL pdfUrl = new URL(url);
//		InputStream is = pdfUrl.openStream();
//		BufferedInputStream bf = new BufferedInputStream(is);
		
		File file = getLatestFileFromDownloads();
		PDDocument pdfDocument = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		String pdfContent = pdfStripper.getText(pdfDocument);
		Assert.assertTrue(pdfContent.contains(textContent));

	}
	
	public File getLatestFileFromDownloads() {
		File dir = new File(".\\src\\test\\resources\\Downloads");
		File[] files = dir.listFiles();
		if(files!=null && files.length>0)
		{
			return Arrays.stream(files)
					.filter(File::isFile)
					.max(Comparator.comparingLong(File::lastModified))
					.orElse(null);
		}
		return null;
	}

	public void switchToNewWindow() {
		String currentWindow = driver.getWindowHandle();
		Set<String> Handles = driver.getWindowHandles();
		for (String handle : Handles) {
			if (!handle.equals(currentWindow)) {
				driver.switchTo().window(handle);
			}
		}

	}

}
