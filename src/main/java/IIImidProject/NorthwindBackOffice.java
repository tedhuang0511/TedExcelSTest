package main.java.IIImidProject;
/*
123
 */

import javax.swing.*;
import java.awt.*;

public class NorthwindBackOffice extends JFrame {
	final private JButton employees, customers, orders, orderdetails, products, suppliers, queryCS, queryEMP, queryOD,queryODET, queryPD;
	public static JTextField jtfCSID, CSID, jtfODID, ODID, jtfLN, LN, jtfDS, DS, jtfDN, DN, jtfPID, PID, jtfPN, PN,jtfSPID, SPID;
	public static JLabel label1, maxpage, unuse1;
	private static JButton firstPage, previousPage, nextPage, lastPage, firstPageEMP, previousPageEMP, nextPageEMP,lastPageEMP, firstPageOD, previousPageOD, nextPageOD, lastPageOD, firstPageODET, previousPageODET, nextPageODET, lastPageODET,firstPagePD, previousPagePD, nextPagePD, lastPagePD;
	public static JTextField page, pageEMP, pageOD, pageODET, pagePD;
	protected final NewPanel newPanel = new NewPanel();
	public static JPanel north; private static JPanel south; public static JPanel east;

	public NorthwindBackOffice() {
		setLayout(new BorderLayout());
		setTitle("北風後台管理系統");
		/*
		 * 按鈕區域
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

		//調整排版用的空label
		unuse1 = new JLabel("　　　　　　　　　　　　　　");

		// 換頁
		label1 = new JLabel("／");
		label1.setForeground(Color.WHITE);
		maxpage = new JLabel();
		firstPage = new JButton("<<");
		previousPage = new JButton("<");
		nextPage = new JButton(">");
		lastPage = new JButton(">>");
		page = new JTextField("1", 3);
		firstPageEMP = new JButton("<<");
		previousPageEMP = new JButton("<");
		nextPageEMP = new JButton(">");
		lastPageEMP = new JButton(">>");
		pageEMP = new JTextField("1", 3);
		firstPageOD = new JButton("<<");
		previousPageOD = new JButton("<");
		nextPageOD = new JButton(">");
		lastPageOD = new JButton(">>");
		pageOD = new JTextField("1", 3);
		firstPageODET = new JButton("<<");
		previousPageODET = new JButton("<");
		nextPageODET = new JButton(">");
		lastPageODET = new JButton(">>");
		pageODET = new JTextField("1", 3);
		firstPagePD = new JButton("<<");
		previousPagePD = new JButton("<");
		nextPagePD = new JButton(">");
		lastPagePD = new JButton(">>");
		pagePD = new JTextField("1", 3);
		// 換頁END
		// 上方搜尋框與標籤欄位
		jtfCSID = new JTextField(10);
		CSID = new JTextField("輸入customerID");CSID.setEnabled(false);CSID.setBackground(Color.black);CSID.setFont(new Font("諧體", Font.BOLD | Font.ITALIC, 12));
		jtfODID = new JTextField(10);
		ODID = new JTextField("輸入orderID");ODID.setEnabled(false);ODID.setBackground(Color.black);ODID.setFont(new Font("諧體", Font.BOLD | Font.ITALIC, 12));
		jtfLN = new JTextField(10);
		LN = new JTextField("輸入LastName");LN.setEnabled(false);LN.setBackground(Color.black);LN.setFont(new Font("諧體", Font.BOLD | Font.ITALIC, 12));
		jtfDS = new JTextField(10);
		DS = new JTextField("OrderDateFrom：");DS.setEnabled(false);DS.setBackground(Color.black);DS.setFont(new Font("諧體", Font.BOLD | Font.ITALIC, 12));
		jtfDN = new JTextField(10);
		DN = new JTextField("OrderDateTo：");DN.setEnabled(false);DN.setBackground(Color.black);DN.setFont(new Font("諧體", Font.BOLD | Font.ITALIC, 12));
		jtfPID = new JTextField(10);
		PID = new JTextField("輸入ProductID：");PID.setEnabled(false);PID.setBackground(Color.black);PID.setFont(new Font("諧體", Font.BOLD | Font.ITALIC, 12));
		jtfPN = new JTextField(10);
		PN = new JTextField("輸入ProductName：");PN.setEnabled(false);PN.setBackground(Color.black);PN.setFont(new Font("諧體", Font.BOLD | Font.ITALIC, 12));
		jtfSPID = new JTextField(10);
		SPID = new JTextField("輸入SupplierID");SPID.setEnabled(false);SPID.setBackground(Color.black);SPID.setFont(new Font("諧體", Font.BOLD | Font.ITALIC, 12));
		// 上方搜尋框與標籤欄位END

		north = new JPanel(new FlowLayout());
		north.setBackground(Color.BLACK);
		add(north, BorderLayout.NORTH);
		add(newPanel, BorderLayout.CENTER);
		JPanel west = new JPanel(new GridLayout(6, 1));
		add(west, BorderLayout.WEST);
		west.add(customers);
		west.add(employees);
		west.add(orders);
		west.add(orderdetails);
		west.add(products);
		west.add(suppliers);

		//xlsx、csv下載按鈕,在NewPanel的時候隨著各table的方法被新增按鈕上去
		east = new JPanel(new GridLayout(4, 1));
		east.setBackground(Color.orange);
		add(east, BorderLayout.EAST);
		//xlsx、csv下載按鈕END---

		//報表按鈕區域
		south = new JPanel(new GridLayout(1, 3));
		south.setBackground(Color.BLACK);south.setSize(1680,100);
		add(south, BorderLayout.SOUTH);
		JButton productSalesReportBTN = new JButton("Product Sales Report");
		productSalesReportBTN.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		productSalesReportBTN.setBackground(Color.PINK);
		productSalesReportBTN.addActionListener(e -> {
			JFrame jf = new MostSalesProductPieChart();
		});
		JButton bestSellerBTN = new JButton("Best Employee Report");
		bestSellerBTN.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		bestSellerBTN.setBackground(Color.PINK);
		bestSellerBTN.addActionListener(e ->{
			JFrame jf = new BestSalesChart();
		});
		JButton salesReportBTN = new JButton("Sales Report");
		salesReportBTN.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
		salesReportBTN.setBackground(Color.PINK);
		salesReportBTN.addActionListener(e ->{
			JFrame jf = new SalesAmountChart();
		});
		south.add(productSalesReportBTN);south.add(bestSellerBTN);south.add(salesReportBTN);
		//報表按鈕區域END---

		setSize(1680, 860);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setListener();
	}

	/*
	 * 下方為各種table按鈕的listener。
	 */
	private void setListener() {
		// CS TABLE
		customers.addActionListener(e -> {
			north.removeAll();// 把視窗北邊的panel全部清空
			repaint();// 重新繪製避免殘影
			north.add(CSID);// 新增搜尋欄位名稱
			north.add(jtfCSID);// 新增搜尋欄位
			north.add(queryCS);// 新增搜尋按鈕
			north.add(unuse1);
			north.add(firstPage);
			north.add(previousPage);
			north.add(page);
			north.add(label1);
			north.add(maxpage);
			north.add(nextPage);
			north.add(lastPage);
			newPanel.customers();// 視窗CENTRAL的panel呼叫customers資料表
		});
		queryCS.addActionListener(e -> {
			newPanel.customers();
			repaint();// 點擊query按鈕時重新呼叫customers資料表
		});
		firstPage.addActionListener(e -> {
			page.setText("1");
			newPanel.customers();
			repaint();
			System.out.println(page.getText());
		});
		previousPage.addActionListener(e -> {
			if (Integer.parseInt(page.getText()) > 1) {
				page.setText(String.valueOf(Integer.parseInt(page.getText()) - 1));
				newPanel.customers();
				repaint();
				System.out.println(page.getText());
			}
		});
		page.addActionListener(e -> {
			newPanel.removeAll();
			newPanel.customers();
			repaint();
		});
		nextPage.addActionListener(e -> {
			if (Integer.parseInt(page.getText()) < Integer.parseInt(maxpage.getText()))
				page.setText(String.valueOf(Integer.parseInt(page.getText()) + 1));
			newPanel.customers();
			repaint();
			System.out.println(page.getText());
		});
		lastPage.addActionListener(e -> {
			page.setText(maxpage.getText());
			newPanel.customers();
			repaint();
			System.out.println(page.getText());
		});
		// CS TABLE END
		// EMP TABLE
		employees.addActionListener(e -> {
			north.removeAll();
			repaint();
			north.add(LN);north.add(jtfLN);north.add(queryEMP);north.add(unuse1);north.add(firstPageEMP);north.add(previousPageEMP);north.add(pageEMP);north.add(label1);north.add(maxpage);north.add(nextPageEMP);north.add(lastPageEMP);
			newPanel.employees();
		});
		queryEMP.addActionListener(e -> {
			newPanel.employees();
			repaint();
		});
		
		firstPageEMP.addActionListener(e -> {
			pageEMP.setText("1");
			newPanel.employees();
			repaint();
		});
		previousPageEMP.addActionListener(e -> {
			if (Integer.parseInt(pageEMP.getText()) > 1) {
				pageEMP.setText(String.valueOf(Integer.parseInt(pageEMP.getText()) - 1));
				newPanel.employees();
				repaint();
			}
		});
		pageEMP.addActionListener(e -> {
			newPanel.removeAll();
			newPanel.employees();
			repaint();
		});
		nextPageEMP.addActionListener(e -> {
			if (Integer.parseInt(pageEMP.getText()) < Integer.parseInt(maxpage.getText()))
				pageEMP.setText(String.valueOf(Integer.parseInt(pageEMP.getText()) + 1));
			newPanel.employees();
			repaint();
		});
		lastPageEMP.addActionListener(e -> {
			pageEMP.setText(maxpage.getText());
			newPanel.employees();
			repaint();
		});
		// EMP TABLE END
		// OD TABLE
		orders.addActionListener(e -> {
			north.removeAll();
			repaint();
			north.add(ODID);north.add(jtfODID);north.add(DS);north.add(jtfDS);north.add(DN);north.add(jtfDN);north.add(queryOD);north.add(unuse1);north.add(firstPageOD);north.add(previousPageOD);north.add(pageOD);north.add(label1);north.add(maxpage);north.add(nextPageOD);north.add(lastPageOD);
			newPanel.orders();
		});
		queryOD.addActionListener(e -> {
			newPanel.orders();
			repaint();
		});
		
		firstPageOD.addActionListener(e -> {
			pageOD.setText("1");
			newPanel.orders();
			repaint();
		});
		previousPageOD.addActionListener(e -> {
			if (Integer.parseInt(pageOD.getText()) > 1) {
				pageOD.setText(String.valueOf(Integer.parseInt(pageOD.getText()) - 1));
				newPanel.orders();
				repaint();
			}
		});
		pageOD.addActionListener(e -> {
			newPanel.removeAll();
			newPanel.orders();
			repaint();
		});
		nextPageOD.addActionListener(e -> {
			if (Integer.parseInt(pageOD.getText()) < Integer.parseInt(maxpage.getText()))
				pageOD.setText(String.valueOf(Integer.parseInt(pageOD.getText()) + 1));
			newPanel.orders();
			repaint();
		});
		lastPageOD.addActionListener(e -> {
			pageOD.setText(maxpage.getText());
			newPanel.orders();
			repaint();
		});
		// OD TABLE END
		// ODET TABLE
		orderdetails.addActionListener(e -> {
			north.removeAll();
			repaint();
			north.add(ODID);north.add(jtfODID);north.add(queryODET);north.add(unuse1);north.add(firstPageODET);north.add(previousPageODET);north.add(pageODET);north.add(label1);north.add(maxpage);north.add(nextPageODET);north.add(lastPageODET);
			newPanel.orderDetail();
		});
		queryODET.addActionListener(e -> {
			newPanel.orderDetail();
			repaint();
		});
		firstPageODET.addActionListener(e -> {
			pageODET.setText("1");
			newPanel.orderDetail();
			repaint();
		});
		previousPageODET.addActionListener(e -> {
			if (Integer.parseInt(pageODET.getText()) > 1) {
				pageODET.setText(String.valueOf(Integer.parseInt(pageODET.getText()) - 1));
				newPanel.orderDetail();
				repaint();
			}
		});
		pageODET.addActionListener(e -> {
			newPanel.removeAll();
			newPanel.orderDetail();
			repaint();
		});
		nextPageODET.addActionListener(e -> {
			if (Integer.parseInt(pageODET.getText()) < Integer.parseInt(maxpage.getText()))
				pageODET.setText(String.valueOf(Integer.parseInt(pageODET.getText()) + 1));
			newPanel.orderDetail();
			repaint();
			System.out.println(pageODET.getText());
		});
		lastPageODET.addActionListener(e -> {
			pageODET.setText(maxpage.getText());
			newPanel.orderDetail();
			repaint();
		});
		//ODET TABLE END//
		//PD TABLE//
		products.addActionListener(e -> {
			north.removeAll();
			repaint();
			north.add(PID);north.add(jtfPID);north.add(PN);north.add(jtfPN);north.add(SPID);north.add(jtfSPID);north.add(queryPD);north.add(unuse1);north.add(firstPagePD);north.add(previousPagePD);north.add(pagePD);north.add(label1);north.add(maxpage);north.add(nextPagePD);north.add(lastPagePD);
			newPanel.products();
		});
		queryPD.addActionListener(e -> {
			newPanel.products();
			repaint();
		});
		firstPagePD.addActionListener(e -> {
			pagePD.setText("1");
			newPanel.products();
			repaint();
		});
		previousPagePD.addActionListener(e -> {
			if (Integer.parseInt(pagePD.getText()) > 1) {
				pagePD.setText(String.valueOf(Integer.parseInt(pagePD.getText()) - 1));
				newPanel.products();
				repaint();
			}
		});
		pagePD.addActionListener(e -> {
			newPanel.removeAll();
			newPanel.products();
			repaint();
		});
		nextPagePD.addActionListener(e -> {
			if (Integer.parseInt(pagePD.getText()) < Integer.parseInt(maxpage.getText()))
				pagePD.setText(String.valueOf(Integer.parseInt(pagePD.getText()) + 1));
			newPanel.products();
			repaint();
		});
		lastPagePD.addActionListener(e -> {
			pagePD.setText(maxpage.getText());
			newPanel.products();
			repaint();
		});
	}
	// PD TABLE END ------ SET LISTENER END //

	public static int getPage() {
		return Integer.parseInt(page.getText());
	}

	public static int getPageEMP() {
		return Integer.parseInt(pageEMP.getText());
	}

	public static int getPageOD() {
		return Integer.parseInt(pageOD.getText());
	}
	
	public static int getPageODET() {
		return Integer.parseInt(pageODET.getText());
	}

	public static int getPagePD() {
		return Integer.parseInt(pagePD.getText());
	}
	
	public static void main(String[] args) {
		new NorthwindBackOffice();
	}
}