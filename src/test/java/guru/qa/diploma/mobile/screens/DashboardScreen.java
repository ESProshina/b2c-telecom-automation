package guru.qa.diploma.mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

public class DashboardScreen {

    // В приложении это кнопка поиска, у нас это будет поиск по базе знаний/услугам
    private final SelenideElement searchButton = $(AppiumBy.accessibilityId("Search"));

    @Step("Открываем экран поиска услуг (База знаний)")
    public ServiceSearchScreen clickSearch() {
        searchButton.shouldBe(visible).click();
        return new ServiceSearchScreen();
    }

    @Step("Проверяем, что главный экран ЛК успешно загрузился")
    public DashboardScreen checkOpened() {
        searchButton.shouldBe(visible);
        return this;
    }
}