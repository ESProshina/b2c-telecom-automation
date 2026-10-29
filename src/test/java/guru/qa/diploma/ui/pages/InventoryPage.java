package guru.qa.diploma.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage {

    private final SelenideElement cartBadge = $(".shopping_cart_badge");
    private final SelenideElement sortDropdown = $(".product_sort_container");
    private final SelenideElement firstItemPrice = $$(".inventory_item_price").first();

    @Step("Подключаем услугу (тариф) '{itemName}' в личном кабинете")
    public InventoryPage addTariffToCart(String itemName) {

        SelenideElement button = $$(".inventory_item")
                .findBy(text(itemName))
                .find("button");

        button.shouldBe(visible);


        Selenide.executeJavaScript("arguments[0].click();", button);

        return this;
    }

    @Step("Проверяем, что в корзине подключено услуг: {count}")
    public InventoryPage shouldHaveCartBadgeCount(String count) {
        cartBadge.shouldBe(visible).shouldHave(text(count));
        return this;
    }

    @Step("Сортируем доступные тарифы по стоимости (от дешевых к дорогим)")
    public InventoryPage sortByPriceLowToHigh() {
        sortDropdown.selectOption("Price (low to high)");
        return this;
    }

    @Step("Проверяем, что самый дешевый тариф стоит {price}")
    public InventoryPage shouldHaveFirstItemPrice(String price) {
        firstItemPrice.shouldHave(text(price));
        return this;
    }
}