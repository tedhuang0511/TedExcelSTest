package IIImidProject;

import ExcelUtil.ExcelUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class NewPanel extends JPanel {
    public NewPanel() {
        setLayout(new BorderLayout());

    }

    public void customers() {
        removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableCustomers());
        table.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton btn = new JButton("customers下載");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
        add(btn,BorderLayout.EAST);
    }

    public void employees() {
        removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableEmployees());
        table.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton btn = new JButton("employees下載");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
        add(btn,BorderLayout.EAST);
    }

    public static JPanel buildPanel(Component c){
        JPanel panel = new NewPanel();
        return panel;
    }

    public void orderDetail() {
        removeAll();
        add(buildPanel(new NewPanel()));
        revalidate();

        JTable table = new JTable(new MyTableOrderDET());
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(false);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);

        JButton btn = new JButton("Order_det下載");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });
        add(btn,BorderLayout.EAST);
    }

}