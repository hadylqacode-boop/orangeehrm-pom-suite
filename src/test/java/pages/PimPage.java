package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class PimPage {
    private final WaitUtils wait;

    private final By addEmployeeBtn = By.xpath("//button[normalize-space()='Add']");

    public PimPage(WebDriver driver) {
        this.wait = new WaitUtils(driver, 20);
    }

    public void clickAddEmployee() {
        wait.click(addEmployeeBtn);
    }
}
