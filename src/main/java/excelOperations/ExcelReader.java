package excelOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 *  Row count starts of '0' indexing (it is not like list<> or String) 
 *  	Where all of the indexes starts from '1' index
 *  	here we are having sixteen rows( But getLasRowNum() is returning value '15')
 *  
 *  But when it comes to cell the indexing starts from '1'
 *		here we are having two cells( But getLastCellNum() is returning value '2')
 */

public class ExcelReader {
	
	public static void main(String[] args) throws IOException {
		
		String srcfile = "G:\\SQ AHMED\\Eclipse_WorkSpace\\SeleniumTopicsProject\\com.utilities.STP\\Sample Excel.xlsx";
		
		File src = new File(srcfile);
		
		FileInputStream file = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(file);
		
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		int rowcount = ws.getLastRowNum();
		
		System.out.println("No.of Rows Exist in the sheet is: "+rowcount);
		
		for(int i=1;i<=rowcount;i++)
		{
			Row row = ws.getRow(i);
			
			int cellcount = row.getLastCellNum();
			
			for(int j=0;j<cellcount;j++)
			{
				Cell cell = row.getCell(j);
				
				switch (cell.getCellType())
                {                  
                    case STRING :
                    	System.out.print(cell.getStringCellValue()+" ");
                    	break;
                    case NUMERIC:
                    	System.out.print(cell.getNumericCellValue()+" ");
                    	break;
                    case BOOLEAN:
                    	System.out.print(cell.getBooleanCellValue()+" ");
                    	break;
                    default:
                    	System.out.print(cell.getStringCellValue()+" ");
                    	break;                    	
                }				
			}
			
			System.out.println();
		}
		wb.close();
	}

}
