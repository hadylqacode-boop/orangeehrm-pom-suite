package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class LoginPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginButton = By.xpath("//button[normalize-space()='Login']");
    private final By errorMessage = By.cssSelector(".oxd-alert-content-text");
    private final By loader = By.cssSelector(".oxd-form-loader");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }

    public void open() {
        driver.get(url);
        wait.waitForLoaderToDisappear();
        wait.visible(username);
    }

    public void login(String user, String pass) {
        wait.waitForLoaderToDisappear();

        wait.visible(username).clear();
        wait.visible(username).sendKeys(user);

        wait.visible(password).clear();
        wait.visible(password).sendKeys(pass);

        wait.clickWithJs(loginButton);
    }

    public String getErrorMessage() {
        wait.waitForLoaderToDisappear();
        return wait.visible(errorMessage).getText();
    }
}
