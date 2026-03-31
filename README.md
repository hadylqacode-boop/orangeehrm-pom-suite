# OrangeHRM Selenium POM Suite

## Objectif
Suite de tests Selenium robuste et maintenable pour OrangeHRM Demo avec Page Object Model.

## URL et identifiants
- URL : https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
- Username : Admin
- Password : admin123

## Couverture
- Connexion réussie
- Connexion échouée
- Déconnexion
- Ajout d'employé
- Mise à jour profil
- Validation champ obligatoire manquant

## Stack
- Java 17
- Maven
- Selenium
- TestNG
- WebDriverManager
- ExtentReports
- GitHub Actions

## Exécution locale
```bash
mvn clean test