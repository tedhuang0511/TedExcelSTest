package IIImidProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthwindBackOffice extends JFrame {
    private JButton employees, customers, orders, orderdetails;
    public static JTextField jtf2,jtf3;
    private NewPanel np = new NewPanel();
    public NorthwindBackOffice() {
        setLayout(new BorderLayout());
        JPanel top = new JPanel(new FlowLayout());
        top.setBackground(Color.black);
        JPanel left = new JPanel(new GridLayout(4, 1));
        JPanel right = new JPanel(new FlowLayout());
        right.setBackground(Color.CYAN);
        JPanel south = new JPanel(new FlowLayout());
        south.setBackground(Color.GREEN);

        employees = new JButton("employees");
        customers = new JButton("customers");
        orders = new JButton("Orders");
        orderdetails = new JButton("Orderdetails");

        jtf2 = new JTextField(10);
        jtf3 = new JTextField("輸入customerID");jtf3.setEnabled(false);jtf3.setBackground(Color.black);jtf3.setFont(new Font("諧體",Font.BOLD|Font.ITALIC,12));

        top.add(jtf3);
        top.add(jtf2);

        add(top, BorderLayout.NORTH);
        add(np, BorderLayout.CENTER);
        add(left, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
        add(south, BorderLayout.SOUTH);

        left.add(customers);
        left.add(employees);
        left.add(orders);
        left.add(orderdetails);


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
                System.out.println("p1");
            }
        });

        employees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                np.employees();
                System.out.println("p2");
            }

        });

    }

    public static void main(String[] args) {
        new NorthwindBackOffice();
    }
}


