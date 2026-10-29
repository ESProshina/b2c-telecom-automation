package guru.qa.diploma;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.diploma.attach.Attachments; // <--- Исправлен импорт
import guru.qa.diploma.config.WebDriverConfig; // <--- Исправлен импорт
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestBase {

    protected static final WebDriverConfig config =
            ConfigFactory.create(WebDriverConfig.class, System.getProperties());

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true));

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browserVersion = config.getBrowserVersion();

        if (config.getRemoteUrl() != null && !config.getRemoteUrl().isEmpty()) {
            Configuration.remote = config.getRemoteUrl();
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Attachments.screenshot();
            Attachments.pageSource();
            Attachments.browserConsoleLogs();
        } catch (Exception e) {
            System.out.println("Не удалось собрать аттачменты: " + e.getMessage());
        } finally {
            closeWebDriver();
        }
    }
}