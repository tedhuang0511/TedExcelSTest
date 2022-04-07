package main.java.IIImidProject;

import java.sql.*;
import java.util.Properties;

public class GetDBData {
    static Properties prop = new Properties();
    static ResultSet res;
    static Connection conn ;
    static PreparedStatement pstmt;
    static int rpp =30;
    public GetDBData() throws SQLException {

    }
    public static int getMaxPage(String sql){
        int rowCount=-1;
        prop.put("user", "root");
        prop.put("password", "");
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/northwind", prop);
            pstmt = conn.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            res = pstmt.executeQuery();
            res.last();//游標放到最後
            rowCount = res.getRow();
            conn.close();
            if(rowCount%rpp==0) {return rowCount/rpp;}
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rowCount/rpp+1;
    }

    public static String[] getColumnsName(String sql){
        String[] columns = new String[0];
        prop.put("user", "root");
        prop.put("password", "");
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/northwind", prop);

            pstmt = conn.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            res = pstmt.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            conn.close();
            int count = rsmd.getColumnCount();//取得欄位數量
            columns = new String[count];
            for (int x = 0; x < count; x++) {
                columns[x] = rsmd.getColumnName(x + 1); //將欄位名稱放入陣列
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }
}