package main.java.IIImidProject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.jdbc.JDBCXYDataset;

public class SalesAmountChart extends JFrame {
	static SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM-dd");
    static DateAxis da = new DateAxis();
	
	
    public SalesAmountChart() {
    	initUI();
    	SwingUtilities.invokeLater(() -> {
            setVisible(true);
        });
    }

    private void initUI() {

    	JDBCXYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private static JDBCXYDataset dataset;
	private JDBCXYDataset createDataset() {
		Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
        	dataset = new JDBCXYDataset(conn);
        	dataset.executeQuery("SELECT STR_TO_DATE(CONCAT(DATE_FORMAT(A.OrderDate,\"%Y%m\"),\"01\"),\"%Y%m%d\" ) AS OrderAmount ,SUM(B.UnitPrice*B.Quantity*(1-B.Discount)) AS TOTAL FROM `orders` A INNER JOIN orderdetails B ON B.OrderID = A.OrderID WHERE A.OrderDate < \"1998-04-30\" GROUP BY 1 ORDER BY 1;");
        }catch(Exception e) {
        	System.out.println(e);
        }
        return dataset;
	}

    private JFreeChart createChart(JDBCXYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "", 
                "DATE", 
                "Sales Amount", 
                dataset,
                PlotOrientation.VERTICAL,
                false, 
                true, 
                true 
        );

        XYPlot plot = chart.getXYPlot(); //domain = X軸 range = Y軸
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        
        f3 = new SimpleDateFormat("yyyy/MM");
        da = new DateAxis();
        da.setDateFormatOverride(f3);
        plot.setDomainAxis(da); //把X軸換成datetime (預設是double)

        chart.setTitle(new TextTitle("Northwind Saler Amount Report(By Year/Month)",
                        new Font("Serif", java.awt.Font.BOLD, 20)
                )
        );

        return chart;

    }
    
    public static void main(String[] args) {

        
    }

}