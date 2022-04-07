package main.java.ExcelUtil;

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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.jdbc.JDBCPieDataset;
import org.jfree.data.jdbc.JDBCXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChartEx extends JFrame {
	static SimpleDateFormat f3 = new SimpleDateFormat("yyyy-MM-dd");
    static DateAxis da = new DateAxis();
	
	
    public LineChartEx() {
    	initUI();
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static JDBCXYDataset dataset;
	private JDBCXYDataset createDataset() {
		Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "");
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/northwind", prop)){
        	dataset = new JDBCXYDataset(conn);
        	dataset.executeQuery("SELECT A.OrderDate ,SUM(B.UnitPrice*B.Quantity*(1-B.Discount)) AS TOTAL FROM `orders` A INNER JOIN orderdetails B ON B.OrderID = A.OrderID GROUP BY 1 ORDER BY 1;");
        }catch(Exception e) {
        	System.out.println(e);
        }
        return dataset;
	}

    private JFreeChart createChart(JDBCXYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "123", 
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
        
        f3 = new SimpleDateFormat("yyyy/MM	");
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

        SwingUtilities.invokeLater(() -> {
            LineChartEx ex = new LineChartEx();
            ex.setVisible(true);
        });
    }

}