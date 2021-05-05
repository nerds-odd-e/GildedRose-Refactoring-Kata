package com.gildedrose;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class GildedRoseTest {

    private GildedRose app;

    public static Stream<Arguments> dataForNormalTests() {
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
    public void item(String testName, int currentQuality, int sellInDays, int expectedUpdateQuality) {
        app = gildedRoseWithItem(item("normal", currentQuality, sellInDays));

        app.updateQuality();

        assertItemQualityEquals(expectedUpdateQuality);
    }

    public static Stream<Arguments> dataForAgedBrieTests() {
        return Stream.of(
                arguments("quality increase one per day when not expired", 0, 1, 1),
                arguments("quality increase two per day when just expired", 0, 0, 2),
                arguments("quality increase two per day when already expired", 0, -1, 2),
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

    public static Stream<Arguments> dataForSulfurasTests() {
        return Stream.of(
                arguments("quality is always 80 and never changes", 80, 1, 80)
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("dataForSulfurasTests")
    public void sulfuras_item(String testName, int currentQuality, int sellInDays, int expectedUpdateQuality) {
        app = gildedRoseWithItem(item("Sulfuras, Hand of Ragnaros", currentQuality, sellInDays));

        app.updateQuality();

        assertItemQualityEquals(expectedUpdateQuality);
    }

    private void assertItemQualityEquals(int expected) {
        assertEquals(expected, app.items[0].quality);
    }

    private GildedRose gildedRoseWithItem(Item item) {
        return new GildedRose(new Item[]{item});
    }

    private Item item(String name, int quality, int sellIn) {
        return new Item(name, sellIn, quality);
    }

}
