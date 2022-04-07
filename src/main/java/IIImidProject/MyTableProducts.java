package main.java.IIImidProject;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Properties;

public class MyTableProducts extends AbstractTableModel {
    static PreparedStatement pstmt;
    static int page;
    static int rpp =30;
    static int start;
    private static String sql = "select * from PRODUCTS";

    public MyTableProducts() {
        NorthwindBackOffice.maxpage.setText(String.valueOf(GetDBData.getMaxPage(sql)));
        NorthwindBackOffice.maxpage.setForeground(Color.WHITE);
        getDBData();
    }

    public static Object[][] getDBData() {
        Object[][] dataList = new Object[0][];
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        page = NorthwindBackOffice.getPagePD();
        start = (page -1) * rpp;
        String presql = sql+" LIMIT %d ,%d";
		String sql1 = String.format(presql, start, rpp);
        String sql2 = sql+" WHERE PRODUCTID = ?";
        String sql3 = sql+" WHERE PRODUCTNAME LIKE ?";
        String sql4 = sql+" WHERE SUPPLIERID = ?";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){

            String A = NorthwindBackOffice.jtfPID.getText();
            String B = NorthwindBackOffice.jtfPN.getText();
            String C = NorthwindBackOffice.jtfSPID.getText();

            if (A.equals("")&&B.equals("")&&C.equals("")) {
                pstmt = conn.prepareStatement(
                        sql1,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            } else if(!(A.equals(""))){
                pstmt = conn.prepareStatement(
                        sql2,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, A);
            } else if(!(B.equals(""))){
                pstmt = conn.prepareStatement(
                        sql3,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, "%" + B + "%");
            } else{
                pstmt = conn.prepareStatement(
                        sql4,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, C);
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
                for(var k = 0; k<columnCount; k++){
                    String oneRowColumn = res.getString(k+1);
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

    private final Object[][] data = getDBData();

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