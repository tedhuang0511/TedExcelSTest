package IIImidProject;
//test
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthwindBackOffice extends JFrame {
    private JButton employees, customers, orders, orderdetails,products,suppliers;
    public static JTextField jtfCSID,CSID,jtfODID,ODID,jtfLN,LN;
    private JPanel Jtop = new JPanel(new FlowLayout());
    private NewPanel np = new NewPanel();
    public NorthwindBackOffice() {
        setLayout(new BorderLayout());
        
        Jtop.setBackground(Color.black);
        JPanel left = new JPanel(new GridLayout(6, 1));
        JPanel right = new JPanel(new FlowLayout());
        right.setBackground(Color.CYAN);
        JPanel south = new JPanel(new FlowLayout());
        south.setBackground(Color.GREEN);

        employees = new JButton("Employees");
        products = new JButton("Products");
        suppliers = new JButton("Suppliers");
        customers = new JButton("Customers");
        orders = new JButton("Orders");
        orderdetails = new JButton("Orderdetails");

        jtfCSID = new JTextField(10);
        CSID = new JTextField("輸入customerID");CSID.setEnabled(false);CSID.setBackground(Color.black);CSID.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfODID = new JTextField(10);
        ODID = new JTextField("輸入orderID");ODID.setEnabled(false);ODID.setBackground(Color.black);ODID.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));
        jtfLN = new JTextField(10);
        LN = new JTextField("輸入LastName");LN.setEnabled(false);LN.setBackground(Color.black);LN.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));


        add(Jtop, BorderLayout.NORTH);
        add(np, BorderLayout.CENTER);
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
        

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
                np.customers();
                Jtop.removeAll();
                repaint();
                Jtop.add(CSID);
                Jtop.add(jtfCSID);
                System.out.println("p1");
            }
        });
        
        employees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                np.employees(); 
                Jtop.removeAll();
                repaint();
                Jtop.add(LN);
                Jtop.add(jtfLN);
            }
        });

        orderdetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                np.orderDetail();
                Jtop.removeAll();
                repaint();
                Jtop.add(ODID);
                Jtop.add(jtfODID);
                
                System.out.println("p2");
            }

        });

    }

    public static void main(String[] args) {
        new NorthwindBackOffice();
    }
}


