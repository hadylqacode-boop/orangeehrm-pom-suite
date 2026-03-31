package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.JsonDataReader;

public class LoginTests extends BaseTest {

    private final JsonNode data = JsonDataReader.read("src/test/resources/testdata/employees.json");

    @Test
    public void successfulLoginShouldOpenDashboard() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(
                data.get("validLogin").get("username").asText(),
                data.get("validLogin").get("password").asText()
        );

        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isLoaded(), "Le dashboard n'est pas affiché.");
    }

    @Test
    public void invalidPasswordShouldShowError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(
                data.get("invalidLogin").get("username").asText(),
                data.get("invalidLogin").get("password").asText()
        );

        Assert.assertTrue(
                loginPage.getErrorMessage().toLowerCase().contains("invalid"),
                "Le message d'erreur attendu n'est pas affiché."
        );
    }

    @Test
    public void logoutShouldReturnToLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(
                data.get("validLogin").get("username").asText(),
                data.get("validLogin").get("password").asText()
        );

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.logout();

        Assert.assertTrue(driver.getCurrentUrl().contains("/auth/login"), "Retour à la page login non effectué.");
    }
}
