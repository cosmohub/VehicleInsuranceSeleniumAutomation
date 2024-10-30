package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataImporter {

	public static String[][] excelDataReader(String testCaseName) throws FileNotFoundException, IOException {
		FileInputStream file = new FileInputStream(".\\src\\main\\resources\\testData\\AutomobileTestData.xlsx");
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheet("TestData");
		Iterator<Row> row = sheet.iterator();
		Row firstRow = row.next();
		int colCount = firstRow.getLastCellNum();
		int rowCount = 0;

		while (row.hasNext()) {
			Row rw = row.next();
			if (rw.getCell(0).getStringCellValue().equalsIgnoreCase(testCaseName)) {
				rowCount++;
			}
		}

		String[][] data = new String[rowCount][colCount];
		DataFormatter formatter = new DataFormatter();
		int i = 0;
		Iterator<Row> rows = sheet.iterator();
		while (rows.hasNext()) {
			Row dataRow = rows.next();
			if (dataRow.getCell(0).getStringCellValue().equalsIgnoreCase(testCaseName)) {
				for (int j = 0; j < colCount; j++) {
					Cell dataValue = dataRow.getCell(j);
					data[i][j] = formatter.formatCellValue(dataValue);

				}
				i++;
			}
			
		}
		return data;

	}

}
