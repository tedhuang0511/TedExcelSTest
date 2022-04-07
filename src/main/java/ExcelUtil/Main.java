package main.java.ExcelUtil;

import IIImidProject.MyTableCustomers;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;

public class Main {
    public static void main(String[] args) {
//        String readFileNmae = "data/123.xlsx";
//        try{
//            ExcelUtil.readFile(readFileNmae);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//		String path = "data/0331.xlsx";
//		TableDataDBImp dataHelp = new TableDataDBImp();
//		Object[][] list = dataHelp.getOrderDET();
//
//		try {
//			ExcelUtil.saveTable(path, list, MyTableCustomers.columnNames);
//		} catch (IOException e) {
//			System.out.println(e.toString());
//		}
//		System.out.println("Write Excel End");
        String filename = "data/223.csv";
        Object[][] dataList = {{"1","2","3"},{"4","5","6"},{"7","8","9"}};
        String[][] s = (String[][]) dataList;
        String[] columnName = {"a","b","c"};
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(filename));
            writer.writeNext(columnName);
            for(var i = 0; i<dataList.length;i++){
                var v = s[i];
                writer.writeNext(v);
            }
            writer.close();
            System.out.println("ok");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}