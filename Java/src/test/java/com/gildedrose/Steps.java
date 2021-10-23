package com.gildedrose;

import com.github.leeonky.jfactory.JFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Steps {

    private GildedRose app;
    private JFactory jFactory = new JFactory();

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

    @Given("gilded rose item with name {string} and quality {int} and sell in {int}")
    public void gildedRoseItemWithNameAndQualityAndSellIn(String name, int quality, int sellIn) {
        app = gildedRoseWithItem(item(name, quality, sellIn));
    }

    @Then("item quality should be {int}")
    public void itemQualityShouldBe(int expectedQuality) {
        assertItemQualityEquals(expectedQuality);
    }

    @Given("jfactory gilded rose item")
    public void jfactoryGildedRoseItem(List<Map<String, String>> data) {
        app = gildedRoseWithItem(jFactory.type(Item.class).properties(data.get(0)).create());
    }

    private void assertItemQualityEquals(int expected) {
        assertQualityEqualsByItemIndex(expected, 0);
    }

    private void assertQualityEqualsByItemIndex(int expected, int index) {
        assertEquals(expected, app.getItems(index).getQuality());
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
