package IIImidProject;

import IIImidProject.NorthwindBackOffice;

import java.sql.*;
import java.util.Properties;

public class TableDataDBImp {
    public static PreparedStatement pstmt;
    public static Object[][] getOrderDET() {
        Object[][] dataList = new Object[0][];
        try {
            Properties prop = new Properties();
            prop.put("user", "root");
            prop.put("password", "");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/northwind", prop);

            String q = NorthwindBackOffice.jtfODID.getText();
            if(q.equals("")){
                pstmt = conn.prepareStatement(
                        "SELECT * FROM ORDERDETAILS",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            }else{
                pstmt = conn.prepareStatement(
                        "SELECT * FROM ORDERDETAILS WHERE ORDERID = ?",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1,q);
            }

            ResultSet res = pstmt.executeQuery();
            res.last();
            dataList = new Object[res.getRow()][];
            res.beforeFirst();
            for (var i = 0; res.next(); i++) {

                String orderid = String.valueOf(res.getInt("OrderID"));
                String pid = String.valueOf(res.getInt("ProductID"));
                String up = String.valueOf(res.getBigDecimal("UnitPrice"));
                String qty = String.valueOf(res.getInt("Quantity"));
                String dc = String.valueOf(res.getDouble("Discount"));
                String[] rowConcate = {orderid, pid, up, qty, dc};

                dataList[i] = rowConcate;

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return dataList;
    }
    public static Object[][] getCustomers() {
        Object[][] dataList = new Object[0][];
        try {
            Properties prop = new Properties();
            prop.put("user", "root");
            prop.put("password", "");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/northwind", prop);
            String q = NorthwindBackOffice.jtfCSID.getText();
            if(q.equals("")){
                pstmt = conn.prepareStatement(
                        "SELECT * FROM CUSTOMERS",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            }else{
                pstmt = conn.prepareStatement(
                        "SELECT * FROM CUSTOMERS WHERE CUSTOMERID = ?",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1,q);
            }


            ResultSet res = pstmt.executeQuery();

            res.last();
            dataList = new Object[res.getRow()][];
            res.beforeFirst();
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
                String[] rowConcate = {b,c,d,e,f,g,h,O,j,k,l};

                dataList[i] = rowConcate;

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return dataList;
    }
    public static Object[][] getEmployees() {
        Object[][] dataList = new Object[0][];
        try {
            Properties prop = new Properties();
            prop.put("user", "root");
            prop.put("password", "");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/northwind", prop);
            String q = NorthwindBackOffice.jtfLN.getText();
            if(q.equals("")){
                pstmt = conn.prepareStatement(
                        "SELECT * FROM EMPLOYEES",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            }else{
                pstmt = conn.prepareStatement(
                        "SELECT * FROM EMPLOYEES WHERE LASTNAME = ?",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1,q);
            }


            ResultSet res = pstmt.executeQuery();

            res.last();
            dataList = new Object[res.getRow()][];
            res.beforeFirst();
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

                String[] rowConcate = {b,c,d,e,f,g,h,O,j,k,l,A,B,C,D,E,F,G};

                dataList[i] = rowConcate;

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return dataList;
    }
}
