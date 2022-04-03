package IIImidProject;

import ExcelUtil.ExcelUtil;
import ExcelUtil.CSVUtil;
import java.awt.*;


import javax.swing.*;

public class NewPanel extends JPanel {
        public NewPanel() {
        setLayout(new BorderLayout());
    }

    public void customers() {
        removeAll(); NorthwindBackOffice.right.removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableCustomers());
        table.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton btn = new JButton("下載XLSX檔案");
        JButton btn2 = new JButton("下載CSV檔案");
        btn.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableCustomers.getDBData();
                    ExcelUtil.saveTable(path, list,MyTableCustomers.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        btn2.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableCustomers.getDBData();
                    CSVUtil.saveTable(path, list,MyTableCustomers.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        NorthwindBackOffice.right.add(btn); NorthwindBackOffice.right.add(btn2);
    }
    public void employees() {
        removeAll(); NorthwindBackOffice.right.removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableEmployees());
        table.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton btn = new JButton("下載XLSX檔案");JButton btn2 = new JButton("下載CSV檔案");
        btn.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableEmployees.getDBData();
                    ExcelUtil.saveTable(path, list,MyTableEmployees.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        btn2.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableEmployees.getDBData();
                    CSVUtil.saveTable(path, list,MyTableEmployees.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        NorthwindBackOffice.right.add(btn);NorthwindBackOffice.right.add(btn2);
    }
    public void orders() {
        removeAll(); NorthwindBackOffice.right.removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableOrders());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton btn = new JButton("下載XLSX檔案");JButton btn2 = new JButton("下載CSV檔案");
        btn.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrders.getDBData();
                    ExcelUtil.saveTable(path, list, MyTableOrders.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        btn2.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrders.getDBData();
                    CSVUtil.saveTable(path, list,MyTableOrders.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        NorthwindBackOffice.right.add(btn);NorthwindBackOffice.right.add(btn2);
    }
    public void orderDetail() {
        removeAll(); NorthwindBackOffice.right.removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableOrderDET());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton btn = new JButton("下載XLSX檔案");JButton btn2 = new JButton("下載CSV檔案");
        btn.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrderDET.getDBData();
                    ExcelUtil.saveTable(path, list, MyTableOrderDET.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        btn2.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrderDET.getDBData();
                    CSVUtil.saveTable(path, list,MyTableOrderDET.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        NorthwindBackOffice.right.add(btn);NorthwindBackOffice.right.add(btn2);
    }
    public void products() {
        removeAll(); NorthwindBackOffice.right.removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableProducts());
//        table.setPreferredScrollableViewportSize(new Dimension(500, 70));          todo 可有可無的兩行?
//        table.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton btn = new JButton("下載XLSX檔案");JButton btn2 = new JButton("下載CSV檔案");
        btn.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableProducts.getDBData();
                    ExcelUtil.saveTable(path, list, MyTableProducts.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        btn2.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableProducts.getDBData();
                    CSVUtil.saveTable(path, list,MyTableProducts.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        NorthwindBackOffice.right.add(btn);NorthwindBackOffice.right.add(btn2);
    }

/*
    buildPanel>>專門刷新表格JPanel
 */
    public static JPanel buildPanel(Component c){
        JPanel panel = new NewPanel();
        return panel;
    }
}