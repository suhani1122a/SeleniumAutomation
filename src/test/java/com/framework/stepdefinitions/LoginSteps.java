package com.framework.stepdefinitions;

import org.testng.Assert;

import com.framework.pages.InventoryPage;
import com.framework.pages.LoginPage;
import com.framework.util.DriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        loginPage = new LoginPage(DriverFactory.getDriver());
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page() {
        inventoryPage = new InventoryPage(DriverFactory.getDriver());
        Assert.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("inventory.html"));
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        Assert.assertEquals(inventoryPage.getTitle(), expectedTitle);
    }

    @Then("I should see an error message containing {string}")
    public void i_should_see_an_error_message_containing(String expectedText) {
        Assert.assertTrue(loginPage.getErrorText().toLowerCase().contains(expectedText.toLowerCase()));
    }
}
