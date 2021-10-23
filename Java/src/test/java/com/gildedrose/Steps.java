package com.gildedrose;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Steps {

    private GildedRose app;

    @Given("gilded rose item with name {string} and sell in {int}")
    public void gilded_rose_item_with_name_and_sell_in(String name, int sellIn) {
        app = gildedRoseWithItem(item(name, 20, sellIn));
    }

    @When("update quality")
    public void update_quality() {
        app.updateQuality();
    }

    @Then("item sell in should be {int}")
    public void item_sell_in_should_be(int expectedSellIn) {
        assertSellInEquals(expectedSellIn);
    }

    private void assertSellInEquals(int expected) {
        assertEquals(expected, app.getItems(0).getSellIn());
    }

    private GildedRose gildedRoseWithItem(Item... items) {
        return new GildedRose(items);
    }

    private Item item(String name, int quality, int sellIn) {
        return new Item(name, sellIn, quality);
    }

}
