package main.java.IIImidProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class NewPanel extends JPanel {

    public NewPanel() {
        setLayout(new BorderLayout());
    }

    public void customers() {
        removeAll(); NorthwindBackOffice.east.removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableCustomers());
        table.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton downloadXlsx = new JButton("DownloadXLSX");JButton downloadCsv = new JButton("DownloadCSV");JButton downloadAllInXlsx = new JButton("DownloadAll_XLSX");JButton downloadAllInCsv = new JButton("DownloadAll_CSV");
        NorthwindBackOffice.east.add(downloadXlsx); NorthwindBackOffice.east.add(downloadCsv); NorthwindBackOffice.east.add(downloadAllInXlsx); NorthwindBackOffice.east.add(downloadAllInCsv);
        downloadXlsx.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableCustomers.getDBData(1);
                    ExcelUtil.saveTable(path, list,MyTableCustomers.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadAllInXlsx.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableCustomers.getDBData(0);
                    ExcelUtil.saveTable(path, list,MyTableCustomers.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadCsv.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableCustomers.getDBData(1);
                    CSVUtil.saveTable(path, list,MyTableCustomers.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadAllInCsv.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableCustomers.getDBData(0);
                    CSVUtil.saveTable(path, list,MyTableCustomers.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });


    }
    public void employees() {
        removeAll(); NorthwindBackOffice.east.removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableEmployees());
        table.addColumn(new TableColumn(18,75,new ButtonRenderer(),new ButtonEditor()));
        table.setFillsViewportHeight(false);
        table.getColumnModel().removeColumn( table.getColumnModel().getColumn(18)); //把dummy column移除掉
        table.getColumnModel().removeColumn( table.getColumnModel().getColumn(17)); //把photo path移除掉
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton downloadXlsx = new JButton("DownloadXLSX");JButton downloadCsv = new JButton("DownloadCSV");JButton downloadAllInXlsx = new JButton("DownloadAll_XLSX");JButton downloadAllInCsv = new JButton("DownloadAll_CSV");
        NorthwindBackOffice.east.add(downloadXlsx); NorthwindBackOffice.east.add(downloadCsv); NorthwindBackOffice.east.add(downloadAllInXlsx); NorthwindBackOffice.east.add(downloadAllInCsv);

        downloadXlsx.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableEmployees.getDBData(1);
                    ExcelUtil.saveTable(path, list,MyTableEmployees.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadAllInXlsx.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableEmployees.getDBData(0);
                    ExcelUtil.saveTable(path, list,MyTableEmployees.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadCsv.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableEmployees.getDBData(1);
                    CSVUtil.saveTable(path, list,MyTableEmployees.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadAllInCsv.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableEmployees.getDBData(0);
                    CSVUtil.saveTable(path, list,MyTableEmployees.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
    }
    public void orders() {
        removeAll(); NorthwindBackOffice.east.removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableOrders());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton downloadXlsx = new JButton("DownloadXLSX");JButton downloadCsv = new JButton("DownloadCSV");JButton downloadAllInXlsx = new JButton("DownloadAll_XLSX");JButton downloadAllInCsv = new JButton("DownloadAll_CSV");
        NorthwindBackOffice.east.add(downloadXlsx); NorthwindBackOffice.east.add(downloadCsv); NorthwindBackOffice.east.add(downloadAllInXlsx); NorthwindBackOffice.east.add(downloadAllInCsv);
        downloadXlsx.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrders.getDBData(1);
                    ExcelUtil.saveTable(path, list, MyTableOrders.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadAllInXlsx.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrders.getDBData(0);
                    ExcelUtil.saveTable(path, list, MyTableOrders.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadCsv.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrders.getDBData(1);
                    CSVUtil.saveTable(path, list,MyTableOrders.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadAllInCsv.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrders.getDBData(0);
                    CSVUtil.saveTable(path, list,MyTableOrders.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
    }
    public void orderDetail() {
        removeAll(); NorthwindBackOffice.east.removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableOrderDET());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton downloadXlsx = new JButton("DownloadXLSX");JButton downloadCsv = new JButton("DownloadCSV");JButton downloadAllInXlsx = new JButton("DownloadAll_XLSX");JButton downloadAllInCsv = new JButton("DownloadAll_CSV");
        NorthwindBackOffice.east.add(downloadXlsx); NorthwindBackOffice.east.add(downloadCsv); NorthwindBackOffice.east.add(downloadAllInXlsx); NorthwindBackOffice.east.add(downloadAllInCsv);
        downloadXlsx.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrderDET.getDBData(1);
                    ExcelUtil.saveTable(path, list, MyTableOrderDET.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadAllInXlsx.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrderDET.getDBData(0);
                    ExcelUtil.saveTable(path, list, MyTableOrderDET.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadCsv.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrderDET.getDBData(1);
                    CSVUtil.saveTable(path, list,MyTableOrderDET.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadAllInCsv.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableOrderDET.getDBData(0);
                    CSVUtil.saveTable(path, list,MyTableOrderDET.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
    }
    public void products() {
        removeAll(); NorthwindBackOffice.east.removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableProducts());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);
        
        JButton downloadXlsx = new JButton("DownloadXLSX");JButton downloadCsv = new JButton("DownloadCSV");JButton downloadAllInXlsx = new JButton("DownloadAll_XLSX");JButton downloadAllInCsv = new JButton("DownloadAll_CSV");
        NorthwindBackOffice.east.add(downloadXlsx); NorthwindBackOffice.east.add(downloadCsv); NorthwindBackOffice.east.add(downloadAllInXlsx); NorthwindBackOffice.east.add(downloadAllInCsv);
        downloadXlsx.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableProducts.getDBData(1);
                    ExcelUtil.saveTable(path, list, MyTableProducts.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadAllInXlsx.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableProducts.getDBData(0);
                    ExcelUtil.saveTable(path, list, MyTableProducts.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadCsv.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableProducts.getDBData(1);
                    CSVUtil.saveTable(path, list,MyTableProducts.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
        downloadAllInCsv.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    String path = jfc.getSelectedFile().getAbsolutePath();
                    Object[][] list = MyTableProducts.getDBData(0);
                    CSVUtil.saveTable(path, list,MyTableProducts.columnNames);
                    JOptionPane.showMessageDialog(null, "存檔成功");
                }catch (Exception eee) {
                    JOptionPane.showMessageDialog(null, "存檔失敗");
                }
            }
        });
    }

    private class ButtonRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton)value;
            button.setText("SHOW"); //$NON-NLS-1$
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
            button.setText("SHOW"); //$NON-NLS-1$
            return button;
        }
    }

    public static JPanel buildPanel(Component c){  //專門刷新panel
        JPanel panel = new NewPanel();
        return panel;
    }

}