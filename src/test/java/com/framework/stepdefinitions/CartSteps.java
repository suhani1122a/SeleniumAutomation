package com.framework.stepdefinitions;

import org.testng.Assert;

import com.framework.pages.InventoryPage;
import com.framework.util.DriverFactory;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartSteps {
	private InventoryPage inventoryPage;

    private InventoryPage inventoryPage() {
        if (inventoryPage == null) {
            inventoryPage = new InventoryPage(DriverFactory.getDriver());
        }
        return inventoryPage;
    }

    @When("I add {string} to the cart")
    public void i_add_item_to_the_cart(String itemName) {
        inventoryPage().addItemToCartByName(itemName);
    }

    @Then("the cart badge should show {string}")
    public void the_cart_badge_should_show(String expectedCount) {
        Assert.assertEquals(inventoryPage().getCartCount(), Integer.parseInt(expectedCount));
    }
}
