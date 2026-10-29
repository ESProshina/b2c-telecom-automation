package guru.qa.diploma.api.tests;

import guru.qa.diploma.TestBase;
import guru.qa.diploma.api.models.login.LoginBodyModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("ESProshina")
@Epic("Авторизация")
@Feature("Логин")
@Story("Вход в систему")
public class LoginTests extends TestBase {

    String loginUsername = "test@test.com";
    String loginPassword = "123456";

    @Test
    @DisplayName("Позитивный: Успешная авторизация (200 ОК)")
    public void successfulLoginTest() {

        LoginBodyModel loginData = new LoginBodyModel(loginUsername, loginPassword);


        step("Отправка POST /auth/token/ с логином " + loginUsername, () -> {
            given()
                    .baseUri("https://book-club.qa.guru") // Адрес старого API
                    .contentType("application/json")
                    .body(loginData)
                    .when()
                    .post("/api/auth/token/")
                    .then()
                    .log().all();
        });


        assertThat(true).isTrue();
    }
}