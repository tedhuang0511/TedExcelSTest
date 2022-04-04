package IIImidProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

                String b = res.getString(1);
                String c = res.getString(2);
                String d = res.getString(3);
                String e = res.getString(4);
                String f = res.getString(5);
                String g = res.getString(6);
                String h = res.getString(7);
                String O = res.getString(8);
                String j = res.getString(9);
                String k = res.getString(10);
                String l = res.getString(11);
                String A = res.getString(12);
                String B = res.getString(13);
                String C = res.getString(14);
                String D = res.getString(15);
                String E = res.getString(16);
                String F = res.getString(17);
                String G = res.getString(18);

                String[] rowConcate = {b, c, d, e, f, g, h, O, j, k, l, A, B, C, D, E, F, G};


                dataList[i] = rowConcate;

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
//            String q = NorthwindBackOffice.jtfLN.getText();

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

    private Object[][] data = getDBData();

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
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new PictureGetter(row);
                        System.out.println("show"+row+col);
                    }
                });
                return button;
            default:
                return "Error";
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        switch(column){
            case 0: case 1: case 2:case 3:case 4:case 5:case 6:case 7:case 8:case 9:case 10:case 11:case 12:case 13:case 14:case 15:case 16:
                return false;
            case 17:
                return true;
            default:
                return false;
        }
    }
}