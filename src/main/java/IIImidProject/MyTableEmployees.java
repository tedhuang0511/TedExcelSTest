package main.java.IIImidProject;

import java.awt.*;
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
    static int page;
    static int rpp =30;
    static int start;
    private static final String sql = "select * from EMPLOYEES";
    private static final Properties prop = new Properties();

    public MyTableEmployees() {
        NorthwindBackOffice.maxpage.setText(String.valueOf(GetDBData.getMaxPage(sql)));
        NorthwindBackOffice.maxpage.setForeground(Color.WHITE);
        getDBData(1);
    }

    public static Object[][] getDBData(int iii) {
        Object[][] dataList = new Object[0][];
        prop.put("user", "root");
        prop.put("password", "");
        page = NorthwindBackOffice.getPageEMP();
        start = (page -1) * rpp;
        String presql = sql+" LIMIT %d ,%d";
        String sql1 = String.format(presql, start, rpp);
        String sql2 = sql + " WHERE LASTNAME = ?"; //上方搜尋框的sql
        PreparedStatement pstmt;
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            String q = NorthwindBackOffice.jtfLN.getText();
            if(iii==0) {
            	pstmt = conn.prepareStatement(
                        sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            }else {
            	if (q.equals("")) {
                    pstmt = conn.prepareStatement(
                            sql1,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                } else {
                    pstmt = conn.prepareStatement(
                            sql2,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    pstmt.setString(1, q);
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

    public static URL getEmpPhotoURL(int row) {
        URL url=null;
        prop.put("user", "root");
        prop.put("password", "");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            var pstmt = conn.prepareStatement(
                    "SELECT PhotoPath FROM EMPLOYEES WHERE EmployeeID = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pstmt.setInt(1,row+1);
            var res = pstmt.executeQuery();
            res.next();
            url = new URL(res.getString(1));
        } catch (Exception e) {
            System.out.println(e);
        }
        return url;
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
        if (col == 18) {
            final JButton button = new JButton();
            button.addActionListener(e -> new PictureGetter(row));
            return button;
        }
        return data[row][col];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return switch (column) {
            case 18 -> true;
            default -> false;
        };
    }
}