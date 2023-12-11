package graphics;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.plot.XYPlot;
// import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import image.Image;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
// import java.util.ArrayList;

public class AutoCorrelationChart {

    public static void autoCorrelationChart(String inputFilePath, String outputFilePath) {
        BufferedImage image = Image.loadAndReadImage(inputFilePath);
        assert image != null;
        double[][] autoCorrelationData = calculateAutoCorrelation(image);
        XYDataset dataset = createDataset(autoCorrelationData);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "График АКФ", "Пиксели", "АКФ", dataset, PlotOrientation.VERTICAL, true, true, false
        );

        XYPlot plot = (XYPlot) chart.getPlot();

        // Создайте XYLineAndShapeRenderer
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);

        // Установите цвет и толщину линий
        //мой цвет
        renderer.setSeriesPaint(0,  new Color(0, 102, 255, 255));
        // renderer.setSeriesPaint(0, Color.red);

        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesShape(0, new Ellipse2D.Double(-2.0, -2.0, 4.0, 4.0));
        renderer.setSeriesStroke(0, new BasicStroke(0.8f));

        plot.setRenderer(renderer);

        try {
            ChartUtils.saveChartAsPNG(new File(outputFilePath), chart, 1600, 800);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/**
 * Calculates the auto-correlation of the given image.
 *
 * @param image The input image.
 * @return The auto-correlation data.
 */
private static double[][] calculateAutoCorrelation(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    int[][] pixels = new int[width][height];

    // Extract the brightness of each pixel
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            pixels[x][y] = image.getRGB(x, y) & 0xFF;
        }
    }

    double[][] autoCorrelationData = new double[2][width * height];

    // Calculate auto-correlation for each lag
    for (int lag = 0; lag < width * height; lag++) {
        double sum = 0;
        int xLag = lag % width;
        int yLag = lag / width;

        for (int x = 0; x < width - xLag; x++) {
            for (int y = 0; y < height - yLag; y++) {
                sum += pixels[x][y] * pixels[x + xLag][y + yLag];
            }
        }

        autoCorrelationData[0][lag] = lag;
        autoCorrelationData[1][lag] = (sum / ((width - xLag) * (height - yLag))) - (double) (width * height) / 16;
    }

    return autoCorrelationData;
}


    private static XYDataset createDataset(double[][] data) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("АКФ", data);
        return dataset;
    }
}
