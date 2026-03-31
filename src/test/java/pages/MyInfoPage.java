package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

public class MyInfoPage {

    private final WebDriver driver;
    private final WaitUtils wait;

    private final By myInfoMenu = By.xpath("//span[text()='My Info']");
    private final By firstName = By.name("firstName");
    private final By saveBtn = By.xpath("(//button[normalize-space()='Save'])[1]");

    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 20);
    }

    public void open() {
        wait.click(myInfoMenu);
        wait.waitForLoaderToDisappear();
    }

    public void updateFirstName(String value) {
        wait.waitForLoaderToDisappear();

        WebElement field = wait.visible(firstName);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", field);
        wait.waitForLoaderToDisappear();

        // vide complètement le champ
        js.executeScript("arguments[0].value='';", field);

        // injecte la nouvelle valeur
        js.executeScript("arguments[0].value=arguments[1];", field, value);

        // déclenche les événements pour que l’application prenne en compte la modification
        js.executeScript(
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                field
        );

        wait.waitForLoaderToDisappear();
        wait.clickWithJs(saveBtn);
    }

    public String getFirstNameValue() {
        wait.waitForLoaderToDisappear();
        return wait.visible(firstName).getAttribute("value");
    }
}