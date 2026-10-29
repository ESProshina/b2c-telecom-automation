package guru.qa.diploma.ui.tests;

import guru.qa.diploma.TestBase;
import guru.qa.diploma.ui.pages.InventoryPage;
import guru.qa.diploma.ui.pages.LoginPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Owner("ESProshina")
@Feature("Управление услугами (Тарифы)")
public class InventoryTests extends TestBase {

    private final LoginPage loginPage = new LoginPage();
    private final InventoryPage inventoryPage = new InventoryPage();

    @BeforeEach
    void loginAsStandardUser() {

        loginPage.openPage()
                .setUsername("standard_user")
                .setPassword("secret_sauce")
                .clickLogin();
    }

    @Test
    @DisplayName("Успешное подключение дополнительной услуги (тарифа)")
    @Severity(SeverityLevel.CRITICAL)
    void addToCartTest() {
        inventoryPage
                .addTariffToCart("Sauce Labs Backpack")
                .shouldHaveCartBadgeCount("1");
    }

    @Test
    @DisplayName("Сортировка доступных тарифных планов по стоимости")
    @Severity(SeverityLevel.NORMAL)
    void sortByPriceTest() {
        inventoryPage
                .sortByPriceLowToHigh()
                .shouldHaveFirstItemPrice("$7.99");
    }
}
