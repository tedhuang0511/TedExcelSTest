package IIImidProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

import javax.swing.table.AbstractTableModel;

public class MyTableOrderDET extends AbstractTableModel {
    public static PreparedStatement pstmt;
    private static ResultSet res;

    public MyTableOrderDET() {
        getDBData();
    }

    public static Object[][] getDBData() {
        Object[][] dataList = new Object[0][];

        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            String q = NorthwindBackOffice.jtfODID.getText();
            if (q.equals("")) {
                pstmt = conn.prepareStatement(
                        "SELECT * FROM orderdetails",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            } else {
                pstmt = conn.prepareStatement(
                        "SELECT * FROM orderdetails WHERE orderID = ?",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, q);
            }
            res = pstmt.executeQuery();
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

    private static String[] getColumnsName() {
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        String[] columns = new String[0]; //欄位數量
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            pstmt = conn.prepareStatement(
                    "SELECT * FROM orderdetails",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            res = pstmt.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            int count = rsmd.getColumnCount();
            columns = new String[count];
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