package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class MyInfoPage {

    private final WaitUtils wait;

    private final By myInfoMenu = By.xpath("//span[text()='My Info']");
    private final By firstName = By.name("firstName");
    private final By saveBtn = By.xpath("(//button[normalize-space()='Save'])[1]");
    private final By successToast = By.cssSelector(".oxd-toast-content");

    public MyInfoPage(WebDriver driver) {
        this.wait = new WaitUtils(driver, 20);
    }

    public void open() {
        wait.click(myInfoMenu);
    }

    public void updateFirstName(String value) {
        wait.visible(firstName).clear();
        wait.visible(firstName).sendKeys(value);

        // attendre disparition loader + clic JS robuste
        wait.clickWithJs(saveBtn);
    }

    public String getSuccessToast() {
        return wait.visible(successToast).getText();
    }
}