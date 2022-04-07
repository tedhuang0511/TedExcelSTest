package main.java.ExcelUtil;

import java.awt.Color;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.text.NumberFormat;  
import javax.swing.Timer;  
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;  
import org.jfree.chart.plot.PiePlot;  
import org.jfree.data.general.DefaultPieDataset;  
import org.jfree.data.general.PieDataset;  
import org.jfree.ui.ApplicationFrame;  
import org.jfree.ui.RefineryUtilities;  
  
/** 
* Source : http://www.koders.com/java/fidCA9ADCF38E1E1B2825657905E9A4FC95579E0E6B.aspx 
* @author John-Lee 
*/  
public class Test3 extends org.jfree.chart.ui.ApplicationFrame{  
  
    /** 
     * Default constructor. 
     * 
     * @param title  the frame title. 
     */  
    public Test3(String title) {  
  
        super(title);  
        PieDataset dataset = createDataset(14);  
  
        // create the chart...  
        JFreeChart chart = ChartFactory.createPieChart(  
            "Pie Chart Demo 4",  // chart title  
            dataset,             // dataset  
            false,                // include legend  
            true,  
            false  
        );  
  
        // set the background color for the chart...  
        chart.setBackgroundPaint(new Color(222, 222, 255));  
        PiePlot plot = (PiePlot) chart.getPlot();  
        plot.setBackgroundPaint(Color.white);  
        plot.setCircular(true);  
        // For the label format, use {0} where the pie section key should be inserted, {1}   
        // for the absolute section value and {2} for the percent amount of the pie section,   
        // e.g. "{0} = {1} ({2})" will display as apple = 120 (5%).  
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {2}"));  
        plot.setNoDataMessage("No data available");  
  
        // add the chart to a panel...  
        ChartPanel chartPanel = new ChartPanel(chart);  
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));  
        setContentPane(chartPanel);  
          
        Rotator rotator = new Rotator(plot);  
        rotator.start();  
  
    }  
  
    /** 
     * Creates a sample dataset. 
     *  
     * @param sections  the number of sections. 
     *  
     * @return A sample dataset. 
     */  
    private PieDataset createDataset(int sections) {  
        DefaultPieDataset result = new DefaultPieDataset();  
        for (int i = 0; i < sections; i++) {  
            double value = 100.0 * Math.random();  
            result.setValue("Section " + i, value);  
        }  
        return result;  
    }  
      
    /** 
     * Starting point for the demonstration application. 
     * 
     * @param args  ignored. 
     */  
    public static void main(String[] args) {          
        Test3 demo = new Test3("Pie Chart Demo 4");  
        demo.pack();  
        RefineryUtilities.centerFrameOnScreen(demo);  
        demo.setVisible(true);  
  
    }  
  
    /** 
     * The rotator. 
     * 
     * @author David Gilbert 
     */  
    public static class Rotator extends Timer implements ActionListener {  
  
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