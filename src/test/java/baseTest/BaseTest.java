package baseTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.GlobalDataPropertyReader;

import basePages.HomeMenuPage;

public class BaseTest {	
	public WebDriver driver;
	public HomeMenuPage HomeMenuPage;
	
	public void initialize_driver() throws FileNotFoundException, IOException {		
		String BROWSER = GlobalDataPropertyReader.getGlobalData("browser");
		System.out.println(BROWSER);
		
		if(BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			HashMap<String, Object> prefs = new HashMap<>();
			prefs.put("download.default_directory", System.getProperty("user.dir")+".\\src\\test\\resources\\Downloads");
//			prefs.put("plugins.always_open_pdf_externally", true);
			prefs.put("download.prompt_for_download", false);
			prefs.put("download.directory_upgrade", true);
			prefs.put("safebrowsing.enabled", true);
			ChromeOptions opts = new ChromeOptions();
			opts.setExperimentalOption("prefs", prefs);			
			driver = new ChromeDriver(opts);
			System.out.println("Executed");
		}
		else if(BROWSER.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
	}
	
	public HomeMenuPage launchApplication() throws FileNotFoundException, IOException {	
		String URL = GlobalDataPropertyReader.getGlobalData("url");
		initialize_driver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		HomeMenuPage = new HomeMenuPage(driver);
		return HomeMenuPage;
		
	}
	
//	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public String takeScreenshot(String testName, WebDriver driver) throws IOException {
		String date = new SimpleDateFormat("dd-MM-yyyy_hms").format(new Date());
		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\screenshots\\"+testName+"_"+date+".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileUtils.copyFile(source, dest);
		return path;
		
	}
}