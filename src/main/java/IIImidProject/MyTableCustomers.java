package main.java.IIImidProject;

import java.awt.*;
import java.sql.*;
import java.util.Properties;

import javax.swing.table.AbstractTableModel;

public class MyTableCustomers extends AbstractTableModel {
    static int page;
    static int rpp =30;
    static int start;
    private static final String sql = "select * from CUSTOMERS";
    
    public MyTableCustomers() {
        NorthwindBackOffice.maxpage.setText(String.valueOf(GetDBData.getMaxPage(sql)));
        NorthwindBackOffice.maxpage.setForeground(Color.WHITE);
        getDBData(1);
    }

    public static Object[][] getDBData(int iii) {
        Object[][] dataList = new Object[0][];
        var conn = DBConnection.connectDB();//拿到DB連線
        page = NorthwindBackOffice.getPage();
        start = (page -1) * rpp;
        String presql = sql + " LIMIT %d ,%d";
		String sql2 = String.format(presql, start, rpp);
		String sql3 = sql +  " WHERE customerID = ?";
        PreparedStatement pstmt;
        try {
            String q = NorthwindBackOffice.jtfCSID.getText();
            if(iii==0){
                pstmt = conn.prepareStatement(
                        sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            }else{
                if (q.equals("")) {
                    pstmt = conn.prepareStatement(
                            sql2,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                } else {
                    pstmt = conn.prepareStatement(
                            sql3,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    pstmt.setString(1, q);
                }
            }
            var res = pstmt.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            int columncount = rsmd.getColumnCount();//取得欄位數量
            res.last();//游標放到最後
            int rowCount = res.getRow();//取得最後一行(int) = 總行數
            res.beforeFirst();//游標回到最前面
            dataList = new Object[rowCount][];
            for (var i = 0; res.next(); i++) {
                String[] columns = new String[columncount];
                for(var k = 0; k<columncount; k++){
                    String oneRowColumn = res.getString(k+1); //取得第一行第一欄.第一行第二欄....第一行最後一欄的資料
                    columns[k] = oneRowColumn;
                }
                dataList[i] = columns; // 把每一行全部欄位的資料放入Object[i]
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return dataList;
    }

    public static String[] columnNames = GetDBData.getColumnsName(sql);

    private final Object[][] data = getDBData(1);

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
}