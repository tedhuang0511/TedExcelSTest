package ExcelUtil;

import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {
    public static void readFile(String fileName) throws IOException{
        InputStream is = new FileInputStream(new File(fileName));
        XSSFWorkbook wb = new XSSFWorkbook(is);
        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator itRow = sheet.rowIterator();

        while(itRow.hasNext()){
            XSSFRow row = (XSSFRow)itRow.next();
            Iterator itCell = row.cellIterator();

            while(itCell.hasNext()){
                XSSFCell cell  = (XSSFCell) itCell.next();
                System.out.print(cell+" ");
            }
            System.out.println("");
        }
    }

    public static void writeFile(String fileName, List<String[]> dataList) throws IOException{
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Test Sheet");


        //主動寫第一欄的標題名稱
        XSSFRow titleRow = sheet.createRow(0);
        XSSFCell cell1 = titleRow.createCell(0);
        cell1.setCellValue("OrderID");
        XSSFCell cell2 = titleRow.createCell(1);
        cell2.setCellValue("ProductID");
        XSSFCell cell3 = titleRow.createCell(2);
        cell3.setCellValue("UnitPrice");
        XSSFCell cell4 = titleRow.createCell(3);
        cell4.setCellValue("Quantity");
        XSSFCell cell5 = titleRow.createCell(4);
        cell5.setCellValue("Discount");

        //從datahelp寫入資料到excel
        for(int i=0; i< dataList.size(); i++) {
            XSSFRow row = sheet.createRow(i+1);
            String[] rowData = dataList.get(i);

            for(int j=0; j< rowData.length; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(rowData[j]);
            }
        }

        FileOutputStream fis = new FileOutputStream(fileName);
        wb.write(fis);
        fis.flush();
        fis.close();

    }

    public static void saveTable(String filename, Object[][] dataList, String[] columnName) throws IOException{
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Test Sheet");

        //寫第一欄的標題名稱
        XSSFRow titleRow = sheet.createRow(0);
        for(var i = 0; i<columnName.length;i++){
            XSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(columnName[i]);
        }
//        XSSFCell cell1 = titleRow.createCell(0);
//        cell1.setCellValue("OrderID");
//        XSSFCell cell2 = titleRow.createCell(1);
//        cell2.setCellValue("ProductID");
//        XSSFCell cell3 = titleRow.createCell(2);
//        cell3.setCellValue("UnitPrice");
//        XSSFCell cell4 = titleRow.createCell(3);
//        cell4.setCellValue("Quantity");
//        XSSFCell cell5 = titleRow.createCell(4);
//        cell5.setCellValue("Discount");

        //從datahelp寫入資料到excel
        for(int i=0; i< dataList.length; i++) {
            XSSFRow row = sheet.createRow(i+1);
            String[] rowData = (String[]) dataList[i];

            for(int j=0; j< rowData.length; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(rowData[j]);
            }
        }

        FileOutputStream fos = new FileOutputStream(filename);
        wb.write(fos);
        fos.flush();
        fos.close();

    }
}