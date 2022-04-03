package ExcelUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class Test3 extends JFrame{
    private JTable table;
    private MyModel myModel;
    private String[] header = {"編號","名稱","地址","電話"};

    public Test3() {

        setLayout(new BorderLayout());

        myModel = new MyModel();
        table = new JTable(myModel);
        JScrollPane jsp = new JScrollPane(table);
        add(jsp, BorderLayout.CENTER);

        setSize(640, 480);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static Object[][] getDBData() {
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");

        String sql = "SELECT * FROM table01";
        Object[][] dataList = new Object[0][];
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost/test", prop);
            PreparedStatement ps = conn.prepareStatement(
                    sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = ps.executeQuery();
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();
            dataList = new Object[rowCount][];
            for (var i = 0; rs.next(); i++) {
                String b = rs.getString(1);
                String c = rs.getString(2);
                String d = rs.getString(3);
                String e = rs.getString(4);

                String[] rowConcate = {b, c, d, e};
                dataList[i] = rowConcate;
            }


        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return dataList;
    }

    public class MyModel extends DefaultTableModel {
        private static ResultSet rs;
        private static int rowCount;

        public MyModel() {
            getDBData();
        }

        public static Object[][] getDBData() {
            Properties prop = new Properties();
            prop.put("user", "root");
            prop.put("password", "");

            String sql = "SELECT * FROM table01";
            Object[][] dataList = new Object[0][];
            try {
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost/test", prop);
                PreparedStatement ps = conn.prepareStatement(
                        sql,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);

                rs = ps.executeQuery();
                rs.last();
                rowCount = rs.getRow();
                rs.beforeFirst();
                dataList = new Object[rowCount][];
                for (var i = 0; rs.next(); i++) {
                    String b = rs.getString(1);
                    String c = rs.getString(2);
                    String d = rs.getString(3);
                    String e = rs.getString(4);

                    String[] rowConcate = {b, c, d, e};
                    dataList[i] = rowConcate;
                }


            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return dataList;
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public String getColumnName(int column) {
            return header[column];
        }

        @Override
        public int getRowCount() {
            return rowCount;
        }

        @Override
        public Object getValueAt(int row, int column) {
            Object ret;
            try {
                rs.absolute(row + 1);
                InputStream in = rs.getBinaryStream(column +1);
                ObjectInputStream oin = new ObjectInputStream(in);
                Object obj = oin.readObject();
                oin.close();
                ret = obj;
            } catch (Exception e) {
                ret = "XXX";
            }
            return ret;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
    public static void main(String[] args) {
        new Test3();
    }

}
