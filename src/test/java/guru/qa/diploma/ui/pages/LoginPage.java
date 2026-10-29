package guru.qa.diploma.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement usernameInput = $("#user-name");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement errorMessage = $("[data-test='error']");

    @Step("Открываем страницу логина")
    public LoginPage openPage() {
        open("/");
        return this;
    }

    @Step("Вводим логин: {username}")
    public LoginPage setUsername(String username) {
        usernameInput.setValue(username);
        return this;
    }

    @Step("Вводим пароль")
    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Нажимаем кнопку Login")
    public LoginPage clickLogin() {
        loginButton.click();
        return this;
    }

    @Step("Проверяем текст ошибки: {expectedError}")
    public LoginPage shouldHaveError(String expectedError) {
        errorMessage.shouldHave(com.codeborne.selenide.Condition.text(expectedError));
        return this;
    }
}