package com.gildedrose;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class GildedRoseTest {

    private GildedRose app;

    public static Stream<Arguments> dataForSellInTests() {
        return Stream.of(
                arguments("sell in days reduce one per day when not expired", "normal", 1, 0),
                arguments("sell in days reduce one per day when just expired", "normal", 0, -1),
                arguments("sell in days reduce one per day when already expired", "normal", -1, -2),
                arguments("sulfuras sell in days never reduce when not expired", "Sulfuras, Hand of Ragnaros", 1, 1),
                arguments("sulfuras sell in days never reduce when just expired", "Sulfuras, Hand of Ragnaros", 0, 0),
                arguments("sulfuras sell in days never reduce when already expired", "Sulfuras, Hand of Ragnaros", -1, -1),
                arguments("sulfuras sell in days never reduce when not expired", "Backstage passes to a TAFKAL80ETC concert", 1, 0), // added to kill a mutator
                arguments("sulfuras sell in days never reduce when just expired", "Backstage passes to a TAFKAL80ETC concert", 0, -1), // added to kill a mutator
                arguments("sulfuras sell in days never reduce when already expired", "Backstage passes to a TAFKAL80ETC concert", -1, -2) // added to kill a mutator
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("dataForSellInTests")
    public void sell_in_days(String testName, String itemName, int currentSellIn, int expectedUpdatedSellIn) {
        app = gildedRoseWithItem(anyQualityItemWithSellIn(itemName, currentSellIn));

        app.updateQuality();

        assertSellInEquals(expectedUpdatedSellIn);
    }

    @Test
    public void should_handle_more_than_one_item() {
        app = gildedRoseWithItem(item("normal", 40, 5), item("Aged Brie", 10, 8));

        app.updateQuality();

        assertQualityEqualsByItemIndex(39, 0);
        assertQualityEqualsByItemIndex(11, 1);
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class Quality {

        public Stream<Arguments> dataForNormalTests() {
            return Stream.of(
                    arguments("quality_reduce_one_per_day_when_not_expired", 50, 1, 49),
                    arguments("quality_reduce_one_per_day_when_just_expired", 50, 0, 48),
                    arguments("quality_reduce_one_per_day_when_already_expired", 50, -1, 48),
                    arguments("quality_reduce_two_per_day_when_expired", 3, 0, 1),
                    arguments("quality_can_not_reduce_beyond_0_when_expired", 1, 0, 0),
                    arguments("quality_should_keep_as_0_even_when_not_expired", 0, 1, 0)
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("dataForNormalTests")
        public void normal_item(String testName, int currentQuality, int sellInDays, int expectedUpdateQuality) {
            app = gildedRoseWithItem(item("normal", currentQuality, sellInDays));

            app.updateQuality();

            assertItemQualityEquals(expectedUpdateQuality);
        }

        public Stream<Arguments> dataForAgedBrieTests() {
            return Stream.of(
                    arguments("quality increase one per day when not expired", 0, 1, 1),
                    arguments("quality increase two per day when just expired", 0, 0, 2),
                    arguments("quality increase two per day when already expired", 0, -1, 2),
                    arguments("quality increase two per day when already expired", 49, -1, 50), // added to kill a mutator
                    arguments("quality increase two per day when already expired", 48, -1, 50), // added to kill a mutator
                    arguments("quality can't exceed 50 when not expired", 50, 1, 50),
                    arguments("quality can't exceed 50 when expired", 49, 1, 50)
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("dataForAgedBrieTests")
        public void aged_brie_item(String testName, int currentQuality, int sellInDays, int expectedUpdateQuality) {
            app = gildedRoseWithItem(item("Aged Brie", currentQuality, sellInDays));

            app.updateQuality();

            assertItemQualityEquals(expectedUpdateQuality);
        }

        public Stream<Arguments> dataForSulfurasTests() {
            return Stream.of(
                    arguments("quality is always 80 and never changes when not expired", 80, 1, 80),
                    arguments("quality is always 80 and never changes when just expired", 80, 0, 80), // added to kill a mutator
                    arguments("quality is always 80 and never changes when already expired", 80, -1, 80) // added to kill a mutator
            );
        }

        @ParameterizedTest(name = "{0}")
        @MethodSource("dataForSulfurasTests")
        public void sulfuras_item(String testName, int currentQuality, int sellInDays, int expectedUpdateQuality) {
            app = gildedRoseWithItem(item("Sulfuras, Hand of Ragnaros", currentQuality, sellInDays));

            app.updateQuality();

            assertItemQualityEquals(expectedUpdateQuality);
        }


        @ParameterizedTest(name = "{0}")
        @MethodSource("dataForBackstagePassesTests")
        public void backstage_passes_item(String testName, int currentQuality, int sellInDays, int expectedUpdateQuality) {
            app = gildedRoseWithItem(item("Backstage passes to a TAFKAL80ETC concert", currentQuality, sellInDays));

            app.updateQuality();

            assertItemQualityEquals(expectedUpdateQuality);
        }

        Stream<Arguments> dataForBackstagePassesTests() {
            return Stream.of(
                    arguments("quality increase one per day when sell in days > 10", 0, 11, 1),
                    arguments("quality increase one per day when sell in days > 10", 50, 11, 50), // added to kill a mutator
                    arguments("quality increase two per day when sell in days = 10", 0, 10, 2),
                    arguments("quality increase two per day when sell in days = 10", 48, 10, 50), // added to kill a mutator
                    arguments("quality increase two per day when sell in days < 10", 0, 9, 2),
                    arguments("quality increase two per day when sell in days > 5", 0, 6, 2),
                    arguments("quality increase three per day when sell in days = 5", 0, 5, 3),
                    arguments("quality increase three per day when sell in days = 5", 47, 5, 50), // added to kill a mutator
                    arguments("quality increase three per day when sell in days < 5", 0, 4, 3),
                    arguments("quality increase three per day when sell in days < 5", 0, 4, 3),
                    arguments("quality drop to 0 when expired regardless quality value", 50, 0, 0),
                    arguments("quality drop to 0 when expired regardless quality value", 50, 1, 50), // added to kill a mutator
                    arguments("quality can't exceed 50 when increasing two per day", 49, 9, 50),
                    arguments("quality can't exceed 50 when increasing three per day", 48, 4, 50)
            );
        }

    }

    private Item anyQualityItemWithSellIn(String name, int sellIn) {
        return item(name, 20, sellIn);
    }

    private void assertItemQualityEquals(int expected) {
        assertQualityEqualsByItemIndex(expected, 0);
    }

    private void assertQualityEqualsByItemIndex(int expected, int index) {
        assertEquals(expected, app.items[index].quality);
    }

    private void assertSellInEquals(int expected) {
        assertEquals(expected, app.items[0].sellIn);
    }

    private GildedRose gildedRoseWithItem(Item... items) {
        return new GildedRose(items);
    }

    private Item item(String name, int quality, int sellIn) {
        return new Item(name, sellIn, quality);
    }

}
