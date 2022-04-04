package IIImidProject;

import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

public class MyTableOrders extends AbstractTableModel {
    public static PreparedStatement pstmt;
    private static ResultSet res;

    public MyTableOrders() {
        getDBData();
    }

    public static Object[][] getDBData() {
        Object[][] dataList = new Object[0][];
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){

            String A = NorthwindBackOffice.jtfDS.getText();
            String B = NorthwindBackOffice.jtfDN.getText();
            String C = NorthwindBackOffice.jtfODID.getText();

            if (A.equals("")&&B.equals("")&&C.equals("")) {
                pstmt = conn.prepareStatement(
                        "SELECT * FROM orders",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            } else if(!(A.equals("")) || !(B.equals(""))){
                pstmt = conn.prepareStatement(
                        "SELECT * FROM orders WHERE OrderDate BETWEEN ? AND ?",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, A);
                pstmt.setString(2, B);
            } else if(!(C.equals(""))){
                pstmt = conn.prepareStatement(
                        "SELECT * FROM orders WHERE OrderID = ?",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, C);
            }

            res = pstmt.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();
            dataList = new Object[rowCount][];
            for (var i = 0; res.next(); i++) {
                String[] columns = new String[14];
                for(var k = 0; k<14; k++){
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

    private static String[] getColumnsName() {
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        String[] columns = new String[14]; //欄位數量
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            pstmt = conn.prepareStatement(
                    "SELECT * FROM orders",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            res = pstmt.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            int count = rsmd.getColumnCount();

            for (int x = 0; x < count; x++) {
                columns[x] = rsmd.getColumnName(x + 1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return columns;
    }

    public static String[] columnNames = getColumnsName();

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