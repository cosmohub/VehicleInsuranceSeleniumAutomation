package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataReader {
	public static List<HashMap<String, String>> testDataImporter(String testCaseName) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\testData\\AutomobileTestData.xlsx");
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet("TestData");
		Iterator<Row> rw = sheet.iterator();
		Row firstRow = rw.next();
		int colCount = firstRow.getLastCellNum();
//		int rowCount = 0;
//		
//		while(rw.hasNext()) {
//			Row row = rw.next();
//			if(row.getCell(0).getStringCellValue().equalsIgnoreCase("TC002_Verify_Automobile_Send_Quotes_For_Platinum_Plan")){
//				rowCount++;
//			}
//		}
		
		DataFormatter formatter = new DataFormatter();
		Iterator<Row> rows = sheet.iterator();
		Row headerRow = rows.next();
		List<HashMap<String, String>> listOfDatas = new ArrayList<>();
				
		while(rows.hasNext()) {
			Row dataRow = rows.next();
			if(dataRow.getCell(0).getStringCellValue().equalsIgnoreCase("TC002_Verify_Automobile_Send_Quotes_For_Platinum_Plan")) {
				HashMap<String, String> data = new HashMap<String, String>();
				for(int i=0;i<colCount;i++) {
					Cell value = dataRow.getCell(i);
					String dataValue = formatter.formatCellValue(value);
					Cell header = headerRow.getCell(i);
					String dataKey = formatter.formatCellValue(header);
					data.put(dataKey, dataValue);					
				}
				listOfDatas.add(data);
			}
		}
		
		
		return listOfDatas;
	}

}
