package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class GlobalDataPropertyReader {
	
	public static String getGlobalData(String testData) throws FileNotFoundException, IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\testData\\GlobalData.properties");
		Properties prop = new Properties();
		prop.load(file);
		String data = prop.getProperty(testData);
		return data;
		
	}

	

}
