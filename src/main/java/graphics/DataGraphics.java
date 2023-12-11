package graphics;

public class DataGraphics {
    public static void initialDataGraphics(String inputFilePath){
        // AutoCorrelationChart.autoCorrelationChart(inputFilePath, "graphics/initial/autoCorrelationChart.png");
        AutoCorrelation.autoCorrelation(inputFilePath, "graphics/initial/autoCorrelationChart.png");

        BrightnessHistogram.brightnessHistogram(inputFilePath, "graphics/initial/brightnessHistogram.png");
        CheckerboardTest.checkerboardTest(inputFilePath, "graphics/initial/CheckerboardTest.png");
    }
    public static void encryptDataGraphics(String inputFilePath){
        // AutoCorrelationChart.autoCorrelationChart(inputFilePath, "graphics/encrypt/autoCorrelationChart.png");
        AutoCorrelation.autoCorrelation(inputFilePath, "graphics/encrypt/autoCorrelationChart.png");

        BrightnessHistogram.brightnessHistogram(inputFilePath, "graphics/encrypt/brightnessHistogram.png");
        CheckerboardTest.checkerboardTest(inputFilePath, "graphics/encrypt/CheckerboardTest.png");
    }
    public static void decryptDataGraphics(String inputFilePath){
        // AutoCorrelationChart.autoCorrelationChart(inputFilePath, "graphics/decrypt/autoCorrelationChart.png");
        AutoCorrelation.autoCorrelation(inputFilePath, "graphics/decrypt/autoCorrelationChart.png");
        BrightnessHistogram.brightnessHistogram(inputFilePath, "graphics/decrypt/brightnessHistogram.png");
        CheckerboardTest.checkerboardTest(inputFilePath, "graphics/decrypt/CheckerboardTest.png");
    }
}
