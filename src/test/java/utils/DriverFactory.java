package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver createDriver() {
        FirefoxOptions options = new FirefoxOptions();

        // Toujours garder une taille fixe
        options.addArguments("--width=1440");
        options.addArguments("--height=900");

        // En CI, Firefox doit tourner en headless
        String ci = System.getenv("CI");
        if (ci != null && ci.equalsIgnoreCase("true")) {
            options.addArguments("-headless");
        }

        return new FirefoxDriver(options);
    }
}