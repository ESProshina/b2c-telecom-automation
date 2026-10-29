package guru.qa.diploma.attach;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Attachments {

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public static byte[] screenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page source", type = "text/html")
    public static byte[] pageSource() {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Browser console logs", type = "text/plain")
    public static String browserConsoleLogs() {
        List<LogEntry> logs = getWebDriver().manage().logs().get(LogType.BROWSER).getAll();
        StringBuilder sb = new StringBuilder();
        for (LogEntry log : logs) {
            sb.append(log.toString()).append("\n");
        }
        return sb.toString();
    }
}
