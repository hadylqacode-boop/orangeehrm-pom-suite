package base;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DriverFactory;
import utils.ExtentManager;
import utils.ScreenshotUtils;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentTest test;

    @BeforeClass
    public void beforeClass() {
        ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp(java.lang.reflect.Method method) {
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
        test = ExtentManager.getInstance().createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = ScreenshotUtils.capture(driver, result.getName());
            test.log(Status.FAIL, result.getThrowable());
            try {
                test.addScreenCaptureFromPath(path);
            } catch (Exception ignored) {
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test passed");
        } else {
            test.log(Status.SKIP, "Test skipped");
        }

        if (driver != null) {
            driver.quit();
        }
        ExtentManager.getInstance().flush();
    }
}
