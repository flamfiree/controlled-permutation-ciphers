package graphics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
// import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.statistics.HistogramDataset;

import javax.imageio.ImageIO;
import java.awt.*;
// import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BrightnessHistogram {

    public static void brightnessHistogram(String inputFilePath, String outputFilePath) {
        HistogramDataset dataset = createDataset(inputFilePath);
        JFreeChart chart = ChartFactory.createHistogram(
                "Распределение яркости", "Яркость от 0 до 255", "Частота встречаемости", dataset, PlotOrientation.VERTICAL, true, true, false
        );

        XYPlot plot = (XYPlot) chart.getPlot();

        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        //мой цвет
        renderer.setSeriesPaint(0, new Color(139, 69, 19));
        // renderer.setSeriesPaint(0, Color.red);

        plot.setRenderer(renderer);

        try {
            ChartUtils.saveChartAsPNG(new File(outputFilePath), chart, 800, 600);
            System.out.println("Гистограмма успешно сохранена в " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static HistogramDataset createDataset(String inputFilePath) {
        HistogramDataset dataset = new HistogramDataset();

        try {
            BufferedImage image = ImageIO.read(new File(inputFilePath));

            int width = image.getWidth();
            int height = image.getHeight();

            int[] pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);

            double[] values = new double[pixels.length];

            for (int i = 0; i < pixels.length; i++) {
                int pixelValue = (pixels[i] >> 16) & 0xFF; // Извлекаем яркость из RGB значения
                values[i] = pixelValue;
            }

            dataset.addSeries("Яркость", values, 10,0,255); // 256 - количество бинов для яркости (0-255)
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataset;
    }

}
