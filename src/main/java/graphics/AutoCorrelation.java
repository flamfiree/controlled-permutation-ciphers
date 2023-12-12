package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import image.Image;

public class AutoCorrelation {

    public static void autoCorrelation(String inputFilePath, String outputFilePath) {
        // Загрузите изображение
        BufferedImage image = Image.loadAndReadImage(inputFilePath);

        // Преобразуйте изображение в массив пикселей
        int[] pixels = Image.image8bitToPixels(image);

        // Вычислите АКФ изображения
        double[] autocorr = autocorrelation(pixels);

        // Создайте объект XYSeriesCollection
        XYSeriesCollection dataset = new XYSeriesCollection();

        // Добавьте в коллекцию серию данных АКФ
        XYSeries series = new XYSeries("АКФ");
        for (int i = 0; i < autocorr.length; i++) {
            series.add(i, autocorr[i]);
        }
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "График АКФ", "Пиксели", "АКФ", dataset, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();

        // Создайте XYLineAndShapeRenderer
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);

        // Установите цвет и толщину линий
        // мой цвет
        renderer.setSeriesPaint(0, new Color(0, 102, 255, 255));
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

    private static double[] autocorrelation(int[] pixels) {
        int count = 20000;
        int[] copiedPixels = new int[count];
        System.arraycopy(pixels, 0, copiedPixels, 0, count);
        double avg = 0;
        for (double value : copiedPixels) {
            avg += value;
        }
        avg /= count;

        for (int i = 0; i < count; i++) {
            copiedPixels[i] -= avg;
        }

        double[] autocorr = new double[count];

        for (int delay = 0; delay < count; delay++) {
        double correlation = 0.0;
        for (int i = delay; i < count; i++) {
        correlation += (copiedPixels[i - delay] * copiedPixels[i]);
        }
        correlation /= count;
        autocorr[delay] = correlation;
        }

        return autocorr;
    }
}
