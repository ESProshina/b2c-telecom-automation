package guru.qa.diploma.ui.tests;

import guru.qa.diploma.TestBase;
import guru.qa.diploma.ui.pages.LoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Owner("ESProshina")
@Feature("Авторизация")
public class LoginTests extends TestBase {

    private final LoginPage loginPage = new LoginPage();

    @ParameterizedTest(name = "Логин под пользователем: {0}")
    @CsvSource({
            "standard_user, secret_sauce, ",
            "locked_out_user, secret_sauce, Epic sadface: Sorry, this user has been locked out.",
            "problem_user, secret_sauce, ",
            "performance_glitch_user, secret_sauce, ",
            "error_user, secret_sauce, ",
            "visual_user, secret_sauce, "
    })
    @Story("Вход в систему")
    @Severity(SeverityLevel.CRITICAL)
    void loginTest(String username, String password, String expectedError) {
        loginPage.openPage()
                .setUsername(username)
                .setPassword(password)
                .clickLogin();

        if (expectedError != null && !expectedError.isEmpty()) {
            loginPage.shouldHaveError(expectedError);
        }
    }
}