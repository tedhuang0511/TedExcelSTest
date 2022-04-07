package main.java.IIImidProject;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;

public class BestSalesChart extends JFrame {
	
	public BestSalesChart() {
		initUI();
		SwingUtilities.invokeLater(() -> {
			setVisible(true);
		});
	}

	private void initUI() {

		JDBCCategoryDataset dataset = createDataset2(); //new 一個CategoryDataset
		JFreeChart chart = createChart(dataset); //把CategoryDataset放入JFreeChart
		ChartPanel chartPanel = new ChartPanel(chart); //把JFreeChart放入ChartPanel
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		add(chartPanel); //把chartpanel新增到Jframe

		pack(); //Causes this Window to be sized to fit the preferred sizeand layouts of its subcomponents
		setTitle("Best Employees"); //JFrame title
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private static JDBCCategoryDataset dataset;
	private JDBCCategoryDataset createDataset2() {
		Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
        	dataset = new JDBCCategoryDataset(conn);
        	dataset.executeQuery("SELECT CONCAT(B.LastName,' ',B.FirstName) AS FullName,count(*) AS SalesQTY FROM `orders` A INNER JOIN employees B ON B.EmployeeID = A.EmployeeID GROUP BY 1 ORDER BY 2 DESC;");
        }catch(Exception e) {
        	System.out.println(e);
        }
        return dataset;
	}

	private JFreeChart createChart(JDBCCategoryDataset dataset) {

		JFreeChart barChart = ChartFactory.createBarChart(
				"NorthWind Most Sales QTY Employee", //圖表名稱
				"", //X軸title
				"SalesQTY", //Y軸title
				dataset,
				PlotOrientation.HORIZONTAL, //HORIZONTAL 水平的, VERTICAL 垂直的
				true, true, true); //是否顯示XY軸

		return barChart;
	}

}
