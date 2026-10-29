package guru.qa.diploma.mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

public class ServiceSearchScreen {

    private final SelenideElement searchField1 =
            $(AppiumBy.id("org.wikipedia.alpha:id/search_text_view"));
    private final SelenideElement searchField2 =
            $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"));

    // Картинка статьи (в ЛК это может быть иконка услуги)
    private final SelenideElement serviceIcon =
            $(AppiumBy.id("org.wikipedia.alpha:id/view_page_header_image"));

    private final SelenideElement closeTooltipButton =
            $(AppiumBy.accessibilityId("Close"));

    @Step("Закрываем подсказку (если появилась)")
    public ServiceSearchScreen closeTooltipIfVisible() {
        if (closeTooltipButton.exists()) {
            closeTooltipButton.click();
        }
        return this;
    }

    @Step("Вводим название услуги для поиска: {serviceName}")
    public ServiceSearchScreen typeQuery(String serviceName) {
        searchField1.shouldBe(visible).click();
        searchField2.shouldBe(visible).setValue(serviceName);
        return this;
    }

    @Step("Кликаем по найденной услуге: {serviceName}")
    public ServiceSearchScreen clickFirstSnippet(String serviceName) {
        $(AppiumBy.xpath("//android.widget.TextView[@text='" + serviceName + "']"))
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Проверяем, что отображается иконка услуги")
    public ServiceSearchScreen checkIconVisible() {
        serviceIcon.shouldBe(visible);
        return this;
    }

    @Step("Проверяем заголовок карточки услуги: {expected}")
    public ServiceSearchScreen checkServiceTitle(String expected) {
        $(AppiumBy.xpath("//android.view.View[@text='" + expected + "']"))
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text(expected));
        return this;
    }
}