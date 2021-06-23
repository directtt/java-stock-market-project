package bank;

import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

public class StockChart {

    public void startChart(String chartName, StockMarket stockMarket) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Stock stockToChart = stockMarket.getStockName(chartName);

                JFrame frame = new JFrame("Charts");
                frame.setSize(800, 600);
                // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                XYDataset ds = createDataset(chartName, stockMarket);
                JFreeChart chart = ChartFactory.createXYLineChart(chartName + " Stock Chart",
                        "Time Interval", "Price", ds, PlotOrientation.VERTICAL, true, true,
                        false);

                float maxPrice = Collections.max(stockToChart.priceHistory);
                float minPrice = Collections.min(stockToChart.priceHistory);
                chart.getXYPlot().getRangeAxis().setRange(minPrice * 0.95, maxPrice * 1.05);

                ChartPanel cp = new ChartPanel(chart);

                frame.getContentPane().add(cp);
            }
        });

    }

    public static XYDataset createDataset(String chartName, StockMarket stockMarket) {

        Stock stockToChart = stockMarket.getStockName(chartName);
        if (stockToChart == null) {
            throw new IllegalArgumentException("Invalid stock name!");
        }
        else {
            DefaultXYDataset ds = new DefaultXYDataset();

            double[] values = new double[stockToChart.priceHistory.size()];
            for (int i = 0; i < stockToChart.priceHistory.size(); i++) {
                values[i] = stockToChart.priceHistory.get(i);
            }

            double[] xAxis = new double[values.length];
            for (int i = 0; i < values.length; i++)
            {
                xAxis[i] = i;
            }

            double[][] data = {  xAxis, values };

            ds.addSeries(chartName, data);

            return ds;
        }
    }
}