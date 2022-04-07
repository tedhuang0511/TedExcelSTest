package main.java.IIImidProject;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.data.jdbc.JDBCPieDataset;

public class MostSalesProductPieChart extends JFrame {
	
	public MostSalesProductPieChart() {
		initUI();
		SwingUtilities.invokeLater(() -> {
			setVisible(true);
		});
	}

	private void initUI() {

		JDBCPieDataset dataset = createDataset2(); //new 一個CategoryDataset
		JFreeChart chart = createChart(dataset); //把CategoryDataset放入JFreeChart
		PiePlot plot = (PiePlot) chart.getPlot();  //設置pieChar的元素
		plot.setBackgroundPaint(Color.white);  
        plot.setCircular(true); 
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={2},QTY:{1}"));  //設定標籤0=productname 2=百分比 1=qty
        plot.setNoDataMessage("No data available");  
		ChartPanel chartPanel = new ChartPanel(chart); //把JFreeChart放入ChartPanel
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		add(chartPanel); //把chartpanel新增到Jframe
		Rotator rotator = new Rotator(plot);  
        rotator.start(); 

		pack(); //Causes this Window to be sized to fit the preferred sizeand layouts of its subcomponents
		setTitle("Best Sales Products"); //JFrame title
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private static JDBCPieDataset dataset;
	private JDBCPieDataset createDataset2() {
		Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
        	dataset = new JDBCPieDataset(conn);
        	dataset.executeQuery("SELECT B.ProductName, SUM(A.Quantity) AS SOLD FROM `orderdetails` A INNER JOIN products B ON B.ProductID = A.PRODUCTID GROUP BY 1 ORDER BY 2 DESC LIMIT 0,10;");
        }catch(Exception e) {
        	System.out.println(e);
        }
        return dataset;
	}

	private JFreeChart createChart(JDBCPieDataset dataset) {

		JFreeChart pieChart = ChartFactory.createPieChart(
				"NorthWind Most Sales QTY Products",//圖表名稱
				dataset,
				true, true, true); //是否顯示XY軸
		return pieChart;
	}
	
	static class Rotator extends Timer implements ActionListener {  
		  
        /** The plot. */  
        private PiePlot plot;  
  
        /** The angle. */  
        private int angle = 270;  
  
        /** 
         * Constructor. 
         * 
         * @param plot  the plot. 
         */  
        Rotator(PiePlot plot) {  
            super(100, null);  
            this.plot = plot;  
            addActionListener(this); // every 100ms, trigger self.  
        }  
  
        /** 
         * Modifies the starting angle. 
         * Ref: 
         *  - javax.swing.Timer : http://download.oracle.com/javase/6/docs/api/javax/swing/Timer.html 
         *  - javax.awt.event.ActionListener : http://download.oracle.com/javase/6/docs/api/java/awt/event/ActionListener.html   
         * @param event  the action event. 
         */  
        public void actionPerformed(ActionEvent event) {  
            this.plot.setStartAngle(angle);  
            this.angle = this.angle + 1;  
            if (this.angle == 360) {  
                this.angle = 0;  
            }  
        }  
    } 

}
