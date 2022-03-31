package ExcelUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Test2 {
    public static void main(String[] args){

        List<String[]> dataList = new ArrayList<String[]>();
        try {
            Properties prop = new Properties();
            prop.put("user", "root");
            prop.put("password", "");

            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/northwind", prop);

            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT * FROM ORDERDETAILS WHERE ORDERID = ?");
            pstmt.setInt(1, 10248);
            ResultSet res = pstmt.executeQuery();
            while(res.next()){
                String orderid = String.valueOf(res.getInt("OrderID"));
                String pid = String.valueOf(res.getInt("ProductID"));
                String up = String.valueOf(res.getBigDecimal("UnitPrice"));
                String qty = String.valueOf(res.getInt("Quantity"));
                String dc = String.valueOf(res.getDouble("Discount"));
                String[] rowConcate = {orderid,pid,up,qty,dc};
                dataList.add(rowConcate);

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }
}
