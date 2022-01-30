package frames.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.time.LocalDate;

public class SampleTimeChart extends JFrame {
    public SampleTimeChart() {   // the constructor will contain the panel of a certain size and the close operations
        super("XY Line Chart Example with JFreechart"); // calls the super class constructor

        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createChartPanel() { // this method will create the chart panel containin the graph
        String chartTitle = "Objects Movement Chart";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";

        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        customizeChart(chart);


        return new ChartPanel(chart);
    }

    private XYDataset createDataset() {    // this method creates the data as time seris
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries series1 = new TimeSeries("Object 1");
        TimeSeries series2 = new TimeSeries("Object 2");
        TimeSeries series3 = new TimeSeries("Object 3");

        var startDate = LocalDate.now();

        var series1StartValue = 1;
        var series1EndValue = 15;

        var series1OtherValues = interpolate(series1StartValue, series1EndValue, 5);
        series1.add(localDateToDay(startDate), series1StartValue);
        for (int i = 0; i < series1OtherValues.length; i++){
            series1.add(localDateToDay(startDate.plusDays(i + 1)), series1OtherValues[i]);
        }
        series1.add(localDateToDay(startDate.plusDays(series1OtherValues.length + 1)), series1EndValue);



        var series2StartValue = 4;
        var series2EndValue = 46;

        var series2OtherValues = interpolate(series2StartValue, series2EndValue, 5);
        series2.add(localDateToDay(startDate), series2StartValue);
        for (int i = 0; i < series2OtherValues.length; i++){
            series2.add(localDateToDay(startDate.plusDays(i + 1)), series2OtherValues[i]);
        }
        series2.add(localDateToDay(startDate.plusDays(series2OtherValues.length + 1)), series2EndValue);

        var series3StartValue = 50;
        var series3EndValue = 15;

        var series3OtherValues = interpolate(series3StartValue, series3EndValue, 5);
        series3.add(localDateToDay(startDate), series3StartValue);
        for (int i = 0; i < series3OtherValues.length; i++){
            series3.add(localDateToDay(startDate.plusDays(i + 1)), series3OtherValues[i]);
        }
        series3.add(localDateToDay(startDate.plusDays(series3OtherValues.length + 1)), series3EndValue);


        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        return dataset;
    }

    private void customizeChart(JFreeChart chart) {   // here we make some customization
        XYPlot plot = chart.getXYPlot();
        DateAxis domain = (DateAxis) plot.getDomainAxis();
        domain.setDateFormatOverride(DateFormat.getDateInstance());
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();


        // sets paint color for plot outlines
        plot.setOutlinePaint(Color.BLUE);
        plot.setOutlineStroke(new BasicStroke(2.0f));

        // sets renderer for lines
        plot.setRenderer(renderer);

        // sets plot background
        plot.setBackgroundPaint(Color.DARK_GRAY);

        // sets paint color for the grid lines
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

    }

    public double[] interpolate(double start, double end, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("interpolate: illegal count!");
        }
        double[] array = new double[count + 1];
        for (int i = 0; i <= count; ++ i) {
            array[i] = start + i * (end - start) / count;
        }
        return array;
    }

    public Day localDateToDay(LocalDate localDate){
        return new Day(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
    }
}
