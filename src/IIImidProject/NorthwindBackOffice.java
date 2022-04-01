package IIImidProject;
/*
蒿蒿爽爽
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthwindBackOffice extends JFrame {
    private JButton employees, customers, orders, orderdetails,products,suppliers;
    public static JTextField jtfCSID, CSID,jtfODID,ODID,jtfLN,LN;
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

        jtfCSID = new JTextField(10);
        CSID = new JTextField("輸入customerID");CSID.setEnabled(false);CSID.setBackground(Color.black);CSID.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfODID = new JTextField(10);
        ODID = new JTextField("輸入orderID");ODID.setEnabled(false);ODID.setBackground(Color.black);ODID.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfLN = new JTextField(10);
        LN = new JTextField("輸入LastName");LN.setEnabled(false);LN.setBackground(Color.black);LN.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));


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

    private void setListener() {
        customers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                top.removeAll();
                repaint();
                top.add(CSID);top.add(jtfCSID);
                np.customers();
                System.out.println("cus");
            }
        });

        employees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                top.removeAll();
                repaint();
                top.add(LN);top.add(jtfLN);
                np.employees();
                System.out.println("emp");
            }

        });

        orderdetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                top.removeAll();
                repaint();
                top.add(ODID);top.add(jtfODID);
                np.orderDetail();
                System.out.println("od");
            }

        });

    }

    public static void main(String[] args) {
        new NorthwindBackOffice();
    }
}


