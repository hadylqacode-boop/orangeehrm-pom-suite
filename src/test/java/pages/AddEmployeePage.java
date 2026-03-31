package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class AddEmployeePage {

    private final WaitUtils wait;

    private final By firstName = By.name("firstName");
    private final By middleName = By.name("middleName");
    private final By lastName = By.name("lastName");
    private final By saveBtn = By.xpath("//button[normalize-space()='Save']");
    private final By successToast = By.cssSelector(".oxd-toast-content");

    // ✅ locator plus robuste pour le message "Required"
    private final By requiredError = By.cssSelector(".oxd-input-field-error-message");

    public AddEmployeePage(WebDriver driver) {
        this.wait = new WaitUtils(driver, 20);
    }

    public void addEmployee(String fn, String mn, String ln) {
        wait.visible(firstName).sendKeys(fn);
        wait.visible(middleName).sendKeys(mn);
        wait.visible(lastName).sendKeys(ln);
        wait.clickWithJs(saveBtn);
    }

    public void addEmployeeWithoutLastName(String fn) {
        wait.visible(firstName).sendKeys(fn);
        wait.clickWithJs(saveBtn);
    }

    public String getSuccessToast() {
        return wait.visible(successToast).getText();
    }

    public boolean isRequiredErrorDisplayed() {
        return wait.visible(requiredError).isDisplayed();
    }

    public String getRequiredErrorText() {
        return wait.visible(requiredError).getText();
    }
}
