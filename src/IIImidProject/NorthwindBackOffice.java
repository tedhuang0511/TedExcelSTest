package IIImidProject;
/*
蒿蒿爽爽歐歐
 */
import ExcelUtil.PictureGetter;

import javax.swing.*;
import java.awt.*;

public class NorthwindBackOffice extends JFrame {
    private JButton employees, customers, orders, orderdetails,products,suppliers, queryCS, queryEMP, queryOD, queryODET,queryPD,showPic;
    public static JTextField jtfCSID, CSID,jtfODID,ODID,jtfLN,LN,jtfDS,DS,jtfDN,DN,jtfPID,PID,jtfPN,PN,jtfSPID,SPID;
    private NewPanel np = new NewPanel();
    private JPanel top;
    public NorthwindBackOffice() {
        setLayout(new BorderLayout());
        top = new JPanel(new FlowLayout());
        top.setBackground(Color.black);
        JPanel left = new JPanel(new GridLayout(6, 1));
        JPanel right = new JPanel(new FlowLayout());
        right.setBackground(Color.CYAN);
        JPanel south = new JPanel(new FlowLayout());
        south.setBackground(Color.GREEN);
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
        showPic = new JButton("showPic!");
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

        add(top, BorderLayout.NORTH);
        add(np, BorderLayout.CENTER);
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
        add(south, BorderLayout.SOUTH);

        left.add(customers);
        left.add(employees);
        left.add(orders);
        left.add(orderdetails);
        left.add(products);
        left.add(suppliers);


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
            top.removeAll();
            repaint();
            top.add(CSID);top.add(jtfCSID);top.add(queryCS);
            np.customers();
            System.out.println("presscustomers");
        });
        queryCS.addActionListener(e -> {
            np.customers();repaint();
        });

        employees.addActionListener(e -> {
            top.removeAll();
            repaint();
            top.add(LN);top.add(jtfLN);top.add(queryEMP);top.add(showPic);
            np.employees();
        });
        queryEMP.addActionListener(e -> {
            np.employees();repaint();
        });
        showPic.addActionListener(e ->{
            new PictureGetter();
        });

        orders.addActionListener(e -> {
            top.removeAll();
            repaint();
            top.add(ODID);top.add(jtfODID);top.add(DS);top.add(jtfDS);top.add(DN);top.add(jtfDN);top.add(queryOD);
            np.orders();
        });
        queryOD.addActionListener(e -> {
            np.orders(); repaint();
        });

        orderdetails.addActionListener(e -> {
            top.removeAll();
            repaint();
            top.add(ODID);top.add(jtfODID);top.add(queryODET);
            np.orderDetail();
        });
        queryODET.addActionListener(e ->{
            np.orderDetail();repaint();
        });

        products.addActionListener(e -> {
            top.removeAll();
            repaint();
            top.add(PID);top.add(jtfPID);top.add(PN);top.add(jtfPN);top.add(SPID);top.add(jtfSPID);top.add(queryPD);
            np.products();
        });
        queryPD.addActionListener(e ->{
            np.products();repaint();
        });

    }

    public static void main(String[] args) {
        new NorthwindBackOffice();
        //todo 表格放入Jbutton?
        //todo 新增.csv下載按鈕跟.txt
    }
}
