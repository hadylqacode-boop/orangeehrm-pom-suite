package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver createDriver() {
        FirefoxOptions options = new FirefoxOptions();

        options.addArguments("--width=1440");
        options.addArguments("--height=900");

        String ci = System.getenv("CI");
        if (ci != null && ci.equalsIgnoreCase("true")) {
            options.addArguments("-headless");
        }

        String firefoxBin = System.getenv("FIREFOX_BIN");
        if (firefoxBin != null && !firefoxBin.isBlank()) {
            options.setBinary(firefoxBin);
        }

        return new FirefoxDriver(options);
    }
}