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
    static int page;
    static int rpp =30;
    static int start;
    static int maxPage;

    public MyTableEmployees() {
        getDBData();
    }
    
    public static int getMaxPage() {
    	int rowCount = -1;
    	Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
        	pstmt = conn.prepareStatement(
        			"SELECT * FROM employees",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        	res = pstmt.executeQuery();
            res.last();//游標放到最後
            rowCount = res.getRow();
        }catch(Exception e) {
        	System.out.println(e);
        }
        if(rowCount%rpp==0) {return rowCount/rpp;}
        
    	return rowCount/rpp+1;
    }
    
    public static Object[][] getDBData() {
        Object[][] dataList = new Object[0][];

        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        page = NorthwindBackOffice.getPageEMP();
        start = (page -1) * rpp;
        String presql = "SELECT * FROM EMPLOYEES LIMIT %d ,%d";
		String sql = String.format(presql, start, rpp);

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            String q = NorthwindBackOffice.jtfLN.getText();
            if (q.equals("")) {
                pstmt = conn.prepareStatement(
                        sql,
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
            ResultSetMetaData rsmd = res.getMetaData();
            int columnCount = rsmd.getColumnCount();
            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();
            dataList = new Object[rowCount][];
            for (var i = 0; res.next(); i++) {
                String[] columns = new String[columnCount];
                for(var k = 0; k<columnCount-1; k++){
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
                    "SELECT * FROM EMPLOYEES",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            res = pstmt.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            int count = rsmd.getColumnCount();
            columns = new String[count];
            for (int x = 0; x < count-1; x++) {
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
            case 0: case 1: case 2:case 3:case 4:case 5:case 6:case 7:case 8:case 9:case 10:case 11:case 12:case 13:case 14:case 15:case 16: case 17:
                return data[row][col];
            case 18:
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
            case 18 -> true;
            default -> false;
        };
    }
}