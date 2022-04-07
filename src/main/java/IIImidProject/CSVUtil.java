package main.java.IIImidProject;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.*;

public class CSVUtil {

    public static void saveTable(String filename, Object[][] dataList, String[] columnName) throws IOException{
        CSVWriter writer = new CSVWriter(new FileWriter(filename));
        writer.writeNext(columnName); //寫CSV第一欄

        //從datahelp寫入資料到excel
        for(var i = 0; i<dataList.length;i++){
            var s = (String[])dataList[i];
            writer.writeNext(s);
        }
        writer.close();
        System.out.println("ok");

    }
}