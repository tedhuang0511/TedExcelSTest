package IIImidProject;
/*
蒿蒿爽爽歐歐
 */

import javax.swing.*;
import java.awt.*;

public class NorthwindBackOffice extends JFrame {
    final private JButton employees, customers, orders, orderdetails,products, suppliers, queryCS, queryEMP, queryOD, queryODET,queryPD;
    public static JTextField jtfCSID, CSID,jtfODID,ODID,jtfLN,LN,jtfDS,DS,jtfDN,DN,jtfPID,PID,jtfPN,PN,jtfSPID,SPID;
    final private NewPanel newPanel = new NewPanel();
    final private JPanel north;
    public static JPanel east;
    public NorthwindBackOffice() {
        setLayout(new BorderLayout());
        setTitle("北風後台管理系統");
/*
按鈕區域
 */
        employees = new JButton("Employees");
        customers = new JButton("Customers");
        orders = new JButton("Orders");
        orderdetails = new JButton("Orderdetails");
        products = new JButton("Products");
        suppliers = new JButton("Suppliers");
        queryCS = new JButton("Query!");
        queryEMP = new JButton("Query!");
        queryOD = new JButton("Query!");
        queryODET = new JButton("Query!");
        queryPD = new JButton("Query!");
/*
上方搜尋框與標籤欄位
 */
        jtfCSID = new JTextField(10);
        CSID = new JTextField("輸入customerID");CSID.setEnabled(false);CSID.setBackground(Color.black);CSID.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfODID = new JTextField(10);
        ODID = new JTextField("輸入orderID");ODID.setEnabled(false);ODID.setBackground(Color.black);ODID.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfLN = new JTextField(10);
        LN = new JTextField("輸入LastName");LN.setEnabled(false);LN.setBackground(Color.black);LN.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfDS = new JTextField(10);
        DS = new JTextField("OrderDateFrom：");DS.setEnabled(false);DS.setBackground(Color.black);DS.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfDN = new JTextField(10);
        DN = new JTextField("OrderDateTo：");DN.setEnabled(false);DN.setBackground(Color.black);DN.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfPID = new JTextField(10);
        PID = new JTextField("輸入ProductID：");PID.setEnabled(false);PID.setBackground(Color.black);PID.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfPN = new JTextField(10);
        PN = new JTextField("輸入ProductName：");PN.setEnabled(false);PN.setBackground(Color.black);PN.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfSPID = new JTextField(10);
        SPID = new JTextField("輸入SupplierID");SPID.setEnabled(false);SPID.setBackground(Color.black);SPID.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));

        north = new JPanel(new FlowLayout());
        north.setBackground(Color.black);
        add(north, BorderLayout.NORTH);

        add(newPanel, BorderLayout.CENTER);

        JPanel left = new JPanel(new GridLayout(6, 1));
        add(left, BorderLayout.WEST);
        left.add(customers);
        left.add(employees);
        left.add(orders);
        left.add(orderdetails);
        left.add(products);
        left.add(suppliers);

        east = new JPanel(new GridLayout(3, 1));
        east.setBackground(Color.CYAN);
        add(east, BorderLayout.EAST);

        JPanel south = new JPanel(new FlowLayout());
        south.setBackground(Color.GREEN);
        add(south, BorderLayout.SOUTH);

        setSize(1680, 860);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setListener();
    }
/*
下方為各種table按鈕的listener。
 */
    private void setListener() {
        customers.addActionListener(e -> {
            north.removeAll();//把視窗北邊的panel全部清空
            repaint();//重新繪製避免殘影
            north.add(CSID);//新增搜尋欄位名稱
            north.add(jtfCSID);//新增搜尋欄位
            north.add(queryCS);//新增搜尋按鈕
            newPanel.customers();//視窗CENTRAL的panel呼叫customers資料表
            System.out.println("presscustomers");
        });
        queryCS.addActionListener(e -> {
            newPanel.customers();repaint();//點擊query按鈕時重新呼叫customers資料表
        });

        employees.addActionListener(e -> {
            north.removeAll();
            repaint();
            north.add(LN);
            north.add(jtfLN);
            north.add(queryEMP);
            newPanel.employees();
        });
        queryEMP.addActionListener(e -> {
            newPanel.employees();repaint();
        });

        orders.addActionListener(e -> {
            north.removeAll();
            repaint();
            north.add(ODID);
            north.add(jtfODID);
            north.add(DS);
            north.add(jtfDS);
            north.add(DN);
            north.add(jtfDN);
            north.add(queryOD);
            newPanel.orders();
        });
        queryOD.addActionListener(e -> {
            newPanel.orders(); repaint();
        });

        orderdetails.addActionListener(e -> {
            north.removeAll();
            repaint();
            north.add(ODID);
            north.add(jtfODID);
            north.add(queryODET);
            newPanel.orderDetail();
        });
        queryODET.addActionListener(e ->{
            newPanel.orderDetail();repaint();
        });

        products.addActionListener(e -> {
            north.removeAll();
            repaint();
            north.add(PID);
            north.add(jtfPID);
            north.add(PN);
            north.add(jtfPN);
            north.add(SPID);
            north.add(jtfSPID);
            north.add(queryPD);
            newPanel.products();
        });
        queryPD.addActionListener(e ->{
            newPanel.products();repaint();
        });

    }

    public static void main(String[] args) {
        new NorthwindBackOffice();
        //TODO 畫面下方加上JTextarea可輸入SQL語法做增刪修?
        //TODO JF下方新增1.回到第一頁 2.上一頁 3.下一頁 4.最後一頁
    }
}