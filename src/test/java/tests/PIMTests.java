package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utils.JsonDataReader;

public class PIMTests extends BaseTest {

    private final JsonNode data = JsonDataReader.read("src/test/resources/testdata/employees.json");

    // 🔐 Méthode de login réutilisable
    private void loginAsAdmin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(
                data.get("validLogin").get("username").asText(),
                data.get("validLogin").get("password").asText()
        );
    }

    // ✅ Test 1 — Ajout employé (succès)
    @Test
    public void addEmployeeShouldSucceed() {
        loginAsAdmin();

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.goToPim();

        PimPage pimPage = new PimPage(driver);
        pimPage.clickAddEmployee();

        JsonNode emp = data.get("employees").get(0);

        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.addEmployee(
                emp.get("firstName").asText(),
                emp.get("middleName").asText(),
                emp.get("lastName").asText()
        );

        Assert.assertTrue(
                addEmployeePage.getSuccessToast().toLowerCase().contains("success"),
                "Le message de succès n'est pas affiché après l'ajout de l'employé."
        );
    }

    // ✅ Test 2 — Mise à jour profil
    @Test
    public void updateProfileShouldSucceed() {
        loginAsAdmin();

        MyInfoPage myInfoPage = new MyInfoPage(driver);
        myInfoPage.open();
        myInfoPage.updateFirstName(data.get("profileUpdate").get("firstName").asText());

        Assert.assertTrue(
                myInfoPage.getSuccessToast().toLowerCase().contains("success"),
                "Le profil n'a pas été mis à jour avec succès."
        );
    }

    // ✅ Test 3 — Champ obligatoire (corrigé)
    @Test
    public void addEmployeeWithoutRequiredFieldShouldShowValidation() {
        loginAsAdmin();

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.goToPim();

        PimPage pimPage = new PimPage(driver);
        pimPage.clickAddEmployee();

        AddEmployeePage addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.addEmployeeWithoutLastName("OnlyFirstName");

        // Vérifie que le message d'erreur est affiché
        Assert.assertTrue(
                addEmployeePage.isRequiredErrorDisplayed(),
                "Le message Required n'est pas affiché."
        );

        // Vérifie que le texte est correct
        Assert.assertTrue(
                addEmployeePage.getRequiredErrorText().toLowerCase().contains("required"),
                "Le texte du message d'erreur n'est pas correct."
        );
    }
}