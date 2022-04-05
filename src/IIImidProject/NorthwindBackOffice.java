package IIImidProject;
/*
蒿蒿test
 */

import javax.swing.*;
import java.awt.*;

public class NorthwindBackOffice extends JFrame {
    final private JButton employees, customers, orders, orderdetails,products, suppliers, queryCS, queryEMP, queryOD, queryODET,queryPD;
    public static JTextField jtfCSID, CSID,jtfODID,ODID,jtfLN,LN,jtfDS,DS,jtfDN,DN,jtfPID,PID,jtfPN,PN,jtfSPID,SPID;
    public static JLabel label1, maxpage;
    public static JButton firstPage,previousPage,nextPage,lastPage;
    public static JTextField page;
    final private NewPanel newPanel = new NewPanel();
    public static JPanel north;
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
        
        //換頁
        label1 = new JLabel("／");
        label1.setForeground(Color.WHITE);
        maxpage = new JLabel("233");
        maxpage.setForeground(Color.WHITE);
        firstPage = new JButton("<<");
        previousPage = new JButton("<");
        nextPage = new JButton(">");
        lastPage = new JButton(">>");
        page = new JTextField("1",3);
        //換頁
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
        north.setBackground(Color.BLACK);
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
        south.setBackground(Color.BLACK);
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
            addPageButton();
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
    //換頁功能區域//
    public void addPageButton() {
    	north.add(firstPage); north.add(previousPage); north.add(page); north.add(label1); north.add(maxpage); north.add(nextPage); north.add(lastPage);
    	firstPage.addActionListener(e ->{
    		page.setText("1");
    	});
    	previousPage.addActionListener(e ->{
    		if(Integer.parseInt(page.getText())>1) {
    			page.setText(String.valueOf(Integer.parseInt(page.getText())-1));
    		}
    	});
    	page.addActionListener(e ->{
    		newPanel.customers();repaint();
    	});
    	nextPage.addActionListener(e ->{
    		if(Integer.parseInt(page.getText())<Integer.parseInt(maxpage.getText()))
    		page.setText(String.valueOf(Integer.parseInt(page.getText())+1));
    	});
    	lastPage.addActionListener(e ->{
    		page.setText(maxpage.getText());
    	});
    	
    }
    //換頁功能區域//

    public static void main(String[] args) {
        new NorthwindBackOffice();
        //TODO 畫面下方加上JTextarea可輸入SQL語法做增刪修?
        //TODO JF下方新增1.回到第一頁 2.上一頁 3.下一頁 4.最後一頁
    }
}

/*
<< < [1] / maxpage > >>
a  b  c  d    e    f  g

a=>Jbutton放在JF,如果被呼叫就把c改成1
b=>Jbutton放在JF,如果被呼叫就把c-1 (if c>1)
c=>page變數(JTextField.getValue) 預設1 也可以自己輸入數字 (要小於等於maxpage)
	MyTable要拿去用 int start = (page -1) * rpp;
d=>就是一個Jlable
e=>Jlable顯示table rowcount/rpp
f=>Jbutton放在JF,如果被呼叫就把c+1
g=>Jbutton放在JF,如果被呼叫就讓c=e
 */