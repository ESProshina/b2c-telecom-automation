package guru.qa.diploma.mobile.screens;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.appium.SelenideAppium.$;

public class OnboardingScreen {

    private final SelenideElement titleKnowledge =
            $(AppiumBy.xpath("//android.widget.TextView[@text=concat('All the world', \"'\", 's knowledge')]"));
    private final SelenideElement titleDataPrivacy =
            $(AppiumBy.xpath("//android.widget.TextView[@text='Data & Privacy']"));
    private final SelenideElement titleLanguages =
            $(AppiumBy.xpath("//android.widget.TextView[@text='Read in more than 300 languages']"));
    private final SelenideElement titleCuriosity =
            $(AppiumBy.xpath("//android.widget.TextView[@text='Follow your curiosity']"));

    private final SelenideElement forwardButton = $(AppiumBy.accessibilityId("Forward"));
    private final SelenideElement skipButton =
            $(AppiumBy.xpath("//android.widget.TextView[@text='Skip']"));

    @Step("Нажимаем кнопку 'Далее' (Forward) на приветственном экране")
    public OnboardingScreen clickForward() {
        forwardButton.shouldBe(visible).click();
        return this;
    }

    @Step("Нажимаем кнопку 'Пропустить' (Skip) и переходим на главный экран")
    public DashboardScreen clickSkip() {
        skipButton.shouldBe(visible).click();
        return new DashboardScreen();
    }

    @Step("Проверяем экран 1: Ознакомление с возможностями сервиса")
    public OnboardingScreen checkKnowledgeScreen() {
        titleKnowledge.shouldBe(visible).shouldHave(text("All the world's knowledge"));
        return this;
    }

    @Step("Проверяем экран 2: Согласие на обработку персональных данных")
    public OnboardingScreen checkDataPrivacyScreen() {
        titleDataPrivacy.shouldBe(visible).shouldHave(text("Data & Privacy"));
        return this;
    }

    @Step("Проверяем экран 3: Поддержка языков и локализация")
    public OnboardingScreen checkLanguagesScreen() {
        titleLanguages.shouldBe(visible).shouldHave(text("Read in more than 300 languages"));
        return this;
    }

    @Step("Проверяем экран 4: Персональные рекомендации и новости")
    public OnboardingScreen checkCuriosityScreen() {
        titleCuriosity.shouldBe(visible).shouldHave(text("Follow your curiosity"));
        return this;
    }


    @Step("Быстро пропускаем онбординг (кликаем Skip, если доступен, иначе Forward)")
    public DashboardScreen skipOnboarding() {
        for (int i = 0; i < 4; i++) {
            if (skipButton.exists()) {
                skipButton.click();
                break;
            }
            if (forwardButton.exists()) {
                forwardButton.click();
            }
        }
        return new DashboardScreen();
    }
}