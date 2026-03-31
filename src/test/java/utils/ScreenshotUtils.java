package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtils {
    public static String capture(WebDriver driver, String fileName) {
        try {
            Path folder = Paths.get("test-output", "screenshots");
            Files.createDirectories(folder);
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path dest = folder.resolve(fileName + ".png");
            Files.copy(src.toPath(), dest);
            return dest.toString();
        } catch (IOException e) {
            return "screenshot_failed";
        }
    }
}
