package graphics;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import image.Image;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CheckerboardTest {
    public static void checkerboardTest(String inputFilePath, String outputFilePath) {
        BufferedImage image = Image.loadAndReadImage(inputFilePath);

        assert image != null;
        double[][] autoCorrelationData = calculateAutoCorrelation(image);
        XYDataset dataset = createDataset(autoCorrelationData);

        JFreeChart chart = ChartFactory.createScatterPlot(
                "тест на решетчатость", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false
        );

        // Получите XYPlot
        XYPlot plot = (XYPlot) chart.getPlot();

        // Создайте XYDotRenderer
        XYDotRenderer renderer = new XYDotRenderer();

        // Установите размер маркера
        renderer.setDotHeight(3); // Установите желаемую высоту маркера
        renderer.setDotWidth(3);  // Установите желаемую ширину маркера
        //мой цвет
        renderer.setSeriesPaint(0, Color.black);

        // renderer.setSeriesPaint(0, Color.gray);

        // Отключите сетку по осям X и Y
        plot.setDomainGridlinesVisible(false);
        plot.setRangeGridlinesVisible(false);

        plot.setRenderer(renderer);

        try {
            ChartUtils.saveChartAsPNG(new File(outputFilePath), chart, 900, 900);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double[][] calculateAutoCorrelation(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // Создайте одномерный массив для хранения пикселей
        int[] pixelArray = new int[width * height];

        // Используйте вложенные циклы для перебора всех пикселей изображения
        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y) & 0xFF;
                pixelArray[index] = pixel;
                index++;
            }
        }

        double[][] autoCorrelationData = new double[2][width * height];

        for (int i = 0; i < autoCorrelationData[0].length - 1; i++) {
            autoCorrelationData[0][i] = pixelArray[i];
            autoCorrelationData[1][i] = pixelArray[i + 1];

        }
        return autoCorrelationData;
    }

    private static XYDataset createDataset(double[][] data) {
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("точка/пиксель", data);
        return dataset;
    }
}