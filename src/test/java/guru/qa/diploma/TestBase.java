package guru.qa.diploma;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.diploma.attach.Attachments;
import guru.qa.diploma.config.WebDriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.closeWebDriver;

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


        Configuration.timeout = 15000;


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        options.addArguments("--window-size=" + config.getBrowserSize().replace("x", ","));
        Configuration.browserCapabilities = options;

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