package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class DashboardPage {
    private final WaitUtils wait;

    private final By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private final By userDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private final By logoutLink = By.xpath("//a[text()='Logout']");
    private final By pimMenu = By.xpath("//span[text()='PIM']");

    public DashboardPage(WebDriver driver) {
        this.wait = new WaitUtils(driver, 20);
    }

    public boolean isLoaded() {
        return wait.visible(dashboardHeader).isDisplayed();
    }

    public void goToPim() {
        wait.click(pimMenu);
    }

    public void logout() {
        wait.click(userDropdown);
        wait.click(logoutLink);
    }
}