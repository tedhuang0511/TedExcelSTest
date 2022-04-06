package IIImidProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Properties;

import javax.swing.table.AbstractTableModel;

public class MyTableCustomers extends AbstractTableModel {
	public static PreparedStatement pstmt;
    private static ResultSet res;
    static int page;
    static int rpp =30;
    static int start;
    static int maxPage;
    private static String sql = "select * from CUSTOMERS";
    private static Properties prop = new Properties();
    
    public MyTableCustomers() {
        getDBData();
    }
    
    public static int getMaxPage() {
    	int rowCount = -1;
        prop.put("user", "root");
        prop.put("password", "");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
        	pstmt = conn.prepareStatement(
        			sql,
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
        prop.put("user", "root");
        prop.put("password", "");
        
        page = NorthwindBackOffice.getPage();
        start = (page -1) * rpp;
        String presql = sql + " LIMIT %d ,%d";
		String sql2 = String.format(presql, start, rpp);
		String sql3 = sql +  " WHERE customerID = ?";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            String q = NorthwindBackOffice.jtfCSID.getText();
            if (q.equals("")) {
                pstmt = conn.prepareStatement(
                		sql2,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
            } else {
                pstmt = conn.prepareStatement(
                        sql3,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                pstmt.setString(1, q);
            }
            res = pstmt.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            int columncount = rsmd.getColumnCount();//取得欄位數量
            res.last();//游標放到最後
            int rowCount = res.getRow();//取得最後一行(int) = 總行數
            res.beforeFirst();//游標回到最前面
            dataList = new Object[rowCount][];
            for (var i = 0; res.next(); i++) {
                String[] columns = new String[columncount];
                for(var k = 0; k<columncount; k++){
                    String oneRowColumn = res.getString(k+1); //取得第一行第一欄.第一行第二欄....第一行最後一欄的資料
                    columns[k] = oneRowColumn;
                }
                dataList[i] = columns; // 把每一行全部欄位的資料放入Object[i]
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return dataList;
    }

    private static String[] getColumnsName() {
        prop.put("user", "root");
        prop.put("password", "");
        String[] columns = new String[0];//欄位陣列初始化
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            pstmt = conn.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            res = pstmt.executeQuery();
            ResultSetMetaData rsmd = res.getMetaData();
            int count = rsmd.getColumnCount();//取得欄位數量
            columns = new String[count];
            for (int x = 0; x < count; x++) {
                columns[x] = rsmd.getColumnName(x + 1); //將欄位名稱放入陣列
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