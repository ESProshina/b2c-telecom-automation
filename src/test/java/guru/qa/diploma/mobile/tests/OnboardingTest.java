package guru.qa.diploma.mobile.tests;

import guru.qa.diploma.mobile.screens.OnboardingScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@DisplayName("Мобильное приложение абонента B2C")
public class OnboardingTest extends MobileTestBase {

    @Test
    @DisplayName("Прохождение приветственных экранов при первом запуске ЛК")
    public void onboardingTest() {
        step("Пройти онбординг и проверить открытие главного экрана (Dashboard)", () -> {
            new OnboardingScreen().passOnboarding().checkOpened();
        });
    }
}