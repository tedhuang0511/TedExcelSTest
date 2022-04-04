package IIImidProject;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class MyTableEmployees extends AbstractTableModel {
    public static PreparedStatement pstmt;
    private static ResultSet res;

    public MyTableEmployees() {
        getDBData();
    }

    public static Object[][] getDBData() {
        Object[][] dataList = new Object[0][];

        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            String q = NorthwindBackOffice.jtfLN.getText();
            if (q.equals("")) {
                pstmt = conn.prepareStatement(
                        "SELECT * FROM EMPLOYEES",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            } else {
                pstmt = conn.prepareStatement(
                        "SELECT * FROM EMPLOYEES WHERE LASTNAME = ?",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, q);
            }

            res = pstmt.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();
            dataList = new Object[rowCount][];

            for (var i = 0; res.next(); i++) {
                String[] columns = new String[18];
                for(var k = 0; k<18; k++){
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
        String[] columns = new String[18]; //欄位數量
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            pstmt = conn.prepareStatement(
                    "SELECT * FROM EMPLOYEES",
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

    public static URL getEmpPhotoURL(int row) {
        URL url=null;
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            pstmt = conn.prepareStatement(
                    "SELECT PhotoPath FROM EMPLOYEES WHERE EmployeeID = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pstmt.setInt(1,row+1);
            res = pstmt.executeQuery();
            res.next();
            url = new URL(res.getString(1));
        } catch (Exception e) {
            System.out.println(e);
        }
        return url;
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
        switch (col) {
            case 0: case 1: case 2:case 3:case 4:case 5:case 6:case 7:case 8:case 9:case 10:case 11:case 12:case 13:case 14:case 15:case 16:
                return data[row][col];
            case 17:
                final JButton button = new JButton();
                button.addActionListener(e -> new PictureGetter(row));
                return button;
            default:
                return "Error";
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return switch (column) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 -> false;
            case 17 -> true;
            default -> false;
        };
    }
}