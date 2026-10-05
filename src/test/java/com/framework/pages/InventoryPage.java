package com.framework.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage{
	private final By pageTitle = By.className("title");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartIcon = By.className("shopping_cart_link");
    private final By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private final By inventoryItems = By.className("inventory_item");
    private final By itemNames = By.className("inventory_item_name");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(pageTitle);
    }

    public void addItemToCartByName(String itemName) {
        List<WebElement> items = driver.findElements(inventoryItems);
        for (WebElement item : items) {
            if (item.findElement(itemNames).getText().equalsIgnoreCase(itemName)) {
                item.findElement(By.tagName("button")).click();
                return;
            }
        }
        throw new RuntimeException("Item not found on inventory page: " + itemName);
    }

    public int getCartCount() {
        if (!isDisplayed(cartBadge)) return 0;
        return Integer.parseInt(getText(cartBadge));
    }

    public void sortBy(String visibleText) {
        Select select = new Select(waitForVisible(sortDropdown));
        select.selectByVisibleText(visibleText);
    }

    public void goToCart() {
        click(cartIcon);
    }
}
