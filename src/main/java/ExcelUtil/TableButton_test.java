package ExcelUtil;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class TableButton_test extends JFrame {
    private JTable table;
    private MyModel myModel;
    private String[] header = {"編號", "名稱", "地址", "電話"};

    public static URL getEmpPhotoURL() {
        URL url=null;
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/test", prop)){
            //String q = NorthwindBackOffice.jtfLN.getText();
            var pstmt = conn.prepareStatement(
                    "SELECT picpath FROM table01 WHERE id = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pstmt.setInt(1,1);
            var res = pstmt.executeQuery();
            res.next();
            url = new URL(res.getString(1));
        } catch (Exception e) {
            System.out.println(e);
        }
        return url;
    }

    public TableButton_test() {
        setLayout(new BorderLayout());
        myModel = new MyModel();
        table = new JTable(myModel);
        JScrollPane jsp = new JScrollPane(table);
        table.addColumn(new TableColumn(3,200,new ButtonRenderer(),new ButtonEditor()));
        add(jsp, BorderLayout.CENTER);
        setSize(640, 480);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class ButtonRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            button.setText("before"); //$NON-NLS-1$
            return button;
        }
    }
    private class ButtonEditor extends DefaultCellEditor {
        public ButtonEditor() {
            super(new JTextField());
            this.setClickCountToStart(1);
        }
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton button = (JButton)value;
            button.setText("before"); //$NON-NLS-1$

            return button;
        }
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

    public static void main(String[] args) {
        new TableButton_test();
    }

    public class MyModel extends AbstractTableModel {
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

        private Object[][] data = getDBData();

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
            switch (column) {
                case 0:
                    return data[row][column];
                case 1:
                    return data[row][column];
                case 2:
                    return data[row][column];
                case 3:
                    final JButton button = new JButton();
                    button.addActionListener(arg0 -> new PictureGetter_Test());
                    return button;
                default:
                    return "Error";
            }
        }
        @Override
        public boolean isCellEditable(int row, int column) {
            switch(column){
                case 0: case 1: case 2:
                    return false;
                case 3:
                    return true;
                default:
                    return false;
            }
        }

    }
}
