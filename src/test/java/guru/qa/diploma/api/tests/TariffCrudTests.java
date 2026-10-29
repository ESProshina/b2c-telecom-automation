package guru.qa.diploma.api.tests;

import guru.qa.diploma.TestBase;
import guru.qa.diploma.api.models.tariffs.CreateTariffBodyModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static guru.qa.diploma.api.specs.BaseSpec.baseRequestSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Owner("ESProshina")
@Epic("Тарифы")
@Feature("Управление тарифами")
@Story("CRUD операции с тарифами")
public class TariffCrudTests extends TestBase {

    @Test
    @DisplayName("Позитивный: Создание тарифа (200 OK)")
    public void createTariffTest() {
        CreateTariffBodyModel body = new CreateTariffBodyModel(
                "Тариф BEE CONTACT LITE", 500, "Базовый тариф для звонков");


        Response response = given()
                .spec(baseRequestSpec)
                .body(body)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .extract()
                .response();


        step("Проверка: tariffName", () -> assertThat(response.jsonPath().getString("json.tariffName")).isEqualTo("Тариф BEE CONTACT LITE"));
        step("Проверка: price", () -> assertThat(response.jsonPath().getInt("json.price")).isEqualTo(500));
        step("Проверка: description", () -> assertThat(response.jsonPath().getString("json.description")).isEqualTo("Базовый тариф для звонков"));
    }
}