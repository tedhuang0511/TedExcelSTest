package IIImidProject;
/*
蒿蒿爽爽歐歐
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthwindBackOffice extends JFrame {
    private JButton employees, customers, orders, orderdetails,products,suppliers, query;
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
        query = new JButton("Query!");

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
            top.add(CSID);top.add(jtfCSID);top.add(query);
            query.addActionListener(e12 -> {
                np.customers();
                var a1 = query.getActionListeners();
                query.removeActionListener(a1[0]);
            });
            np.customers();
            System.out.println("cus");
        });

        employees.addActionListener(e -> {
            top.removeAll();
            repaint();
            top.add(LN);top.add(jtfLN);top.add(query);
            query.addActionListener(e12 -> {
                np.employees();
                var a1 = query.getActionListeners();
                query.removeActionListener(a1[0]);
            });
            np.employees();
            System.out.println("emp");
        });

        orders.addActionListener(e -> {
            top.removeAll();
            repaint();
            top.add(DS);top.add(jtfDS);top.add(DN);top.add(jtfDN);top.add(query);
            query.addActionListener(e12 -> {
                np.orders();
                var a1 = query.getActionListeners();
                query.removeActionListener(a1[0]);
            });
            np.orders();
            System.out.println("emp");
        });

        orderdetails.addActionListener(e -> {
            top.removeAll();
            repaint();
            top.add(ODID);top.add(jtfODID);top.add(query);
            query.addActionListener(e12 -> {
                np.orderDetail();
                var a1 = query.getActionListeners();
                query.removeActionListener(a1[0]);
            });
            np.orderDetail();
            System.out.println("od");
        });

    }

    public static void main(String[] args) {
        new NorthwindBackOffice();
    }
}


