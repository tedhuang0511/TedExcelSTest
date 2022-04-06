package IIImidProject;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;

import javax.swing.table.AbstractTableModel;

public class MyTableOrderDET extends AbstractTableModel {
    static PreparedStatement pstmt;
    static int page;
    static int rpp =30;
    static int start;
    private static String sql = "select * from ORDERDETAILS";

    public MyTableOrderDET() {
        NorthwindBackOffice.maxpage.setText(String.valueOf(GetDBData.getMaxPage(sql)));
        NorthwindBackOffice.maxpage.setForeground(Color.WHITE);
        getDBData();
    }

    public static Object[][] getDBData() {
        Object[][] dataList = new Object[0][];
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        page = NorthwindBackOffice.getPageODET();
        start = (page -1) * rpp;
        String presql = sql+" LIMIT %d ,%d";
		String sql1 = String.format(presql, start, rpp);
        String sql2 = sql+" WHERE orderID = ?";
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
            String q = NorthwindBackOffice.jtfODID.getText();
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
            var res = pstmt.executeQuery();
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

    public static String[] columnNames = GetDBData.getColumnsName(sql);

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