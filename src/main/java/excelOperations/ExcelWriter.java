package excelOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 *  
 */

public class ExcelWriter {
	
	public static void main(String[] args) throws IOException {
		
		String srcfile = "G:\\SQ AHMED\\Eclipse_WorkSpace\\SeleniumTopicsProject\\com.utilities.STP\\WriteExcel.xlsx";
		
		File src = new File(srcfile);
		
		FileInputStream file = new FileInputStream(src);
		
		XSSFWorkbook wb = new XSSFWorkbook(file);
		
		XSSFSheet ws = wb.createSheet("Test Data");
		
		Map<String, Object[]> map = new HashMap<String, Object[]>();
		
		map.put("1", new Object[] {"ID","Name", "Relation"});
		map.put("2", new Object[] {"1","Syed Roshan Zameer", "Big Brother"});
		map.put("3", new Object[] {"2","Syed Sadaddin", "Second Brother"});
		map.put("4", new Object[] {"3","Syed Qhubaib Ahmed", "My Self"});
		map.put("5", new Object[] {"4","Syed Mohammad Hasnain", "Younger Brother"});
		
		Set<String> set = map.keySet();
		int rownum = 0;
		
		for(String key:set)
		{
			Row row = ws.createRow(rownum++);
			
			Object[] object = map.get(key);
			
			for(int j=0;j<object.length;j++)
			{
				
				Cell cell = row.createCell(j);
				
				cell.setCellValue((String) object[j]);				
			}
			
		}
		
		FileOutputStream fout = new FileOutputStream(src);
		wb.write(fout);
		wb.close();
		
		int[] a = {1,2,3,4};
		for(int i=0;i<a.length;i++)
		{
			System.out.println(a[i]);
		}
	}

}
