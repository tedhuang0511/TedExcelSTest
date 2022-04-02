package IIImidProject;
/*
蒿蒿爽爽歐歐
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthwindBackOffice extends JFrame {
    private JButton employees, customers, orders, orderdetails,products,suppliers, queryCS, queryEMP, queryOD, queryODET;
    public static JTextField jtfCSID, CSID,jtfODID,ODID,jtfLN,LN,jtfDS,DS,jtfDN,DN;
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

        jtfCSID = new JTextField(10);
        CSID = new JTextField("輸入customerID");CSID.setEnabled(false);CSID.setBackground(Color.black);CSID.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfODID = new JTextField(10);
        ODID = new JTextField("輸入orderID");ODID.setEnabled(false);ODID.setBackground(Color.black);ODID.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfLN = new JTextField(10);
        LN = new JTextField("輸入LastName");LN.setEnabled(false);LN.setBackground(Color.black);LN.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfDS = new JTextField(10);
        DS = new JTextField("DateFrom：");DS.setEnabled(false);DS.setBackground(Color.black);DS.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfDN = new JTextField(10);
        DN = new JTextField("DateTo：");DN.setEnabled(false);DN.setBackground(Color.black);DN.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));

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
            System.out.println("cus");
        });
        queryCS.addActionListener(e -> {
            np.customers();repaint();
        });

        employees.addActionListener(e -> {
            top.removeAll();
            repaint();
            top.add(LN);top.add(jtfLN);top.add(queryEMP);
            np.employees();
            System.out.println("emp");
        });

        queryEMP.addActionListener(e -> {
            np.employees();repaint();
        });

        orders.addActionListener(e -> {
            top.removeAll();
            repaint();
            top.add(ODID);top.add(jtfODID);top.add(DS);top.add(jtfDS);top.add(DN);top.add(jtfDN);top.add(queryOD);
            np.orders();
            System.out.println("emp");
        });
        queryOD.addActionListener(e -> {
            np.orders(); repaint();
        });

        orderdetails.addActionListener(e -> {
            top.removeAll();
            repaint();
            top.add(ODID);top.add(jtfODID);top.add(queryODET);
            np.orderDetail();
            System.out.println("od");
        });
        queryODET.addActionListener(e ->{
            np.orderDetail();repaint();
        });

    }

    public static void main(String[] args) {
        new NorthwindBackOffice();
    }
}


