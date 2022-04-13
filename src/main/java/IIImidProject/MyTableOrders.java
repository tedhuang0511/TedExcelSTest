package main.java.IIImidProject;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class MyTableOrders extends AbstractTableModel {
    static int page;
    static int rpp = 30;
    static int start;
    private static String sql = "select * from ORDERS";
    private static PreparedStatement pstmt;

    public MyTableOrders() {
        NorthwindBackOffice.maxpage.setText(String.valueOf(GetDBData.getMaxPage(sql)));
        NorthwindBackOffice.maxpage.setForeground(Color.WHITE);
        getDBData(1);
    }

    public static Object[][] getDBData(int iii) {
        Object[][] dataList = new Object[0][];
        var conn = DBConnection.connectDB();//拿到DB連線
        try {
            page = NorthwindBackOffice.getPageOD();
            start = (page - 1) * rpp;
            String presql = sql + " LIMIT %d ,%d";
            String sql1 = String.format(presql, start, rpp);
            String sql2 = sql + " WHERE OrderDate BETWEEN ? AND ?";
            String sql3 = sql + " WHERE OrderID = ?";

            String A = NorthwindBackOffice.jtfDS.getText();
            String B = NorthwindBackOffice.jtfDN.getText();
            String C = NorthwindBackOffice.jtfODID.getText();
            if (iii == 0) {
                pstmt = conn.prepareStatement(
                        sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            } else {
                if (A.equals("") && B.equals("") && C.equals("")) {
                    pstmt = conn.prepareStatement(
                            sql1,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                } else if (!(A.equals("")) || !(B.equals(""))) {
                    pstmt = conn.prepareStatement(
                            sql2,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    pstmt.setString(1, A);
                    pstmt.setString(2, B);
                } else if (!(C.equals(""))) {
                    pstmt = conn.prepareStatement(
                            sql3,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    pstmt.setString(1, C);
                }
            }
            var res = pstmt.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            int columnCount = rsmd.getColumnCount();
            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();
            dataList = new Object[rowCount][];
            for (var i = 0; res.next(); i++) {
                String[] columns = new String[columnCount];
                for (var k = 0; k < columnCount; k++) {
                    String oneRowColumn = res.getString(k + 1);
                    columns[k] = oneRowColumn;
                }
                dataList[i] = columns;
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