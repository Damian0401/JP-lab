package frames.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;

public class TimeSeriesChart extends JFrame {
    public TimeSeriesChart(String title ,XYDataset dataset) {   // the constructor will contain the panel of a certain size and the close operations
        super("TimeSeriesChart"); // calls the super class constructor

        JPanel chartPanel = createChartPanel(title, dataset);
        add(chartPanel, BorderLayout.CENTER);
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createChartPanel(String chartTitle, XYDataset dataset) { // this method will create the chart panel containin the graph
        String xAxisLabel = "Time";
        String yAxisLabel = "Value";

        JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        customizeChart(chart);


        return new ChartPanel(chart);
    }

    private void customizeChart(JFreeChart chart) {   // here we make some customization
        XYPlot plot = chart.getXYPlot();

        // sets paint color for plot outlines
        plot.setOutlinePaint(Color.BLUE);
        plot.setOutlineStroke(new BasicStroke(2.0f));

        // sets plot background
        plot.setBackgroundPaint(Color.DARK_GRAY);

        // sets paint color for the grid lines
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

    }
}
